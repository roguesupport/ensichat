package com.nutomic.ensichat.service

import java.io.File

import android.app.{Notification, NotificationManager, PendingIntent, Service}
import android.content.{Context, Intent}
import android.os.Handler
import com.nutomic.ensichat.activities.MainActivity
import com.nutomic.ensichat.bluetooth.BluetoothInterface
import com.nutomic.ensichat.core.ChatService
import com.nutomic.ensichat.core.interfaces.{Settings, DatabaseInterface}
import com.nutomic.ensichat.util.{NotificationHandler, PRNGFixes}
import android.support.v4.app.NotificationCompat
import com.nutomic.ensichat.R

class EnsichatService extends Service {

  private val Tag = "EnsichatService"

  private lazy val binder = new EnsichatServiceBinder(this)

  private lazy val notificationHandler = new NotificationHandler(this)

  private lazy val notificationManager =
    getSystemService(Context.NOTIFICATION_SERVICE).asInstanceOf[NotificationManager]

  // TODO
  private val database: DatabaseInterface = null

  // TODO
  private lazy val settings: Settings = null

  private lazy val chatService = new ChatService(settings, database, new File(getFilesDir, "keys"))

  override def onBind(intent: Intent) =  binder

  override def onStartCommand(intent: Intent, flags: Int, startId: Int) = Service.START_STICKY

  /**
   * Generates keys and starts Bluetooth interface.
   */
  override def onCreate(): Unit = {
    super.onCreate()
    PRNGFixes.apply()
    showPersistentNotification()
    chatService.start()
    // TODO: how to handle callbacks?
    chatService.setTransmissionInterface(new BluetoothInterface(this, new Handler(),
      onMessageReceived, callConnectionListeners, onConnectionOpened))
  }

  def showPersistentNotification(): Unit = {
    val openIntent = PendingIntent.getActivity(this, 0, new Intent(this, classOf[MainActivity]), 0)
    val notification = new NotificationCompat.Builder(this)
      .setSmallIcon(R.drawable.ic_launcher)
      .setContentTitle(getString(R.string.app_name))
      .setContentIntent(openIntent)
      .setOngoing(true)
      .setPriority(Notification.PRIORITY_MIN)
      .build()
    notificationManager.notify(NotificationHandler.NotificationIdRunning, notification)
  }

  override def onDestroy(): Unit = {
    notificationManager.cancel(NotificationHandler.NotificationIdRunning)
    chatService.stop()
  }


}