package line

import line.Implicits._
import line.Type.{F, SBF}

case class Line(n: LineNumber, b: Body) {
  def mapIf(range: LineRange, f: F): Body = b.map(range.contains(n).orId(f))

  def mapIf(number: LineNumber, f: F): Body = b.map(number.eq(n).orId(f))

  def sMapIf(range: LineRange, sbf: SBF): Seq[Body] = b.sMap(range.contains(n).orSeq(sbf))

  def sMapIf(number: LineNumber, sbf: SBF): Seq[Body] = b.sMap(number.eq(n).orSeq(sbf))

  def sMapIfOr(number: LineNumber, sbf1: SBF, range: LineRange, sbf2: SBF): Seq[Body] = {
    if (number == n) {
      b.sMap(sbf1)
    } else if (range.contains(n)) {
      b.sMap(sbf2)
    } else {
      Seq(b)
    }
  }

  def yank(range: LineRange): Option[Body] = if (range.contains(n)) Some(b) else None

  def display: Option[String] = b match {
    case NonEmptyBody(v) => Some(s"${n.v}: $v")
    case EmptyBody => Some(s"${n.v}: ")
    case NonVisibleBody => None
  }
}

object Implicits {

  implicit class Bool(v: Boolean) {
    def orId(f: F): F = if (v) f else identity

    def orSeq(sbf: SBF): SBF = if (v) sbf else b => Seq(b)
  }

}
