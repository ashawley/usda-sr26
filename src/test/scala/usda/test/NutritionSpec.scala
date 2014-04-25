package usda.test

import usda.Nutrition

import org.specs2.mutable.Specification

import usda.Nutrient

class NutritionSpec extends Specification with DatabaseHarness {

  "Nutrition" should {
    "get the nutrition for a Gov code" in {
      new Nutrition with MySQL {
        forFood("05082").head === Nutrient(
          "203",
          "Protein",
          BigDecimal("24.220"),
          "g"
        )
        forFood("05082")(4) === Nutrient(
          "208",
          "Energy",
          BigDecimal("174.0"),
          "kcal"
        )
      }
      success
    }

    "get the nutrients for a Gov code" in {
      new Nutrition with MySQL {
        nutrients("05082").take(5) === List("203", "204", "205", "207", "208")
      }
      success
    }

    "get the name of a nutrient" in {
      new Nutrition with MySQL {
        name("208") must beSome("Energy")
      }
      success
    }

    "get the units for a nutrient" in {
      new Nutrition with MySQL {
        units("208") must beSome("kcal")
      }
      success
    }

    "get the units for a nutrient" in {
      new Nutrition with MySQL {
        units("208") must beSome("kcal")
      }
      success
    }
  }
}
