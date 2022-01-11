package ticketapp

import scala.annotation.tailrec
import scala.io.StdIn

object Main {

  private val service = new TicketService(TicketStore.getTicket)
  private val controller = new TicketController(service.getTicket)

  def main(args: Array[String]): Unit = {
    loop()
  }

  @tailrec
  private def loop(): Unit = {
    val userInput = StdIn.readLine()
    val result = controller.getTicket(UserInput(userInput))
    println(result)
    loop()
  }
}
