package line

case class LineRange(s: LineNumber, e: LineNumber) {
  def contains(o: LineNumber): Boolean = s <= o && o <= e

  def shift(n: LineNumber): LineRange = LineRange(n, n + (e.v - s.v))

  def shift(n: LineNumber, i: Int): LineRange = LineRange(n + i, n + (e.v - s.v) + i)

  def size: Int = e.v - s.v + 1
}

object LineRange {
  def parse(s: String, last: Int): Option[LineRange] = s.split(",").toSeq.map(_.trim) match {
    case Seq("%") => Some(LineRange(LineNumber(1), LineNumber(last)))

    case Seq("$") => Some(LineRange(LineNumber(last), LineNumber(last)))

    case Seq(_s) => for {
      __s <- LineNumber.parse(_s, last)
    } yield LineRange(__s, __s)

    case Seq(_s, "$") => for {
      __s <- LineNumber.parse(_s, last)
    } yield LineRange(__s, LineNumber(last))

    case Seq(_s, _e) => for {
      __s <- LineNumber.parse(_s, last)
      __e <- LineNumber.parse(_e, last)
    } yield LineRange(__s, __e)

    case _ => None
  }
}
