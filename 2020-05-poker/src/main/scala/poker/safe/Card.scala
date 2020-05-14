package poker.safe

case class Card(s: Int, n: Int) {
  def power: Int = n * 10 + s
}
