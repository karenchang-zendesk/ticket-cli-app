package ticketapp

object Main {
  def main(args: Array[String]): Unit = {

    val service = new TicketService(TicketStore.getTicket)
    val result = service.getTicket(TicketId(1))

    println(result)
  }
}
