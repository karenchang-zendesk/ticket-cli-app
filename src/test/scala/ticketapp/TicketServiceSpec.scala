package ticketapp

import weaver.monixcompat.FunSuite

object TicketServiceSpec extends FunSuite {

  test("if the ticket exists, should return the ticket unchanged ") {
    val testTicket = Ticket("des", "author")
    val fetchTicketSuccessfully: TicketId => Option[Ticket] = _ => Some(testTicket)
    val service = new TicketService(fetchTicketSuccessfully)

    expect(service.getTicket(TicketId(1)) == Some(testTicket))
  }

  test("if the ticket dose not exist, should return None") {
    val fetchTicketUnsuccessfully: TicketId => Option[Ticket] = _ => None
    val service = new TicketService(fetchTicketUnsuccessfully)

    expect(service.getTicket(TicketId(1)) == None)
  }
}
