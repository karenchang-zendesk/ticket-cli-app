package ticketapp

case class Ticket(description: String, author: String)

class TicketService {

  def getTicket(id: TicketId): Option[Ticket] = ???
}

// Todo: Tight coupling and fake TicketStore