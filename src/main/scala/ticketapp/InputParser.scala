package ticketapp

case class UserInput(value: String)

case class TicketId(value: Long)

object InputParser {

  def parseTicketId(input: UserInput): Option[TicketId] = {
    val maybeNumber: Option[Long] = input.value.toLongOption
    maybeNumber match {
      case Some(value) if value > 0 => Some(TicketId(value))
      case Some(_) => None
      case None => None
    }
  }
}
