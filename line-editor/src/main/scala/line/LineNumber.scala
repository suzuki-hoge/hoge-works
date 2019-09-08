package line

case class LineNumber(v: Int) {
  def eq(o: LineNumber): Boolean = v == o.v

  def ==(o: LineNumber): Boolean = v == o.v

  def >=(o: LineNumber): Boolean = v >= o.v

  def <=(o: LineNumber): Boolean = v <= o.v

  def >(o: LineNumber): Boolean = v > o.v

  def <(o: LineNumber): Boolean = v < o.v

  def +(o: Int): LineNumber = LineNumber(v + o)

  def -(o: Int): LineNumber = LineNumber(v - o)

  def asRange:LineRange = LineRange(this, this)
}

object LineNumber {
  def parse(s: String, last: Int): Option[LineNumber] = s match {
    case _s if _s.matches("""^\d+$""") => Some(s.toInt).filter(i => 0 < i && i <= last).map(LineNumber(_))
    case _s if _s == "$" => Some(LineNumber(last))
    case _ => None
  }
}
