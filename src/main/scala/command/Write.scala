package command

object Write extends EditorCommand {
  def parse(s: String): Option[Write.type] = s.trim match {
    case "write" => Some(Write)
    case "w" => Some(Write)
    case _ => None
  }
}
