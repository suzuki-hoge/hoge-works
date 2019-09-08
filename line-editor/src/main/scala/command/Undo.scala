package command

object Undo extends EditorCommand {
  def parse(s: String, undoCommand: Option[UndoCommand]): Option[Undo.type] = undoCommand match {
    case None => None
    case _ => s.trim match {
      case "undo" => Some(Undo)
      case "u" => Some(Undo)
      case _ => None
    }
  }
}
