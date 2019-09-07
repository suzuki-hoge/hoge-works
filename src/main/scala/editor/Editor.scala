package editor

import java.io.PrintWriter

import command._
import line.{Body, Lines, NonEmptyBody}

import scala.io.{Source, StdIn}

object Main extends App {
  val editor = Editor.boot("/tmp/a.txt")

  editor.action()
}

case class Editor(path: String, var ls: Lines, var undoCommand: Option[UndoCommand]) {

  def action(): Unit = {
    print("\n")

    ls.display.foreach(println)

    input match {
      case InputtedLineCommand(lc) =>
        undoCommand = Some(lc.undo(ls))
        ls = lc.execute(ls)

        action()

      case InputtedEditorCommand(ec) => ec match {
        case Undo =>
          ls = undoCommand.get.execute(ls)
          undoCommand = None

          action()

        case Write => end()
      }
    }
  }

  private def input: InputtedCommand = {
    val inputted = StdIn.readLine("> ")

    InputtedCommand.eitherOr(
      LineCommand.parse(inputted, ls.$),
      EditorCommand.parse(inputted, undoCommand)
    ) match {
      case None => input
      case Some(ic) => ic
    }
  }

  private def end(): Unit = {
    val file = new PrintWriter(path)

    ls.ls.map(_.b).flatMap {
      case NonEmptyBody(v) => Some(v + "\n")
      case _ => None
    }.foreach(file.write)

    file.close()
  }
}

object Editor {
  def boot(path: String): Editor = {
    val source = Source.fromFile(path)

    val lines = Lines.sequenceWith(
      source.getLines.toSeq.map(Body.of)
    )

    source.close()

    Editor(path, lines, None)
  }
}

sealed trait InputtedCommand

case class InputtedLineCommand(lc: LineCommand) extends InputtedCommand

case class InputtedEditorCommand(ec: EditorCommand) extends InputtedCommand

object InputtedCommand {
  def eitherOr(olc: Option[LineCommand], oec: Option[EditorCommand]): Option[InputtedCommand] = (olc, oec) match {
    case (Some(lc), None) => Some(InputtedLineCommand(lc))
    case (None, Some(ec)) => Some(InputtedEditorCommand(ec))
    case _ => None
  }
}
