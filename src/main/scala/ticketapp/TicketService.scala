package ticketapp

case class Ticket(description: String, author: String)

case class EnrichedTicket(description: String, author: String, age: Option[Int])

class TicketService(fetchTicket: TicketId => Option[Ticket],
                    fetchAuthor: Name => Option[Author]) {

  def getTicket(id: TicketId): Option[EnrichedTicket] = {

    //    val maybeTicket = fetchTicket(id)
    //    maybeTicket match {
    //      case Some(ticket) =>
    //        val name = Name(ticket.author)
    //        val maybeAuthor: Option[Author] = fetchAuthor(name)
    //        maybeAuthor match {
    //          case Some(author) => Some(EnrichedTicket(ticket.description, ticket.author, author.age.value))
    //          case None => None
    //        }
    //      case None => None
    //    }

//         fetchTicket(id).flatMap(ticket => fetchAuthor(Name(ticket.author)).map(author => EnrichedTicket(ticket.description, ticket.author, author.age.value)))

    fetchTicket(id).map { ticket =>
      val maybeAuthor: Option[Author] = fetchAuthor(Name(ticket.author))
      val x: Option[Int] = maybeAuthor.map (author => author.age.value)
      EnrichedTicket(ticket.description, ticket.author, x)
    }
  }

//    for {
//      ticket <- fetchTicket(id)
//      author <- fetchAuthor(Name(ticket.author))
//    } yield EnrichedTicket(ticket.description, ticket.author, author.age.value)

}
