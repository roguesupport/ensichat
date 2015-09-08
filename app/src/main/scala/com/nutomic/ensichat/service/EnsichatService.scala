package com.nutomic.ensichat.service

import android.app.{Notification, NotificationManager, PendingIntent, Service}
import android.content.{Context, Intent}
import com.nutomic.ensichat.activities.MainActivity
import com.nutomic.ensichat.core.ChatService
import com.nutomic.ensichat.util.{NotificationHandler, PRNGFixes}
import android.support.v4.app.NotificationCompat

class EnsichatService extends Service {

  private val Tag = "EnsichatService"

  private lazy val binder = new EnsichatServiceBinder(this)

  private lazy val notificationHandler = new NotificationHandler(this)

  private lazy val notificationManager =
    getSystemService(Context.NOTIFICATION_SERVICE).asInstanceOf[NotificationManager]

  private lazy val chatService = new ChatService

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
    // TODO: pass bluetooth interface
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