import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class MyTest extends AnyFlatSpec with ScalaCheckPropertyChecks {

  it should "work" in {
    implicit val arbString = Arbitrary(Gen.alphaStr)
    implicit val arb: Arbitrary[Foo] =  Arbitrary(Gen.resultOf(Foo))

    forAll {f: Foo =>
      println(f)

      f.a shouldBe "1"
    }
  }

}


case class Foo(a: String, b: Int)
