package poker.safe

import poker.unsafe.PlayerHandFactory

object Battle {
  def apply(ws: String): Either[String, String] = {
    val ws1 = ws.split(" +").take(5).mkString(" ")
    val ws2 = ws.split(" +").drop(5).mkString(" ")

    for {
      h1 <- PlayerHandFactory.fromString(ws1)
      h2 <- PlayerHandFactory.fromString(ws2)
    } yield Seq((h1.judge._2, "first hand"), (h2.judge._2, "second hand")).maxBy(_._1.power)._2
  }
}
