package line

case class Text(v: String) {
  def asBody: Body = Body.of(v)
}

object Text {
  def parse(s: String): Option[Text] = s match {
    case _s if _s.matches("""[^/]*""") => Some(Text(s.replaceFirst("^\"", "").replaceFirst("\"$", "")))
    case _ => None
  }
}
