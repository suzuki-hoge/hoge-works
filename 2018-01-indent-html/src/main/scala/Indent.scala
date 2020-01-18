case class Indent(v: Int) {
  override def toString: String = "  " * v

  def +(n: Int): Indent = Indent(v + n)
}

object Indent {
  def zero: Indent = Indent(0)
}
