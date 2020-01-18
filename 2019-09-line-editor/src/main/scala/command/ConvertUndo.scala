package command

import line.Type.SBF
import line.{Body, LineRange, Lines}

case class ConvertUndo(range: LineRange, bs: Seq[Body]) extends UndoCommand {
  private val delete: SBF = _ => Seq()
  private val append: SBF = b => Seq(b) ++ bs

  override def execute(ls: Lines): Lines = ls
    .sMap(l => l.sMapIf(range, delete))
    .sMap(l => l.sMapIf(range.s - 1, append))
}


