package com.nutomic.ensichat.protocol.body

import java.nio.ByteBuffer

import com.nutomic.ensichat.protocol.Message
import com.nutomic.ensichat.util.BufferUtils

import scala.Predef.String

object MessageReceived {

  val Type = 5

  /**
   * Constructs [[MessageReceived]] instance from byte array.
   */
  def read(array: Array[Byte]): MessageReceived = {
    val b = ByteBuffer.wrap(array)
    val messageId = BufferUtils.getUnsignedInt(b)
    new MessageReceived(messageId)
  }

}

case class MessageReceived(messageId: Long) extends MessageBody {

  override def protocolType = -1

  override def contentType = MessageReceived.Type

  override def write: Array[Byte] = {
    val b = ByteBuffer.allocate(length)
    BufferUtils.putUnsignedInt(b, messageId)
    b.array()
  }

  override def length = 4

  override def equals(a: Any): Boolean = a match {
    case o: MessageReceived => o.messageId == messageId
    case _ => false
  }

}
