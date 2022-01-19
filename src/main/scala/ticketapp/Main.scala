package ticketapp

import scala.annotation.tailrec
import scala.io.StdIn

object Main {

  private val service = new TicketService(TicketStore.getTicket, AuthorStore.getAuthor)
  private val controller = new TicketController(service.getTicket)

  def main(args: Array[String]): Unit = {
    loop()
  }

  @tailrec
  private def loop(): Unit = {
    val userInput = StdIn.readLine("Enter Ticket Id: ")
    val result = controller.getTicket(UserInput(userInput))
    println(result)
    loop()
  }
}
