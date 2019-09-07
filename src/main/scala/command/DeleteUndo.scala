package command

import line.Type.SBF
import line.{LineNumber, Lines}

case class DeleteUndo(n: LineNumber, sbf: SBF) extends UndoCommand {
  override def execute(ls: Lines): Lines = ls.sMap(l => l.sMapIf(n, sbf))
}


