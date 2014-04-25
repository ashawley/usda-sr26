package usda.test

import usda.Units

import org.specs2.mutable.Specification

class UnitsSpec extends Specification {

  "3 cups" should {
    "be equal to 48 tablespoons" in {
      new Units {
        new Cups(3).toTablespoons.toInt === 48
      }
      success
    }
  }

  "A quarter cup" should {
    "be equal to 12 teaspoons" in {
      new Units {
        new Cups(0.25).toTeaspoons.toInt === 12
      }
      success
    }
  }

  "4 tablespoons" should {
    "be equal to a quarter cup" in {
      new Units {
        new Tablespoons(4).toCups.toDouble === 0.25
      }
      success
    }
  }

  "3 tablespoons" should {
    "be equal to a 9 teaspoons" in {
      new Units {
        new Tablespoons(3).toTeaspoons.toInt === 9
      }
      success
    }
  }

  "6 teaspoons" should {
    "be equal to 2 tablespoons" in {
      new Units {
        new Teaspoons(6).toTablespoons.toInt === 2
      }
      success
    }
  }

  "32 teaspoons" should {
    "be equal to a third of a cup" in {
      new Units {
        new Teaspoons(32).toCups.toDouble === (2.toDouble / 3)
      }
      success
    }
  }

  "236.5882365 milliliters " should {
    "be equal to a cup" in {
      new Units {
        new Milliliters(236.5882365).toCups.toDouble === 1.0
      }
      success
    }
  }

  "12 ounces" should {
    "be equal to three-quarters of a pound" in {
      new Units {
        new Ounces(12).toPounds.toDouble === 0.75
      }
      success
    }
  }

  "17.63698097 ounces" should {
    "be equal to half a kilogram" in {
      new Units {
        new Ounces(17.63698097).toGrams.toFloat === 500.0
      }
      success
    }
  }

  "2.5 pounds" should {
    "be equal to 40 ounces" in {
      new Units {
        new Pounds(2.5).toOunces.toInt === 40
      }
      success
    }
  }

  "1000 milliliters of water" should {
    "be equal to 1000 grams" in {
      new Units {
        new Density(new Grams(1000), new Milliliters(1000)).toGrams(new Milliliters(1000)).toDouble === 1000
      }
      success
    }
  }

  "1 cup of water" should {
    "be equal to 236.58824 grams" in {
      new Units {
        new Density(new Grams(1000), new Milliliters(1000)).toGrams(new Cups(1)).toFloat === 236.58824.toFloat
      }
      success
    }
  }

  "2 cooked chicken legs without skin" should {
    "be equal to 14 ounces" in {
      new Units {
        new Grams(2 * 199).toOunces.toFloat === 14.039037.toFloat
      }
      success
    }
  }

}
