import org.scalatest.flatspec.AnyFlatSpec

class Zad01FlatSpec extends AnyFlatSpec {

  "The ranking function" should """provide the expected result for the "test.txt" data file.""" in {
    val expectedResult = Map(
      1 -> Set((1,20)),
      2 -> Set((2,22)),
      3 -> Set((3,19)),
      4 -> Set((4,9)),
      5 -> Set((5,18), (5,12)),
      7 -> Set((7,2)),
      8 -> Set((8,23))
    )
    val result = ranking().groupBy(_._1).view.mapValues(_.toSet).toMap
    assert( expectedResult == result )
  }
}
