package usda

trait Units {

  abstract class Unit(n: BigDecimal) {
    def toBigDecimal = n
    def toInt = n.toInt
    def toLong = n.toLong
    def toFloat = n.toFloat
    def toDouble = n.toDouble
    override def toString = n.toString
  }

  abstract class Volume(n: BigDecimal) extends Unit(n: BigDecimal) {
    def toMilliliters: Milliliters
  }

  abstract class Mass(n: BigDecimal) extends Unit(n: BigDecimal) {
    def toGrams: Grams
  }

  class Cups(n: BigDecimal) extends Volume(n: BigDecimal) {
    def toTablespoons = new Tablespoons(n * 16)
    def toTeaspoons = toTablespoons.toTeaspoons
    def toMilliliters = toTeaspoons.toMilliliters
  }

  class Tablespoons(n: BigDecimal) extends Volume(n: BigDecimal) {
    def toCups = new Cups(n / 16)
    def toTeaspoons = new Teaspoons(n * 3)
    def toMilliliters = toTeaspoons.toMilliliters
  }

  class Teaspoons(n: BigDecimal) extends Volume(n: BigDecimal) {
    def toCups = toTablespoons.toCups
    def toTablespoons = new Tablespoons(n / 3)
    def toMilliliters = new Milliliters(n * 4.92892159375)
  }

  class Milliliters(n: BigDecimal) extends Volume(n: BigDecimal) {
    def toCups = toTablespoons.toCups
    def toTablespoons = toTeaspoons.toTablespoons
    def toTeaspoons = new Teaspoons(n / 4.92892159375)
    def toMilliliters = this
  }

  class Pounds(n: BigDecimal) extends Mass(n: BigDecimal) {
    def toOunces = new Ounces(n * 16)
    def toGrams = toOunces.toGrams
  }

  class Ounces(n: BigDecimal) extends Mass(n: BigDecimal) {
    def toGrams = new Grams(n * 28.349523125)
    def toPounds = new Pounds(n / 16)
  }

  class Grams(n: BigDecimal) extends Mass(n: BigDecimal) {
    def toOunces = new Ounces(n / 28.349523125)
    def toPounds = toOunces.toPounds
    def toGrams = this
  }

  class Density(mass: Mass, volume: Volume) {
    def toVolume(m: Mass) = new Milliliters(m.toGrams.toBigDecimal * volume.toMilliliters.toBigDecimal / mass.toGrams.toBigDecimal)
    def toCups(m: Mass) = toVolume(m).toCups
    def toTablespoons(m: Mass) = toVolume(m).toTablespoons
    def toTeaspoons(m: Mass) = toVolume(m).toTeaspoons
    def toMilliliters(m: Mass) = toVolume(m)

    def toMass(v: Volume) = new Grams(v.toMilliliters.toBigDecimal * mass.toGrams.toBigDecimal / volume.toMilliliters.toBigDecimal)
    def toPounds(v: Volume) = toMass(v).toPounds
    def toOunces(v: Volume) = toMass(v).toOunces
    def toGrams(v: Volume) = toMass(v)
  }

}
