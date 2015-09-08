package com.nutomic.ensichat.core.interfaces

trait Preferences {
  def put[T](key: String, value: T): Unit
  def get[T](key: String, default: T): T
}
