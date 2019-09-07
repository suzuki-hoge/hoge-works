package command

import line.Lines

trait Command {
}

object Command {
  def dispatch(s: String, last: Int): Option[Command] = Seq(
    Append.parse(s, last),
    Delete.parse(s, last),
    Copy.parse(s, last),
    Move.parse(s, last),
    Execute.parse(s, last),
    Substitute.parse(s, last)
  ).flatten.headOption
}

trait ParsableCommand {
  def parse(s: String, last: Int): Option[Command]
}

trait ExecutableCommand extends Command {
  def execute(ls: Lines): Lines

  def undo(ls: Lines): Option[UndoCommand]
}

trait UndoCommand {
  def execute(ls: Lines): Lines
}
