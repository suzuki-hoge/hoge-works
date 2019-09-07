package command

import org.scalatest.FunSuite
import util.Helpers._

class UndoTest extends FunSuite {
  val last = 5
  val undoCommand = Some(DeletionUndo($$(1, 2)))

  test("parse") {
    assert(
      Undo.parse("undo", undoCommand).contains(Undo)
    )
    assert(
      Undo.parse("u", undoCommand).contains(Undo)
    )
  }

  test("parse (no match)") {
    assert(
      Undo.parse("undo", None).isEmpty
    )
    assert(
      Undo.parse("1/undo", undoCommand).isEmpty
    )
  }
}
