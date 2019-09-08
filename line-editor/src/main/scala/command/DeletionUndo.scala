package command

import line.Type.SBF
import line.{LineRange, Lines}

case class DeletionUndo(range: LineRange) extends UndoCommand {
  private val delete: SBF = _ => Seq()

  override def execute(ls: Lines): Lines = ls.sMap(l => l.sMapIf(range, delete))
}


