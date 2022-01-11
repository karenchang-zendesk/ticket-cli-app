package ticketapp

import weaver.monixcompat.FunSuite

object TicketControllerSpec extends FunSuite {

  test("given a valid ticket id, if the ticket exists, should return the ticket description and author") {
    val testTicket = Ticket("des", "author")
    val fetchTicketSuccessfully: TicketId => Option[Ticket] = _ => Some(testTicket)
    val service = new TicketService(fetchTicketSuccessfully)
    val controller = new TicketController(service.getTicket)

    expect(controller.getTicket(UserInput("1")) == "Description: des, Author: author")
  }

  test("given a valid ticket id, if the ticket does not exist, should return message - ticket not found") {
    val fetchTicketUnsuccessfully: TicketId => Option[Ticket] = _ => None
    val service = new TicketService(fetchTicketUnsuccessfully)
    val controller = new TicketController(service.getTicket)

    expect(controller.getTicket(UserInput("1")) == "Ticket not found")
  }

  test("given an invalid ticket id, should return message - ticket id is invalid ") {
    val fetchTicketUnsuccessfully: TicketId => Option[Ticket] = _ => None
    val service = new TicketService(fetchTicketUnsuccessfully)
    val controller = new TicketController(service.getTicket)

    expect(controller.getTicket(UserInput("a")) == "Ticket Id is invalid: a")
  }
}
