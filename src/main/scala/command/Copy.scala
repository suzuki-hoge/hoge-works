package command

import line.Type.SBF
import line.{LineNumber, LineRange, Lines}

case class Copy(range: LineRange, n: LineNumber) extends ExecutableCommand {
  override def execute(ls: Lines): Lines = {
    val yanked = ls.yank(range)
    val append: SBF = b => Seq(b) ++ yanked

    ls.sMap(l => l.sMapIf(n, append))
  }

  override def undo(ls: Lines): Option[UndoCommand] = Some(DeletionUndo(range.shift(n + 1)))
}

object Copy extends ParsableCommand {
  override def parse(s: String, last: Int): Option[Command] = s.split("/").toSeq.map(_.trim) match {
    case Seq(_range, _name, _n) if Seq("copy", "c").contains(_name) => for {
      __range <- LineRange.parse(_range, last)
      __n <- LineNumber.parse(_n, last)
    } yield Copy(__range, __n)
    case _ => None
  }
}
