package usda.test

import usda.Weight
import usda.Size

import org.specs2.mutable.Specification

class WeightSpec extends Specification with DatabaseHarness {

  "Weight" should {
    "get the sizes for a Gov code" in {
      new Weight with MySQL {
        sizes("01123") === List(
          ("1", "large"),
          ("2", "extra large"),
          ("3", "jumbo"),
          ("5", "cup (4.86 large eggs)"),
          ("6", "medium"),
          ("7", "small")
        )
      }
      success
    }

    "get the Sequence number for a Gov code and a size" in {
      new Weight with MySQL {
        weightId("01123", "jumbo") must beSome("3")
      }
      success
    }

    "get the size for a Gov code and a Sequence number" in {
      new Weight with MySQL {
        size("01123", "3") must beSome(Size("3", 1.000, "jumbo", 63.00))
      }
      success
    }

    "get the weight for a Gov code and a Sequence number" in {
      new Weight with MySQL {
        grams("01123", "3") must beSome(63.0)
      }
      success
    }
  }
}
