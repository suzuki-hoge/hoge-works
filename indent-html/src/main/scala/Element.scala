trait Element {
  def show(indent: Indent): String
}

trait BlockElement extends Element

trait InlineElement extends Element

case class Attr(id: Option[Id], classes: Seq[Class]) {
  override def toString: String = (id, classes) match {
    case (None, Seq()) => ""
    case (Some(x), Seq()) => s""" id="${x.v}""""
    case (None, xs) => s""" class="${xs.map(_.v).mkString(" ")}""""
    case (Some(x), xs) => s""" id="${x.v}" class="${xs.map(_.v).mkString(" ")}""""
  }
}

object Attr {
  def apply(attr: String): Attr = Attr(
    attr.split(" ").filter(_.startsWith("#")).map(_.replace("#", "")).map(Id).headOption,
    attr.split(" ").filter(_.startsWith(".")).map(_.replace(".", "")).map(Class)
  )
}

case class Id(v: String)

case class Class(v: String)

case class Div(attr: Attr, subs: Seq[Element]) extends BlockElement {
  override def show(indent: Indent): String =
    s"""$indent<div$attr>
       |${subs.map(_.show(indent + 1)).mkString("\n")}
       |$indent</div>""".stripMargin
}

object Div {
  def apply(attr: String, subs: Element*): Div = Div(Attr(attr), subs.toSeq)

  def apply(subs: Element*): Div = apply("", subs: _*)
}

case class Span(attr: Attr, text: String) extends InlineElement {
  override def show(indent: Indent): String = s"$indent<span$attr>$text</span>"
}

object Span {
  def apply(attr: String, text: String): Span = Span(Attr(attr), text)

  def apply(text: String): Span = apply("", text)
}

case class Ul(attr: Attr, first: Li, remains: Seq[Li]) extends BlockElement {
  override def show(indent: Indent): String =
    s"""$indent<ul$attr>
       |${first.show(indent + 1)}
       |${remains.map(_.show(indent + 1)).mkString("\n")}
       |$indent</ul>""".stripMargin
}

object Ul {
  def apply(attr: String, first: Li, remains: Li*): Ul = Ul(Attr(attr), first, remains.toSeq)

  def apply(first: Li, remains: Li*): Ul = apply("", first, remains: _*)
}

case class Li(attr: Attr, subs: Seq[Element]) extends BlockElement {
  override def show(indent: Indent): String =
    s"""$indent<li$attr>
       |${subs.map(_.show(indent + 1)).mkString("\n")}
       |$indent</li>""".stripMargin
}

object Li {
  def apply(attr: String, subs: Element*): Li = Li(Attr(attr), subs.toSeq)

  def apply(subs: Element*): Li = apply("", subs: _*)
}
