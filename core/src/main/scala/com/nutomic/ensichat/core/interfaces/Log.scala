package com.nutomic.ensichat.core.interfaces

// TODO: is this the best option?
//       if so, pass implementation in
object Log {
  def setLogInstance(log: Log) = instance = log
  private var instance: Log = _
  def v(tag: String, message: String, throwable: Throwable = null) = instance.v(tag, message, Option(throwable))
  def d(tag: String, message: String, throwable: Throwable = null) = instance.d(tag, message, Option(throwable))
  def i(tag: String, message: String, throwable: Throwable = null) = instance.i(tag, message, Option(throwable))
  def w(tag: String, message: String, throwable: Throwable = null) = instance.w(tag, message, Option(throwable))
  def e(tag: String, message: String, throwable: Throwable = null) = instance.e(tag, message, Option(throwable))
}

trait Log {
  def v(tag: String, message: String, throwable: Option[Throwable])
  def d(tag: String, message: String, throwable: Option[Throwable])
  def i(tag: String, message: String, throwable: Option[Throwable])
  def w(tag: String, message: String, throwable: Option[Throwable])
  def e(tag: String, message: String, throwable: Option[Throwable])
}
