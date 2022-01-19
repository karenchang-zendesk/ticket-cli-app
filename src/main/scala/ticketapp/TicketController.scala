package ticketapp

class TicketController(fetchTicket: TicketId => Option[EnrichedTicket]) {

  def getTicket(userInput: UserInput): String = {
    val maybeTicketId: Option[TicketId] = InputParser.parseTicketId(userInput)
    maybeTicketId match {
      case Some(id) =>
        val maybeTicket = fetchTicket(id)
        maybeTicket match {
          case Some(ticket) => s"Description: ${ticket.description}, Author: ${ticket.author}, Age: ${ticket.age}"
          case None => "Ticket not found"
        }
      case None => s"Ticket Id is invalid: ${userInput.value}"
    }
  }
}
