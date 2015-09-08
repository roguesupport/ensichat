package com.nutomic.ensichat.protocol.body

import android.test.AndroidTestCase
import com.nutomic.ensichat.core.body.UserInfo
import junit.framework.Assert

class UserInfoTest extends AndroidTestCase {

  def testWriteRead(): Unit = {
    val name = new UserInfo("name", "status")
    val bytes = name.write
    val body = UserInfo.read(bytes)
    Assert.assertEquals(name, body.asInstanceOf[UserInfo])
  }

}
