package command

import line.Type.SBF
import line.{LineNumber, LineRange, Lines}

case class Move(range: LineRange, n: LineNumber) extends LineCommand {
  override def execute(ls: Lines): Lines = {
    val yanked = ls.yank(range)
    val append: SBF = b => Seq(b) ++ yanked
    val delete: SBF = _ => Seq()

    ls.sMap(l => l.sMapIfOr(n, append, range, delete))
  }

  override def undo(ls: Lines): UndoCommand = {
    val yanked = ls.yank(range)
    val append: SBF = b => Seq(b) ++ yanked

    if (range.s < n)
      MoveUndo(range.shift(n + 1, -deletedCount), range.s - 1, append)
    else
      MoveUndo(range.shift(n + 1), range.s - 1 + deletedCount, append)
  }

  private def deletedCount = range.size
}

object Move {
  def parse(s: String, last: Int): Option[Move] = (s.split("/").toSeq.map(_.trim) match {
    case Seq(_range, _name, _n) if Seq("move", "m").contains(_name) => for {
      __range <- LineRange.parse(_range, last)
      __n <- LineNumber.parse(_n, last)
    } yield Move(__range, __n)
    case _ => None
  }).filterNot(m => m.range.contains(m.n))
}
