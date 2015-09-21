package com.nutomic.ensichat.core

import org.junit.Assert._
import junit.framework.TestCase

class CryptoTest extends TestCase {


  private lazy val crypto: Crypto = new Crypto(getContext)

  override def setUp(): Unit = {
    super.setUp()
    if (!crypto.localKeysExist) {
      crypto.generateLocalKeys()
    }
  }

  def testSignVerify(): Unit = {
    MessageTest.messages.foreach { m =>
      val signed = crypto.sign(m)
      assertTrue(crypto.verify(signed, crypto.getLocalPublicKey))
      assertEquals(m.header, signed.header)
      assertEquals(m.body, signed.body)
    }
  }

  def testEncryptDecrypt(): Unit = {
    MessageTest.messages.foreach{ m =>
      val encrypted = crypto.encrypt(crypto.sign(m), crypto.getLocalPublicKey)
      val decrypted = crypto.decrypt(encrypted)
      assertEquals(m.body, decrypted.body)
      assertEquals(m.header, encrypted.header)
    }
  }

}
