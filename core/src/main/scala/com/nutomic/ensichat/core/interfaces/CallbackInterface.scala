package com.nutomic.ensichat.core.interfaces

import com.nutomic.ensichat.core.Message

/**
 * Created by felix on 19.09.15.
 */
trait CallbackInterface {

  /*
    TODO need do:
    notificationHandler.onMessageReceived(msg)
    val i = new Intent(ChatService.ActionMessageReceived)
    i.putExtra(ChatService.ExtraMessage, msg)
    LocalBroadcastManager.getInstance(this)
      .sendBroadcast(i)
    need param?
  */
  def onMessageReceived(msg: Message): Unit

  def onConnectionsChanged(): Unit
}
