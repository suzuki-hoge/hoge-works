package poker.unsafe

import poker.safe._

object PlayerHandFactory {
  def fromString(ws: String): Either[String, PlayerHand] = {
    val cs = ws.split(" +").map(CardFactory.fromString)
    val errors = for (Left(c) <- cs) yield c

    (if (errors.isEmpty) Right(for (Right(c) <- cs) yield c) else Left(errors)) match {
      case Right(_cs) if _cs.length != 5 => Left("must be 5 cards")
      case Right(_cs) if _cs.distinct.length != 5 => Left("dup cards")
      case Right(_cs) => Right(PlayerHand(_cs))
      case Left(es) => Left(es.mkString(", "))
    }
  }
}
