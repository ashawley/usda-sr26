package usda.test

import org.specs2.mutable.Specification

object GroupBySpec extends Specification {
  "Tables" should {
    "be able to be grouped" in {
      Seq(
        (1, 2, 3),
        (1, 2, 4),
        (1, 2, 4),
        (1, 3, 4),
        (1, 3, 5),
        (2, 2, 3),
        (2, 2, 5),
        (2, 3, 6),
        (2, 2, 7)
      ).groupBy(_._1).values.map(xs => xs.head match {
          case (a, _, _) => Seq(a,
            xs.groupBy(_._2).values.map(_.head match {
              case (_, b, _) => b
            }),
            xs.groupBy(_._3).values.map(_.head match {
              case (_, _, c) => c
            }))
        }) === Seq(
          Seq(2,
            Seq(2, 3),
            Seq(5, 7, 3, 6)),
          Seq(1,
            Seq(2, 3),
            Seq(5, 4, 3)))
    }
  }
}
