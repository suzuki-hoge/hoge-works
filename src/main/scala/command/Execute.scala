package command

import command.Implicits._
import line.Type.F
import line.{LineRange, Lines, Text}

case class Execute(range: LineRange, regex: Text, ms: Motions) extends ExecutableCommand {
   private val execute: F = s => s.matches(regex.v).orId(ms.convert)(s)

  override def execute(ls: Lines): Lines = ls.map(l => l.mapIf(range, execute))

  override def undo(ls: Lines): Option[UndoCommand] = Some(ConvertUndo(range, ls.yank(range)))
}

case class Motions(ms: Seq[Motion]) {
  def convert: F = ms.map(_.convert).reduceLeft((acc, f) => f.compose(acc))
}

sealed trait Motion {
  def convert: F
}

object Capitalize extends Motion {
  override def convert: F = s => s.capitalize
}

object Period extends Motion {
  override def convert: F = s => s"$s."
}

object Implicits {

  implicit class Bool(v: Boolean) {
    def orId(f: F): F = if (v) f else identity
  }

}

object Execute extends ParsableCommand {
  override def parse(s: String, last: Int): Option[Command] = s.split("/").toSeq.map(_.trim) match {
    case Seq(_range, _name, _regex, _motions) if Seq("execute", "e").contains(_name) => for {
      __range <- LineRange.parse(_range, last)
      __regex <- Text.parse(_regex)
      __motions <- Motions.parse(_motions)
    } yield Execute(__range, __regex, __motions)
    case _ => None
  }
}

object Motions {
  def parse(s: String): Option[Motions] = s.split(" ").toSeq.flatMap(Motion.parse) match {
    case Seq() => None
    case ms => Some(Motions(ms))
  }
}

object Motion {
  def parse(s: String): Option[Motion] = s match {
    case "c" => Some(Capitalize)
    case "p" => Some(Period)
    case _ => None
  }
}
