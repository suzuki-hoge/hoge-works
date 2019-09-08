package line

import line.Type.{F, SBF}

trait Body {
  def map(f: F): Body

  def sMap(sbf: SBF): Seq[Body]
}

object Body {
  def of(s: String): Body = NonEmptyBody(s)

  def empty: Body = EmptyBody

  def nonVisible: Body = NonVisibleBody
}

case class NonEmptyBody(v: String) extends Body {
  override def map(f: F): Body = NonEmptyBody(f(v))

  override def sMap(sbf: SBF): Seq[Body] = sbf(this)
}

object EmptyBody extends Body {
  override def map(f: F): Body = this

  override def sMap(sbf: SBF): Seq[Body] = sbf(this)
}

object NonVisibleBody extends Body {
  override def map(f: F): Body = this

  override def sMap(sbf: SBF): Seq[Body] = sbf(this)
}
