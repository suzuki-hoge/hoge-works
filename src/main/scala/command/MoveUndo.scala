package command

import line.Type.SBF
import line.{LineNumber, LineRange, Lines}

case class MoveUndo(range: LineRange, n: LineNumber, append: SBF) extends UndoCommand {
  private val delete: SBF = _ => Seq()

  override def execute(ls: Lines): Lines = {
    ls.sMap(l => l.sMapIfOr(n, append, range, delete))
  }
}


