package ticketapp

import monix.eval.Task
import cats.syntax.all._

case class Ticket(description: String, author: String)

case class EnrichedTicket(description: String, author: String, age: Option[Int])

class TicketService(fetchTicket: TicketId => Task[Option[Ticket]],
                    fetchAuthor: Name => Task[Option[Author]]) {

  def getTicket(id: TicketId): Task[Option[EnrichedTicket]] = {

    //    fetchTicket(id).map { ticket =>
    //      val maybeAuthor: Option[Author] = fetchAuthor(Name(ticket.author))
    //      val maybeAge: Option[Int] = maybeAuthor.map (author => author.age.value)
    //      EnrichedTicket(ticket.description, ticket.author, maybeAge)
    //    }

    val x: Task[Option[Ticket]] = fetchTicket(id)
    val result: Task[Option[EnrichedTicket]] = x.flatMap { maybeTicket =>
      maybeTicket.traverse { ticket =>
        val y: Task[Option[Author]] = fetchAuthor(Name(ticket.author))
        val z: Task[Option[Int]] = y.map(maybeAuthor => maybeAuthor.map(author => author.age.value))
        z.map(maybeAge => EnrichedTicket(ticket.description, ticket.author, maybeAge))
      }
    }
    result

    //    for {
    //      maybeTicket <- fetchTicket(id)
    //      maybeEnrichedTicket <- maybeTicket.traverse { ticket =>
    //        val y: Task[Option[Author]] = fetchAuthor(Name(ticket.author))
    //        val z: Task[Option[Int]] = y.map(maybeAuthor => maybeAuthor.map(author => author.age.value))
    //        z.map(maybeAge => EnrichedTicket(ticket.description, ticket.author, maybeAge))
    //      }
    //    } yield maybeEnrichedTicket
  }
}

    //  object TicketService {
    //    def apply(): TicketService = new TicketService(TicketStore.getTicket, AuthorStore.getAuthor)
    //  }
