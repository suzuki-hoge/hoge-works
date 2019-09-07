package command

import line.Lines

trait LineCommand {
  def execute(ls: Lines): Lines

  def undo(ls: Lines): UndoCommand
}

object LineCommand {
  def parse(s: String, last: Int): Option[LineCommand] = Seq(
    Append.parse(s, last),
    Delete.parse(s, last),
    Copy.parse(s, last),
    Move.parse(s, last),
    Execute.parse(s, last),
    Substitute.parse(s, last)
  ).flatten.headOption
}

trait EditorCommand {
}

object EditorCommand {
  def parse(s: String, undoCommand: Option[UndoCommand]): Option[EditorCommand] = Seq(
    Undo.parse(s, undoCommand),
    Write.parse(s)
  ).flatten.headOption
}

trait UndoCommand {
  def execute(ls: Lines): Lines
}
