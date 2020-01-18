
object Main extends App {
  val element = Div("#content .c1 .c2",
    Span("bla bla bla"),
    Div(".c1",
      Div(".c2",
        Span("pon pon pon")
      )
    ),
    Ul(".nav",
      Li(".nav-item", Span("#item1", "bla bla bla")),
      Li(".nav-item", Span("#item2", "pon pon pon"))
    )
  )
  val indent = Indent.zero

  println(
    element.show(indent)
  )
}
