package command

import line.Type.F
import line.{LineRange, Lines, Text}

case class Substitute(range: LineRange, src: Text, dst: Text) extends LineCommand {
  private val substitute: F = s => s.replace(src.v, dst.v)

  override def execute(ls: Lines): Lines = ls.map(l => l.mapIf(range, substitute))

  override def undo(ls: Lines): UndoCommand = ConvertUndo(range, ls.yank(range))
}

object Substitute {
  def parse(s: String, last: Int): Option[Substitute] = s.split("/").toSeq.map(_.trim) match {
    case Seq(_range, _name, _src, _dst) if Seq("substitute", "s").contains(_name) => for {
      __range <- LineRange.parse(_range, last)
      __src <- Text.parse(_src)
      __dst <- Text.parse(_dst)
    } yield Substitute(__range, __src, __dst)
    case _ => None
  }
}
