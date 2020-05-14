package poker.safe

case class PlayerHand(cs: Seq[Card]) {
  def judge: (Hand, Card) = Seq(
    royalStraightFlush, straightFlush, fourOfAKind, fullHouse, flush, straight, threeOfAKind, twoPair, onePair, highCard
  ).flatten.head

  def highCard: Option[(Hand, Card)] = Some(HighCard, max)

  def onePair: Option[(Hand, Card)] = for {c <- nOfAKind(2, 1)} yield (OnePair, c)

  def twoPair: Option[(Hand, Card)] = for {c <- nOfAKind(2, 2)} yield (TwoPair, c)

  def threeOfAKind: Option[(Hand, Card)] = for {c <- nOfAKind(3, 1)} yield (ThreeOfAKind, c)

  def straight: Option[(Hand, Card)] = if (cs.map(_.n).sorted.map(_ - min.n) == (0 to 4)) Option(Straight, max) else None

  def flush: Option[(Hand, Card)] = if (cs.map(_.s).distinct.length == 1) Option(Flush, max) else None

  def fullHouse: Option[(Hand, Card)] = for {_ <- onePair; _ <- threeOfAKind} yield (FullHouse, max)

  def fourOfAKind: Option[(Hand, Card)] = for {c <- nOfAKind(4, 1)} yield (FourOfAKind, c)

  def straightFlush: Option[(Hand, Card)] = for {_ <- straight; _ <- flush} yield (StraightFlush, max)

  def royalStraightFlush: Option[(Hand, Card)] = for {_ <- straight; _ <- flush; _ <- Some(min).filter(_.n == 10)} yield (RoyalStraightFlush, max)

  private def min: Card = cs.minBy(_.power)

  private def max: Card = cs.maxBy(_.power)

  private def nOfAKind(dup: Int, found: Int): Option[Card] = {
    cs.groupBy(_.n).values.toSeq.filter(_.length == dup).map(_.maxBy(_.power)).sortBy(_.power).reverse match {
      case seq if seq.length == found => seq.headOption
      case _ => None
    }
  }
}
