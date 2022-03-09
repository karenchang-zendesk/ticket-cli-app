package ticketapp

import monix.eval.Task
import weaver.monixcompat.FunSuite

object TicketServiceSpec extends FunSuite {

  test("if the ticket and author both exist, should return the enriched ticket") {
    val testTicket = Ticket("des", "author")
    val testAuthor = Author(Name("author"), Age(20))
    val testEnrichedTicket = EnrichedTicket("des", "author", Some(20))
    val fetchTicketSuccessfully: TicketId =>Task[Option[Ticket]] = _ => Task(Some(testTicket))
    val fetchAuthorSuccessfully: Name => Task[Option[Author]] = _ => Task(Some(testAuthor))
    val service = new TicketService(fetchTicketSuccessfully, fetchAuthorSuccessfully)

    expect(service.getTicket(TicketId(1)) == Some(testEnrichedTicket))
  }

  test("if the ticket exists but the author does not exist, should return none") {
    val testTicket = Ticket("des", "author")
    val fetchTicketSuccessfully: TicketId => Task[Option[Ticket]] = _ => Task(Some(testTicket))
    val fetchAuthorUnSuccessfully: Name => Task[Option[Author]] = _ => Task(None)
    val service = new TicketService(fetchTicketSuccessfully, fetchAuthorUnSuccessfully)

    expect(service.getTicket(TicketId(1)) == None)
  }

  test("if the ticket dose not exist, should return None") {
    val fetchTicketUnsuccessfully: TicketId => Task[Option[Ticket]] = _ => Task(None)
    val fetchAuthorUnSuccessfully: Name => Task[Option[Author]] = _ => Task(None)
    val service = new TicketService(fetchTicketUnsuccessfully, fetchAuthorUnSuccessfully)

    expect(service.getTicket(TicketId(1)) == None)
  }
}
