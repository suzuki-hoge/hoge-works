package command

import line.{Body, Text}
import org.scalatest.FunSuite
import util.Helpers._

class CommandTest extends FunSuite {
  test("dispatch") {
    val last = 5

    assert(
      Command.dispatch("1/append/new line.", last).contains(Append($(1), Body.of("new line.")))
    )
    assert(
      Command.dispatch("1,$/d", last).contains(Delete($$(1, last)))
    )
    assert(
      Command.dispatch("2, 3/copy/$", last).contains(Copy($$(2, 3), $(last)))
    )
    assert(
      Command.dispatch("1/m/3", last).contains(Move($$(1, 1), $(3)))
    )
    assert(
      Command.dispatch("%/execute/^p.*/c p", last).contains(Execute($$(1, last), Text("^p.*"), Motions(Seq(Capitalize, Period))))
    )
    assert(
      Command.dispatch("1/s/src/dst", last).contains(Substitute($$(1, 1), Text("src"), Text("dst")))
    )
  }
}
