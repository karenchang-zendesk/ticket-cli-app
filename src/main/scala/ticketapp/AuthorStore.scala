package ticketapp

case class Name(value: String)

case class Age(value: Int)

case class Author(name: Name, age: Age)

object AuthorStore {

  private val authors: Map[Name, Age] = Map(Name("Jack") -> Age(32))

  def getAuthor(name: Name): Option[Author] = {
    val maybeAge: Option[Age] = authors.get(name)
    maybeAge.map(age => Author(name, age))

//    maybeAge match {
//      case Some(age) => Some(Author(name, age))
//      case None => None
//    }
  }
}
