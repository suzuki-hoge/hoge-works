import safe.Flow

import scala.io.StdIn

object Main extends App {
  print("current plan ( small / normal / large / mega ): ")
  val current = StdIn.readLine()

  print("user ( basic / premium ): ")
  val user = StdIn.readLine()

  print("next plan ( small / normal / large / mega ): ")
  val next = StdIn.readLine()

  println(Flow(current, user, next))
}
