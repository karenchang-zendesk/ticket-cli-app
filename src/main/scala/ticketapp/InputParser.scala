package ticketapp

case class UserInput(value: String)
case class TicketId(value: Long)

object InputParser {

  def parseTicketId(input: UserInput): TicketId = TicketId(input.value.toLong)
}

// Todo: if the input is not a Long