package com.nutomic.ensichat.protocol.body

import android.test.AndroidTestCase
import junit.framework.Assert

class MessageReceivedTest extends AndroidTestCase {

  def testWriteRead(): Unit = {
    val mr = new MessageReceived(123)
    val bytes = mr.write
    val body = MessageReceived.read(bytes)
    Assert.assertEquals(mr, body.asInstanceOf[MessageReceived])
  }

}
