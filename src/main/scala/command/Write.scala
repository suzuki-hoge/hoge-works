package command

object Write extends Command with ParsableCommand {
  override def parse(s: String, last: Int): Option[Command] = s.trim match {
    case "write" => Some(Write)
    case "w" => Some(Write)
    case _ => None
  }
}
