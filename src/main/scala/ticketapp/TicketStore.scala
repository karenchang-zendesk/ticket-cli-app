package ticketapp

object TicketStore {

  private val tickets: Map[TicketId, Ticket] = Map(TicketId(1) -> Ticket("Hello", "Jack"), TicketId(2) -> Ticket("Hi", "Karen"))

  def getTicket(id: TicketId): Option[Ticket] = tickets.get(id)
}
