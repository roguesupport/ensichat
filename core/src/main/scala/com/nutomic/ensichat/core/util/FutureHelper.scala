package com.nutomic.ensichat.core.util

import scala.concurrent.{ExecutionContext, Future}

/**
 * Wraps [[Future]], so that exceptions are always thrown.
 *
 * @see https://github.com/saturday06/gradle-android-scala-plugin/issues/56
 */
object FutureHelper {

  def apply[A](action: => A)(implicit executor: ExecutionContext): Future[A] = {
    val f = Future(action)
    f.onFailure {
      case e =>
        // TODO: check if we need to throw on GUI thread
        throw e
    }
    f
  }

}
