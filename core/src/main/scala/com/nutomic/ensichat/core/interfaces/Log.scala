package com.nutomic.ensichat.core.interfaces

object Log {

  def setLogClass[T](logClass: Class[T]) = {
    this.logClass = logClass
  }

  private var logClass: Class[_] = _

  def v(tag: String, message: String, throwable: Throwable = null) =
    logClass.getMethod("v", classOf[String], classOf[String], classOf[Throwable])
      .invoke(null, tag, message, throwable)

  def d(tag: String, message: String, throwable: Throwable = null) =
    logClass.getMethod("v", classOf[String], classOf[String], classOf[Throwable])
      .invoke(null, tag, message, throwable)

  def i(tag: String, message: String, throwable: Throwable = null) =
    logClass.getMethod("v", classOf[String], classOf[String], classOf[Throwable])
      .invoke(null, tag, message, throwable)

  def w(tag: String, message: String, throwable: Throwable = null) =
    logClass.getMethod("v", classOf[String], classOf[String], classOf[Throwable])
      .invoke(null, tag, message, throwable)

  def e(tag: String, message: String, throwable: Throwable = null) =
    logClass.getMethod("v", classOf[String], classOf[String], classOf[Throwable])
      .invoke(null, tag, message, throwable)

}
