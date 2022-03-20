package ticketapp

import cats.effect.ExitCode
import monix.eval.{Task, TaskApp}

import scala.io.StdIn

object Main extends TaskApp {

  //  If used companion object
  //  private val service = TicketService()

  private val service = new TicketService(TicketStore.getTicket, AuthorStore.getAuthor)
  private val controller = new TicketController(service.getTicket)

  override def run(args: List[String]): Task[ExitCode] = loop().map(_ => ExitCode.Success)

  private def loop(): Task[Unit] = {
    val y = (userInput: String) => controller.getTicket(UserInput(userInput))
    val x = Task(StdIn.readLine("Enter Ticket Id: "))

    for {
      userInput <- x
      result <- y(userInput)
      _ <- Task(println(result))
      _ <- loop()
    } yield ()
  }

}
