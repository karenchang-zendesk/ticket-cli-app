package ticketapp

import weaver.monixcompat.FunSuite

object InputParserSpec extends FunSuite{

  test("should return ticket id given a user input") {
    val input = UserInput("1")
    val result = InputParser.parseTicketId(input)
    expect(result == TicketId(1))
  }
}
