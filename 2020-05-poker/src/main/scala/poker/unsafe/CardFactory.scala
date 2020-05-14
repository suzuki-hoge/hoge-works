package poker.unsafe

import poker.safe.Card

object CardFactory {
  def fromString(w: String): Either[String, Card] = {
    val ws = w.split("-")

    for {
      s <- ws(0) match {
        case "S" => Right(4)
        case "H" => Right(3)
        case "D" => Right(2)
        case "C" => Right(1)
        case _ => Left("no suit format")
      }
      n <- ws(1) match {
        case _n if (2 to 10).map(_.toString).contains(_n) => Right(_n.toInt)
        case "J" => Right(11)
        case "Q" => Right(12)
        case "K" => Right(13)
        case "A" => Right(14)
        case _ => Left("no number format")
      }
    } yield Card(s, n.toInt)
  }
}
