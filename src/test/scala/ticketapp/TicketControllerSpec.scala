package ticketapp

import monix.eval.Task
import weaver.monixcompat.{FunSuite, SimpleTaskSuite}

object TicketControllerSpec extends SimpleTaskSuite {

  test("given a valid ticket id, if the ticket exists, should return the ticket description and author") {
    val testTicket = EnrichedTicket("des", "author", Some(32))
    val fetchTicketSuccessfully: TicketId => Task[Option[EnrichedTicket]] = _ => Task(Some(testTicket))
    val controller = new TicketController(fetchTicketSuccessfully)

    controller.getTicket(UserInput("1")).map( result => expect(result == "Description: des, Author: author, Age: 32"))
  }

  test("given a valid ticket id, if the ticket does not exist, should return message - ticket not found") {
    val fetchTicketUnsuccessfully: TicketId =>  Task[Option[EnrichedTicket]] = _ => Task(None)
    val controller = new TicketController(fetchTicketUnsuccessfully)

    controller.getTicket(UserInput("1")).map( result => expect(result == "Ticket not found"))
  }

  test("given an invalid ticket id, should return message - ticket id is invalid ") {
    val fetchTicketUnsuccessfully: TicketId =>  Task[Option[EnrichedTicket]] = _ => Task(None)
    val controller = new TicketController(fetchTicketUnsuccessfully)

    controller.getTicket(UserInput("a")).map( result => expect(result == "Ticket Id is invalid: a"))
  }
}
