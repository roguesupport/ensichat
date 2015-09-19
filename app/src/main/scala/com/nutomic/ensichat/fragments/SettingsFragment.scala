package com.nutomic.ensichat.fragments

import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import android.preference.Preference.OnPreferenceChangeListener
import android.preference.{Preference, PreferenceFragment, PreferenceManager}
import com.nutomic.ensichat.activities.EnsichatActivity
import com.nutomic.ensichat.core.body.UserInfo
import com.nutomic.ensichat.core.interfaces.Settings._
import com.nutomic.ensichat.fragments.SettingsFragment._
import com.nutomic.ensichat.util.Database

object SettingsFragment {
  val Version = "version"
}

/**
 * Settings screen.
 */
class SettingsFragment extends PreferenceFragment with OnPreferenceChangeListener
  with OnSharedPreferenceChangeListener {

  private lazy val database = new Database(getActivity)

  private lazy val name                 = findPreference(KeyUserName)
  private lazy val status               = findPreference(KeyUserStatus)
  private lazy val notificationSoundsOn = findPreference(KeyNotificationSoundsOn)
  private lazy val scanInterval         = findPreference(KeyScanInterval)
  private lazy val maxConnections       = findPreference(MaxConnections)
  private lazy val version              = findPreference(Version)

  private lazy val prefs = PreferenceManager.getDefaultSharedPreferences(getActivity)

  override def onCreate(savedInstanceState: Bundle): Unit =  {
    super.onCreate(savedInstanceState)

    addPreferencesFromResource(R.xml.settings)

    notificationSoundsOn.setDefaultValue(DefaultNotificationSoundsOn)
    scanInterval.setDefaultValue(DefaultScanInterval)
    maxConnections.setDefaultValue(DefaultMaxConnections)

    name.setOnPreferenceChangeListener(this)
    status.setOnPreferenceChangeListener(this)

    scanInterval.setOnPreferenceChangeListener(this)

    if (BuildConfig.DEBUG) {
      maxConnections.setOnPreferenceChangeListener(this)
    } else
      getPreferenceScreen.removePreference(maxConnections)

    val packageInfo = getActivity.getPackageManager.getPackageInfo(getActivity.getPackageName, 0)
    version.setSummary(packageInfo.versionName)
    prefs.registerOnSharedPreferenceChangeListener(this)
  }

  override def onDestroy(): Unit = {
    super.onDestroy()
    prefs.unregisterOnSharedPreferenceChangeListener(this)
  }

  /**
   * Updates summary, sends updated name to contacts.
   */
  override def onPreferenceChange(preference: Preference, newValue: AnyRef): Boolean = {
    preference.setSummary(newValue.toString)
    true
  }

  /**
   * Sends the updated username or status to all contacts.
   */
  override def onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
    key match {
      case KeyUserName | KeyUserStatus =>
        val service = getActivity.asInstanceOf[EnsichatActivity].service
        val ui = new UserInfo(prefs.getString(KeyUserName, ""), prefs.getString(KeyUserStatus, ""))
        database.getContacts.foreach(c => service.get.sendTo(c.address, ui))
    }
  }

}
