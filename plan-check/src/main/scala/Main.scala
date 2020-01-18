import safe.Flow

import scala.io.StdIn

object Main extends App {
  print("plan ( small / normal / large ): ")
  val in = StdIn.readLine()

  println(Flow(in))
}
