package com.nutomic.ensichat.server

import com.nutomic.ensichat.core.ChatService

object Main extends App {
  val chatService = new ChatService()
  chatService.start()
  sys.addShutdownHook(chatService.stop())
}