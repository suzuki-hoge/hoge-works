import unsafe.{Parser, Processor}

object Main extends App {
  run("/tmp/work")
  run("/tmp/work -type a")
  run("/tmp/work -type d")
  run("/tmp/work -type f")
  run("/tmp/work -d")
  run("/tmp/woook")
  run("/tmp/work/conf/subs/options.conf")

  private def run(line: String): Unit = println(
    (for {
      x <- Parser(line).right
      x2 <- Processor(x._1, x._2)
    } yield x2).fold(e => e, r => r.show())
  )
}
