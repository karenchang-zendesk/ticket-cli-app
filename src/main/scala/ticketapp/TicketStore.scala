package ticketapp

object TicketStore {

  val tickets: Map[TicketId, Ticket] = Map(TicketId(1) -> Ticket("Hello", "Karen"))

  def getTicket(id: TicketId): Option[Ticket] = tickets.get(id)
}
