package ticketapp

import weaver.monixcompat.FunSuite

object InputParserSpec extends FunSuite{

  test("should return ticket id given a user input") {
    val input = UserInput("1")
    val result: Option[TicketId] = InputParser.parseTicketId(input)
    expect(result == Some(TicketId(1)))
  }

  test("should return None if user input is not a number") {
    val input = UserInput("abc")
    val result: Option[TicketId] = InputParser.parseTicketId(input)
    expect(result == None)
  }

  test("should return None given non positive value") {
    val input = UserInput("0")
    val result: Option[TicketId] = InputParser.parseTicketId(input)
    expect(result == None)
  }
}
