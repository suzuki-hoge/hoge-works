package command

import line.Type.SBF
import line.{LineRange, Lines}

case class Delete(range: LineRange) extends ExecutableCommand {
  private val delete: SBF = b => Seq()

  override def execute(ls: Lines): Lines = ls.sMap(l => l.sMapIf(range, delete))

  override def undo(ls: Lines): Option[UndoCommand] = Some(DeleteUndo(range.s - 1, b => Seq(b) ++ ls.yank(range)))
}

object Delete extends ParsableCommand {
  override def parse(s: String, last: Int): Option[Command] = s.split("/").toSeq.map(_.trim) match {
    case Seq(_range, _name) if Seq("delete", "d").contains(_name) => for {
      __range <- LineRange.parse(_range, last)
    } yield Delete(__range)
    case _ => None
  }
}
