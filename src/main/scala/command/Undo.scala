package command

object Undo extends Command with ParsableCommand {
  override def parse(s: String, last: Int): Option[Command] = s.trim match {
    case "undo" => Some(Undo)
    case "u" => Some(Undo)
    case _ => None
  }
}
