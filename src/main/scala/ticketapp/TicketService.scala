package ticketapp

case class Ticket(description: String, author: String)

class TicketService(fetchTicket: TicketId => Option[Ticket]) {

  def getTicket(id: TicketId): Option[Ticket] = fetchTicket(id)
}
