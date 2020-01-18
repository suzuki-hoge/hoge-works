import safe.Flow

import scala.io.StdIn

object Main extends App {
  print("plan ( small / normal / large / mega ): ")
  val plan = StdIn.readLine()

  print("user ( basic / premium ): ")
  val user = StdIn.readLine()

  println(Flow(plan, user))
}
