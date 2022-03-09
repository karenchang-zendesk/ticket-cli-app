package ticketapp

import monix.eval.Task

class TicketController(fetchTicket: TicketId => Task[Option[EnrichedTicket]]) {

  def getTicket(userInput: UserInput): Task[String] = {
    val maybeTicketId: Option[TicketId] = InputParser.parseTicketId(userInput)
    maybeTicketId match {
      case Some(id) =>
        val x: Task[Option[EnrichedTicket]] = fetchTicket(id)
        val y: Task[String] = x.map {
          case Some(ticket) => s"Description: ${ticket.description}, Author: ${ticket.author}, Age: ${ticket.age}"
          case None => "Ticket not found"
        }
        y
      case None => Task(s"Ticket Id is invalid: ${userInput.value}")
    }
  }
}
