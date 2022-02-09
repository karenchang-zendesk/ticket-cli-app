package ticketapp

import weaver.monixcompat.FunSuite

object TicketControllerSpec extends FunSuite {

  test("given a valid ticket id, if the ticket exists, should return the ticket description and author") {
    val testTicket = EnrichedTicket("des", "author", Some(32))
    val fetchTicketSuccessfully: TicketId => Option[EnrichedTicket] = _ => Some(testTicket)
    val controller = new TicketController(fetchTicketSuccessfully)

    expect(controller.getTicket(UserInput("1")) == "Description: des, Author: author, Age: 32")
  }

  test("given a valid ticket id, if the ticket does not exist, should return message - ticket not found") {
    val fetchTicketUnsuccessfully: TicketId =>  Option[EnrichedTicket] = _ => None
    val controller = new TicketController(fetchTicketUnsuccessfully)

    expect(controller.getTicket(UserInput("1")) == "Ticket not found")
  }

  test("given an invalid ticket id, should return message - ticket id is invalid ") {
    val fetchTicketUnsuccessfully: TicketId =>  Option[EnrichedTicket] = _ => None
    val controller = new TicketController(fetchTicketUnsuccessfully)

    expect(controller.getTicket(UserInput("a")) == "Ticket Id is invalid: a")
  }
}
