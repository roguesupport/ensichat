package com.nutomic.ensichat.core.body

import junit.framework.TestCase
import org.junit.Assert._

object ConnectionInfoTest {

  def generateCi(context: Context) = {
    val crypto = new Crypto(context)
    if (!crypto.localKeysExist)
      crypto.generateLocalKeys()
    new ConnectionInfo(crypto.getLocalPublicKey)
  }

}

class ConnectionInfoTest extends TestCase {

  def testWriteRead(): Unit = {
    val ci = ConnectionInfoTest.generateCi(getContext)
    val bytes = ci.write
    val body = ConnectionInfo.read(bytes)
    assertEquals(ci.key, body.asInstanceOf[ConnectionInfo].key)
  }

}
