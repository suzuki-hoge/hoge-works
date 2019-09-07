package line


case class Lines(ls: Seq[Line]) {
  def $: Int = ls.lastOption.map(_.n.v).getOrElse(1)

  def map(f: Line => Body): Lines = Lines.sequenceWith(ls.map(f))

  def sMap(sf: Line => Seq[Body]): Lines = Lines.sequenceWith(ls.flatMap(sf))

  def yank(range: LineRange): Seq[Body] = ls.flatMap(l => l.yank(range))

  def display: Seq[String] = ls.flatMap(_.display)
}

object Lines {
  def sequenceWith(bs: Seq[Body]): Lines = {
    val visibles = bs match {
      case Seq() => Seq(Line(LineNumber(1), Body.empty))
      case Seq(NonVisibleBody) => Seq(Line(LineNumber(1), Body.empty))
      case _ => bs
        .flatMap {
          case NonVisibleBody => None
          case EmptyBody => None
          case b => Some(b)
        }
        .zip(LazyList.from(1, 1))
        .map {
          case (b, n) => Line(LineNumber(n), b)
        }
    }
    Lines(Seq(Line(LineNumber(0), NonVisibleBody)) ++ visibles)
  }
}
