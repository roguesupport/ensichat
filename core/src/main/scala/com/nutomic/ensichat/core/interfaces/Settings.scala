package com.nutomic.ensichat.core.interfaces

object Settings {

  val KeyUserName     = "user_name"
  val KeyUserStatus   = "user_status"
  val KeyScanInterval = "scan_interval_seconds"
  val MaxConnections  = "max_connections"

  val DefaultUserStatus                = "Let's chat!"
  val DefaultScanInterval              = 15
  val DefaultNotificationSoundsEnabled = true
  val DefaultMaxConnections            = 1000000

}

trait Settings {

  def put[T](key: String, value: T): Unit
  def get[T](key: String, default: T): T

}
