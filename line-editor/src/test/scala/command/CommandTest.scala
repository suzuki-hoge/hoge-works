package command

import line.{Body, Text}
import org.scalatest.FunSuite
import util.Helpers._

class CommandTest extends FunSuite {
  test("parse line command") {
    val last = 5

    assert(
      LineCommand.parse("1/append/new line.", last).contains(Append($(1), Body.of("new line.")))
    )
    assert(
      LineCommand.parse("1,$/d", last).contains(Delete($$(1, last)))
    )
    assert(
      LineCommand.parse("2, 3/copy/$", last).contains(Copy($$(2, 3), $(last)))
    )
    assert(
      LineCommand.parse("1/m/3", last).contains(Move($$(1, 1), $(3)))
    )
    assert(
      LineCommand.parse("%/execute/^p.*/c p", last).contains(Execute($$(1, last), Text("^p.*"), Motions(Seq(Capitalize, Period))))
    )
    assert(
      LineCommand.parse("1/s/src/dst", last).contains(Substitute($$(1, 1), Text("src"), Text("dst")))
    )
  }

  test("parse editor command") {
    assert(
      EditorCommand.parse("undo", Some(DeletionUndo($$(1, 2)))).contains(Undo)
    )
    assert(
      EditorCommand.parse("write", None).contains(Write)
    )
  }
}
