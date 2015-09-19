package com.nutomic.ensichat.core.interfaces

import com.nutomic.ensichat.core.{User, Message, Address}

import scala.collection.SortedSet

trait DatabaseInterface {

  /**
   * Returns the count last messages for device.
   */
  def getMessages(address: Address, count: Int): SortedSet[Message]

  /**
   * Inserts the given new message into the database.
   */
  def onMessageReceived(msg: Message): Unit

  /**
   * Returns all contacts of this user.
   */
  def getContacts: Set[User]

  /**
   * Returns the contact with the given address if it exists.
   */
  def getContact(address: Address): Option[User]

  /**
   * Inserts the given device into contacts.
   */
  def addContact(contact: User): Unit

  def updateContact(contact: User): Unit

}
