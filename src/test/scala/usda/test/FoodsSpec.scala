package usda.test

import usda.Foods

import usda.Food
import usda.Nutrient
import usda.Size

import org.specs2.mutable.Specification

class FoodsSpec extends Specification with DatabaseHarness {

  "Foods" should {
    "find the Gov code from a description" in {
      new Foods with MySQL {
        ndbNo("Chicken, broilers or fryers, leg, meat only, cooked, roasted") must beSome("05082")
      }
      success
    }

    "get the food group code for a Gov code" in {
      new Foods with MySQL {
        code("01123") must beSome("0100")
      }
      success
    }

    "get the food group name for a group code" in {
      new Foods with MySQL {
        groupName("0100") must beSome("Dairy and Egg Products")
      }
      success
    }

    "get the food group name for a Gov code" in {
      new Foods with MySQL {
        group("01123") must beSome("Dairy and Egg Products")
      }
      success
    }

    "starting with the 1 letter" in {
      new Foods with MySQL {
        startsWith("A", 2) === List("Abiyuch, raw", "Acerola juice, raw")
      }
      success
    }

    "starting with the 2 letters" in {
      new Foods with MySQL {
        startsWith("Ap", 2) === List(
          "Apple juice, canned or bottled, unsweetened, with added ascorbic acid",
          "Apple juice, canned or bottled, unsweetened, without added ascorbic acid"
        )
      }
      success
    }

    "starting with the 9 letters" in {
      new Foods with MySQL {
        startsWith("Apples, r", 2) === List(
          "Apples, raw, fuji, with skin",
          "Apples, raw, gala, with skin"
        )
      }
      success
    }

    "list all foods" in {
      new Foods with MySQL {
        detail(10, 20).length === 20
        detail(0, 1) === Seq(
          Food("01001", "Butter, salted", "",
            Seq(
              Size("0", 100.0, "g", 1.0),
              Size("1", 1.000, "pat (1\" sq, 1/3\" high)", 5.00),
              Size("2", 1.000, "tbsp", 14.20),
              Size("3", 1.000, "cup", 227.00),
              Size("4", 1.000, "stick", 113.00)
            ),
            Seq(
              Nutrient("601", "Cholesterol", 215.000, "mg"),
              Nutrient("324", "Vitamin D", 60.000, "IU"),
              Nutrient("323", "Vitamin E (alpha-tocopherol)", 2.320, "mg"),
              Nutrient("303", "Iron, Fe", 0.020, "mg"),
              Nutrient("205", "Carbohydrate, by difference", 0.060, "g"),
              Nutrient("645", "Fatty acids, total monounsaturated", 21.021, "g"),
              Nutrient("401", "Vitamin C, total ascorbic acid", 0.000, "mg"),
              Nutrient("415", "Vitamin B-6", 0.003, "mg"),
              Nutrient("204", "Total lipid (fat)", 81.110, "g"),
              Nutrient("269", "Sugars, total", 0.060, "g"),
              Nutrient("320", "Vitamin A, RAE", 684.000, "µg"),
              Nutrient("208", "Energy", 717.000, "kcal"),
              Nutrient("309", "Zinc, Zn", 0.090, "mg"),
              Nutrient("306", "Potassium, K", 24.000, "mg"),
              Nutrient("417", "Folate, total", 3.000, "µg"),
              Nutrient("432", "Folate, food", 3.000, "µg"),
              Nutrient("418", "Vitamin B-12", 0.170, "µg"),
              Nutrient("307", "Sodium, Na", 643.000, "mg"),
              Nutrient("304", "Magnesium, Mg", 2.000, "mg"),
              Nutrient("646", "Fatty acids, total polyunsaturated", 3.043, "g"),
              Nutrient("606", "Fatty acids, total saturated", 51.368, "g"),
              Nutrient("605", "Fatty acids, total trans", 3.278, "g"),
              Nutrient("301", "Calcium, Ca", 24.000, "mg"),
              Nutrient("203", "Protein", 0.850, "g"),
              Nutrient("291", "Fiber, total dietary", 0.000, "g")
            )
          )
        )
      }
      success
    }

    "list one food" in {
      new Foods with MySQL {
        forNdbNo("15209") === Some(
          Food("15209", "Fish, salmon, Atlantic, wild, cooked, dry heat", "",
            Seq(
              Size("0", 100.00, "g", 1.00),
              Size("1", 3.000, "oz", 85.00),
              Size("2", 0.500, "fillet", 154.00)
            ),
            Seq(
              Nutrient("309", "Zinc, Zn", 0.820, "mg"),
              Nutrient("204", "Total lipid (fat)", 8.130, "g"),
              Nutrient("303", "Iron, Fe", 1.030, "mg"),
              Nutrient("601", "Cholesterol", 71.000, "mg"),
              Nutrient("304", "Magnesium, Mg", 37.000, "mg"),
              Nutrient("203", "Protein", 25.440, "g"),
              Nutrient("320", "Vitamin A, RAE", 13.000, "µg"),
              Nutrient("401", "Vitamin C, total ascorbic acid", 0.000, "mg"),
              Nutrient("301", "Calcium, Ca", 15.000, "mg"),
              Nutrient("415", "Vitamin B-6", 0.944, "mg"),
              Nutrient("646", "Fatty acids, total polyunsaturated", 3.256, "g"),
              Nutrient("208", "Energy", 182.000, "kcal"),
              Nutrient("307", "Sodium, Na", 56.000, "mg"),
              Nutrient("306", "Potassium, K", 628.000, "mg"),
              Nutrient("432", "Folate, food", 29.000, "µg"),
              Nutrient("606", "Fatty acids, total saturated", 1.257, "g"),
              Nutrient("205", "Carbohydrate, by difference", 0.000, "g"),
              Nutrient("645", "Fatty acids, total monounsaturated", 2.697, "g"),
              Nutrient("417", "Folate, total", 29.000, "µg"),
              Nutrient("291", "Fiber, total dietary", 0.000, "g"),
              Nutrient("418", "Vitamin B-12", 3.050, "µg")
            )
          )
        )
      }
      success
    }
  }
}
