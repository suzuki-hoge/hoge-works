package util

import line.{Body, LineNumber, LineRange, Lines}

object Helpers {
  def $(i: Int): LineNumber = LineNumber(i)

  def $$(s: Int, e: Int): LineRange = LineRange($(s), $(e))

  def $$$(ss: String*): Lines = Lines.sequenceWith(ss.toSeq.map(Body.of))
}
