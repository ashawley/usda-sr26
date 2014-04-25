package usda
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = scala.slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: scala.slick.driver.JdbcProfile
  import profile.simple._
  import scala.slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import scala.slick.jdbc.{ GetResult => GR }

  /** DDL for all tables. Call .create to execute. */
  lazy val ddl = DataSrc.ddl ++ DerivCd.ddl ++ FdGroup.ddl ++ FoodDes.ddl ++ Footnote.ddl ++ Langdesc.ddl ++ Langual.ddl ++ NutData.ddl ++ NutrDef.ddl ++ SrcCd.ddl ++ Weight.ddl

  /**
   * Entity class storing rows of table DataSrc
   *  @param datasrcId Database column DataSrc_ID
   *  @param authors Database column Authors
   *  @param title Database column Title
   *  @param year Database column Year
   *  @param journal Database column Journal
   *  @param volCity Database column Vol_City
   *  @param issueState Database column Issue_State
   *  @param startPage Database column Start_Page
   *  @param endPage Database column End_Page
   */
  case class DataSrcRow(datasrcId: String, authors: String, title: String, year: String, journal: String, volCity: String, issueState: String, startPage: String, endPage: String)
  /** GetResult implicit for fetching DataSrcRow objects using plain SQL queries */
  implicit def GetResultDataSrcRow(implicit e0: GR[String]): GR[DataSrcRow] = GR {
    prs =>
      import prs._
      DataSrcRow.tupled((<<[String], <<[String], <<[String], <<[String], <<[String], <<[String], <<[String], <<[String], <<[String]))
  }
  /** Table description of table DATA_SRC. Objects of this class serve as prototypes for rows in queries. */
  class DataSrc(tag: Tag) extends Table[DataSrcRow](tag, "DATA_SRC") {
    def * = (datasrcId, authors, title, year, journal, volCity, issueState, startPage, endPage) <> (DataSrcRow.tupled, DataSrcRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (datasrcId.?, authors.?, title.?, year.?, journal.?, volCity.?, issueState.?, startPage.?, endPage.?).shaped.<>({ r => import r._; _1.map(_ => DataSrcRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column DataSrc_ID  */
    val datasrcId: Column[String] = column[String]("DataSrc_ID")
    /** Database column Authors  */
    val authors: Column[String] = column[String]("Authors")
    /** Database column Title  */
    val title: Column[String] = column[String]("Title")
    /** Database column Year  */
    val year: Column[String] = column[String]("Year")
    /** Database column Journal  */
    val journal: Column[String] = column[String]("Journal")
    /** Database column Vol_City  */
    val volCity: Column[String] = column[String]("Vol_City")
    /** Database column Issue_State  */
    val issueState: Column[String] = column[String]("Issue_State")
    /** Database column Start_Page  */
    val startPage: Column[String] = column[String]("Start_Page")
    /** Database column End_Page  */
    val endPage: Column[String] = column[String]("End_Page")
  }
  /** Collection-like TableQuery object for table DataSrc */
  lazy val DataSrc = new TableQuery(tag => new DataSrc(tag))

  /**
   * Entity class storing rows of table DerivCd
   *  @param derivCd Database column Deriv_Cd
   *  @param derivDesc Database column Deriv_Desc
   */
  case class DerivCdRow(derivCd: String, derivDesc: String)
  /** GetResult implicit for fetching DerivCdRow objects using plain SQL queries */
  implicit def GetResultDerivCdRow(implicit e0: GR[String]): GR[DerivCdRow] = GR {
    prs =>
      import prs._
      DerivCdRow.tupled((<<[String], <<[String]))
  }
  /** Table description of table DERIV_CD. Objects of this class serve as prototypes for rows in queries. */
  class DerivCd(tag: Tag) extends Table[DerivCdRow](tag, "DERIV_CD") {
    def * = (derivCd, derivDesc) <> (DerivCdRow.tupled, DerivCdRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (derivCd.?, derivDesc.?).shaped.<>({ r => import r._; _1.map(_ => DerivCdRow.tupled((_1.get, _2.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column Deriv_Cd  */
    val derivCd: Column[String] = column[String]("Deriv_Cd")
    /** Database column Deriv_Desc  */
    val derivDesc: Column[String] = column[String]("Deriv_Desc")
  }
  /** Collection-like TableQuery object for table DerivCd */
  lazy val DerivCd = new TableQuery(tag => new DerivCd(tag))

  /**
   * Entity class storing rows of table FdGroup
   *  @param fdgrpCd Database column FdGrp_Cd
   *  @param fdgrpDesc Database column FdGrp_Desc
   */
  case class FdGroupRow(fdgrpCd: String, fdgrpDesc: String)
  /** GetResult implicit for fetching FdGroupRow objects using plain SQL queries */
  implicit def GetResultFdGroupRow(implicit e0: GR[String]): GR[FdGroupRow] = GR {
    prs =>
      import prs._
      FdGroupRow.tupled((<<[String], <<[String]))
  }
  /** Table description of table FD_GROUP. Objects of this class serve as prototypes for rows in queries. */
  class FdGroup(tag: Tag) extends Table[FdGroupRow](tag, "FD_GROUP") {
    def * = (fdgrpCd, fdgrpDesc) <> (FdGroupRow.tupled, FdGroupRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (fdgrpCd.?, fdgrpDesc.?).shaped.<>({ r => import r._; _1.map(_ => FdGroupRow.tupled((_1.get, _2.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column FdGrp_Cd  */
    val fdgrpCd: Column[String] = column[String]("FdGrp_Cd")
    /** Database column FdGrp_Desc  */
    val fdgrpDesc: Column[String] = column[String]("FdGrp_Desc")

    /** Index over (fdgrpCd) (database name FD_GROUP__FdGrp_Cd_idx) */
    val index = primaryKey("FD_GROUP__FdGrp_Cd_idx", fdgrpCd)
  }
  /** Collection-like TableQuery object for table FdGroup */
  lazy val FdGroup = new TableQuery(tag => new FdGroup(tag))

  /**
   * Entity class storing rows of table FoodDes
   *  @param ndbNo Database column NDB_No
   *  @param fdgrpCd Database column FdGrp_Cd
   *  @param longDesc Database column Long_Desc
   *  @param shrtDesc Database column Shrt_Desc
   *  @param comname Database column ComName
   *  @param manufacname Database column ManufacName
   *  @param survey Database column Survey
   *  @param refDesc Database column Ref_desc
   *  @param refuse Database column Refuse Default(0)
   *  @param sciname Database column SciName
   *  @param nFactor Database column N_Factor
   *  @param proFactor Database column Pro_Factor
   *  @param fatFactor Database column Fat_Factor
   *  @param choFactor Database column CHO_Factor
   */
  case class FoodDesRow(ndbNo: String, fdgrpCd: String, longDesc: String, shrtDesc: String, comname: String, manufacname: String, survey: String, refDesc: String, refuse: Int = 0, sciname: String, nFactor: scala.math.BigDecimal, proFactor: scala.math.BigDecimal, fatFactor: scala.math.BigDecimal, choFactor: scala.math.BigDecimal)
  /** GetResult implicit for fetching FoodDesRow objects using plain SQL queries */
  implicit def GetResultFoodDesRow(implicit e0: GR[String], e1: GR[Int], e2: GR[scala.math.BigDecimal]): GR[FoodDesRow] = GR {
    prs =>
      import prs._
      FoodDesRow.tupled((<<[String], <<[String], <<[String], <<[String], <<[String], <<[String], <<[String], <<[String], <<[Int], <<[String], <<[scala.math.BigDecimal], <<[scala.math.BigDecimal], <<[scala.math.BigDecimal], <<[scala.math.BigDecimal]))
  }
  /** Table description of table FOOD_DES. Objects of this class serve as prototypes for rows in queries. */

  class FoodDes(tag: Tag) extends Table[FoodDesRow](tag, "FOOD_DES") {
    def * = (ndbNo, fdgrpCd, longDesc, shrtDesc, comname, manufacname, survey, refDesc, refuse, sciname, nFactor, proFactor, fatFactor, choFactor) <> (FoodDesRow.tupled, FoodDesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (ndbNo.?, fdgrpCd.?, longDesc.?, shrtDesc.?, comname.?, manufacname.?, survey.?, refDesc.?, refuse.?, sciname.?, nFactor.?, proFactor.?, fatFactor.?, choFactor.?).shaped.<>({ r => import r._; _1.map(_ => FoodDesRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11.get, _12.get, _13.get, _14.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column NDB_No  */
    val ndbNo: Column[String] = column[String]("NDB_No")
    /** Database column FdGrp_Cd  */
    val fdgrpCd: Column[String] = column[String]("FdGrp_Cd")
    /** Database column Long_Desc  */
    val longDesc: Column[String] = column[String]("Long_Desc")
    /** Database column Shrt_Desc  */
    val shrtDesc: Column[String] = column[String]("Shrt_Desc")
    /** Database column ComName  */
    val comname: Column[String] = column[String]("ComName")
    /** Database column ManufacName  */
    val manufacname: Column[String] = column[String]("ManufacName")
    /** Database column Survey  */
    val survey: Column[String] = column[String]("Survey")
    /** Database column Ref_desc  */
    val refDesc: Column[String] = column[String]("Ref_desc")
    /** Database column Refuse Default(0) */
    val refuse: Column[Int] = column[Int]("Refuse", O.Default(0))
    /** Database column SciName  */
    val sciname: Column[String] = column[String]("SciName")
    /** Database column N_Factor  */
    val nFactor: Column[scala.math.BigDecimal] = column[scala.math.BigDecimal]("N_Factor")
    /** Database column Pro_Factor  */
    val proFactor: Column[scala.math.BigDecimal] = column[scala.math.BigDecimal]("Pro_Factor")
    /** Database column Fat_Factor  */
    val fatFactor: Column[scala.math.BigDecimal] = column[scala.math.BigDecimal]("Fat_Factor")
    /** Database column CHO_Factor  */
    val choFactor: Column[scala.math.BigDecimal] = column[scala.math.BigDecimal]("CHO_Factor")

    /** Foreign key (fdgrpCd) (database name FOOD_DES__FD_GROUP__fdGroup_fk) */
    val group = foreignKey("FOOD_DES__FD_GROUP__fdGroup_fk", fdgrpCd, FdGroup)(_.fdgrpCd)
    /** Index over (longDesc) (database name FOOD_DES__Long_Desc_idx) */
    val index1 = index("FOOD_DES__Long_Desc_idx", longDesc)

  }
  /** Collection-like TableQuery object for table FoodDes */
  lazy val FoodDes = new TableQuery(tag => new FoodDes(tag))

  /**
   * Entity class storing rows of table Footnote
   *  @param ndbNo Database column NDB_No
   *  @param footntNo Database column Footnt_No
   *  @param footntTyp Database column Footnt_Typ
   *  @param nutrNo Database column Nutr_No
   *  @param footntTxt Database column Footnt_Txt
   */
  case class FootnoteRow(ndbNo: String, footntNo: String, footntTyp: String, nutrNo: String, footntTxt: String)
  /** GetResult implicit for fetching FootnoteRow objects using plain SQL queries */
  implicit def GetResultFootnoteRow(implicit e0: GR[String]): GR[FootnoteRow] = GR {
    prs =>
      import prs._
      FootnoteRow.tupled((<<[String], <<[String], <<[String], <<[String], <<[String]))
  }
  /** Table description of table FOOTNOTE. Objects of this class serve as prototypes for rows in queries. */
  class Footnote(tag: Tag) extends Table[FootnoteRow](tag, "FOOTNOTE") {
    def * = (ndbNo, footntNo, footntTyp, nutrNo, footntTxt) <> (FootnoteRow.tupled, FootnoteRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (ndbNo.?, footntNo.?, footntTyp.?, nutrNo.?, footntTxt.?).shaped.<>({ r => import r._; _1.map(_ => FootnoteRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column NDB_No  */
    val ndbNo: Column[String] = column[String]("NDB_No")
    /** Database column Footnt_No  */
    val footntNo: Column[String] = column[String]("Footnt_No")
    /** Database column Footnt_Typ  */
    val footntTyp: Column[String] = column[String]("Footnt_Typ")
    /** Database column Nutr_No  */
    val nutrNo: Column[String] = column[String]("Nutr_No")
    /** Database column Footnt_Txt  */
    val footntTxt: Column[String] = column[String]("Footnt_Txt")
  }
  /** Collection-like TableQuery object for table Footnote */
  lazy val Footnote = new TableQuery(tag => new Footnote(tag))

  /**
   * Entity class storing rows of table Langdesc
   *  @param factorCode Database column Factor_Code
   *  @param description Database column Description
   */
  case class LangdescRow(factorCode: String, description: String)
  /** GetResult implicit for fetching LangdescRow objects using plain SQL queries */
  implicit def GetResultLangdescRow(implicit e0: GR[String]): GR[LangdescRow] = GR {
    prs =>
      import prs._
      LangdescRow.tupled((<<[String], <<[String]))
  }
  /** Table description of table LANGDESC. Objects of this class serve as prototypes for rows in queries. */
  class Langdesc(tag: Tag) extends Table[LangdescRow](tag, "LANGDESC") {
    def * = (factorCode, description) <> (LangdescRow.tupled, LangdescRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (factorCode.?, description.?).shaped.<>({ r => import r._; _1.map(_ => LangdescRow.tupled((_1.get, _2.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column Factor_Code  */
    val factorCode: Column[String] = column[String]("Factor_Code")
    /** Database column Description  */
    val description: Column[String] = column[String]("Description")
  }
  /** Collection-like TableQuery object for table Langdesc */
  lazy val Langdesc = new TableQuery(tag => new Langdesc(tag))

  /**
   * Entity class storing rows of table Langual
   *  @param ndbNo Database column NDB_No
   *  @param factorCode Database column Factor_Code
   */
  case class LangualRow(ndbNo: String, factorCode: String)
  /** GetResult implicit for fetching LangualRow objects using plain SQL queries */
  implicit def GetResultLangualRow(implicit e0: GR[String]): GR[LangualRow] = GR {
    prs =>
      import prs._
      LangualRow.tupled((<<[String], <<[String]))
  }
  /** Table description of table LANGUAL. Objects of this class serve as prototypes for rows in queries. */
  class Langual(tag: Tag) extends Table[LangualRow](tag, "LANGUAL") {
    def * = (ndbNo, factorCode) <> (LangualRow.tupled, LangualRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (ndbNo.?, factorCode.?).shaped.<>({ r => import r._; _1.map(_ => LangualRow.tupled((_1.get, _2.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column NDB_No  */
    val ndbNo: Column[String] = column[String]("NDB_No")
    /** Database column Factor_Code  */
    val factorCode: Column[String] = column[String]("Factor_Code")
  }
  /** Collection-like TableQuery object for table Langual */
  lazy val Langual = new TableQuery(tag => new Langual(tag))

  /**
   * Entity class storing rows of table NutData
   *  @param ndbNo Database column NDB_No
   *  @param nutrNo Database column Nutr_No
   *  @param nutrVal Database column Nutr_Val
   *  @param numDataPts Database column Num_Data_Pts
   *  @param stdError Database column Std_Error
   *  @param srcCd Database column Src_Cd
   *  @param derivCd Database column Deriv_Cd
   *  @param refNdbNo Database column Ref_NDB_No
   *  @param addNutrMark Database column Add_Nutr_Mark
   *  @param numStudies Database column Num_Studies Default(0)
   *  @param min Database column Min
   *  @param max Database column Max
   *  @param df Database column DF Default(0)
   *  @param lowEb Database column Low_EB
   *  @param upEb Database column Up_EB
   *  @param statCmt Database column Stat_cmt
   *  @param addmodDate Database column AddMod_Date
   *  @param cc Database column CC
   */
  case class NutDataRow(ndbNo: String, nutrNo: String, nutrVal: scala.math.BigDecimal, numDataPts: scala.math.BigDecimal, stdError: scala.math.BigDecimal, srcCd: String, derivCd: String, refNdbNo: String, addNutrMark: String, numStudies: Int = 0, min: scala.math.BigDecimal, max: scala.math.BigDecimal, df: Int = 0, lowEb: scala.math.BigDecimal, upEb: scala.math.BigDecimal, statCmt: String, addmodDate: String, cc: String)
  /** GetResult implicit for fetching NutDataRow objects using plain SQL queries */
  implicit def GetResultNutDataRow(implicit e0: GR[String], e1: GR[scala.math.BigDecimal], e2: GR[Int]): GR[NutDataRow] = GR {
    prs =>
      import prs._
      NutDataRow.tupled((<<[String], <<[String], <<[scala.math.BigDecimal], <<[scala.math.BigDecimal], <<[scala.math.BigDecimal], <<[String], <<[String], <<[String], <<[String], <<[Int], <<[scala.math.BigDecimal], <<[scala.math.BigDecimal], <<[Int], <<[scala.math.BigDecimal], <<[scala.math.BigDecimal], <<[String], <<[String], <<[String]))
  }
  /** Table description of table NUT_DATA. Objects of this class serve as prototypes for rows in queries. */
  class NutData(tag: Tag) extends Table[NutDataRow](tag, "NUT_DATA") {
    def * = (ndbNo, nutrNo, nutrVal, numDataPts, stdError, srcCd, derivCd, refNdbNo, addNutrMark, numStudies, min, max, df, lowEb, upEb, statCmt, addmodDate, cc) <> (NutDataRow.tupled, NutDataRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (ndbNo.?, nutrNo.?, nutrVal.?, numDataPts.?, stdError.?, srcCd.?, derivCd.?, refNdbNo.?, addNutrMark.?, numStudies.?, min.?, max.?, df.?, lowEb.?, upEb.?, statCmt.?, addmodDate.?, cc.?).shaped.<>({ r => import r._; _1.map(_ => NutDataRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11.get, _12.get, _13.get, _14.get, _15.get, _16.get, _17.get, _18.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column NDB_No  */
    val ndbNo: Column[String] = column[String]("NDB_No")
    /** Database column Nutr_No  */
    val nutrNo: Column[String] = column[String]("Nutr_No")
    /** Database column Nutr_Val  */
    val nutrVal: Column[scala.math.BigDecimal] = column[scala.math.BigDecimal]("Nutr_Val")
    /** Database column Num_Data_Pts  */
    val numDataPts: Column[scala.math.BigDecimal] = column[scala.math.BigDecimal]("Num_Data_Pts")
    /** Database column Std_Error  */
    val stdError: Column[scala.math.BigDecimal] = column[scala.math.BigDecimal]("Std_Error")
    /** Database column Src_Cd  */
    val srcCd: Column[String] = column[String]("Src_Cd")
    /** Database column Deriv_Cd  */
    val derivCd: Column[String] = column[String]("Deriv_Cd")
    /** Database column Ref_NDB_No  */
    val refNdbNo: Column[String] = column[String]("Ref_NDB_No")
    /** Database column Add_Nutr_Mark  */
    val addNutrMark: Column[String] = column[String]("Add_Nutr_Mark")
    /** Database column Num_Studies Default(0) */
    val numStudies: Column[Int] = column[Int]("Num_Studies", O.Default(0))
    /** Database column Min  */
    val min: Column[scala.math.BigDecimal] = column[scala.math.BigDecimal]("Min")
    /** Database column Max  */
    val max: Column[scala.math.BigDecimal] = column[scala.math.BigDecimal]("Max")
    /** Database column DF Default(0) */
    val df: Column[Int] = column[Int]("DF", O.Default(0))
    /** Database column Low_EB  */
    val lowEb: Column[scala.math.BigDecimal] = column[scala.math.BigDecimal]("Low_EB")
    /** Database column Up_EB  */
    val upEb: Column[scala.math.BigDecimal] = column[scala.math.BigDecimal]("Up_EB")
    /** Database column Stat_cmt  */
    val statCmt: Column[String] = column[String]("Stat_cmt")
    /** Database column AddMod_Date  */
    val addmodDate: Column[String] = column[String]("AddMod_Date")
    /** Database column CC  */
    val cc: Column[String] = column[String]("CC")

    /** Index over (ndbNo) (database name NUT_DATA__NDB_No_idx) */
    val index1 = index("NUT_DATA__NDB_No_idx", ndbNo)
    /** Index over (nutrNo) (database name NUT_DATA__Nutr_No_idx) */
    val index2 = index("NUT_DATA__Nutr_No_idx", nutrNo)
    /** Foreign key over (nutrNo) (database name NUT_DATA__NUTR_DEF__Nutr_No_fk) */
    val definition = foreignKey("NUT_DATA__NUTR_DEF__Nutr_No_fk", nutrNo, NutrDef)(_.nutrNo)
    /** Foreign key (ndbNo) (database name FOOD__DES__NUT_DATA__NDB_No_fk) */
    val food = foreignKey("NUT_DATA__FOOD__DES__NDB_No_fk", ndbNo, FoodDes)(_.ndbNo)

  }
  /** Collection-like TableQuery object for table NutData */
  lazy val NutData = new TableQuery(tag => new NutData(tag))

  /**
   * Entity class storing rows of table NutrDef
   *  @param nutrNo Database column Nutr_No
   *  @param units Database column Units
   *  @param tagname Database column Tagname
   *  @param nutrdesc Database column NutrDesc
   *  @param numDec Database column Num_Dec
   *  @param srOrder Database column SR_Order
   */
  case class NutrDefRow(nutrNo: String, units: String, tagname: String, nutrdesc: String, numDec: String, srOrder: Int)
  /** GetResult implicit for fetching NutrDefRow objects using plain SQL queries */
  implicit def GetResultNutrDefRow(implicit e0: GR[String], e1: GR[Int]): GR[NutrDefRow] = GR {
    prs =>
      import prs._
      NutrDefRow.tupled((<<[String], <<[String], <<[String], <<[String], <<[String], <<[Int]))
  }
  /** Table description of table NUTR_DEF. Objects of this class serve as prototypes for rows in queries. */
  class NutrDef(tag: Tag) extends Table[NutrDefRow](tag, "NUTR_DEF") {
    def * = (nutrNo, units, tagname, nutrdesc, numDec, srOrder) <> (NutrDefRow.tupled, NutrDefRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (nutrNo.?, units.?, tagname.?, nutrdesc.?, numDec.?, srOrder.?).shaped.<>({ r => import r._; _1.map(_ => NutrDefRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column Nutr_No  */
    val nutrNo: Column[String] = column[String]("Nutr_No")
    /** Database column Units  */
    val units: Column[String] = column[String]("Units")
    /** Database column Tagname  */
    val tagname: Column[String] = column[String]("Tagname")
    /** Database column NutrDesc  */
    val nutrdesc: Column[String] = column[String]("NutrDesc")
    /** Database column Num_Dec  */
    val numDec: Column[String] = column[String]("Num_Dec")
    /** Database column SR_Order  */
    val srOrder: Column[Int] = column[Int]("SR_Order")

    /** Index over (nutrdesc) (database name NUTR_DEF__NutrDesc_idx) */
    val index1 = index("NUTR_DEF__NutrDesc_idx", nutrdesc)
    /** Primary key over (nutrNo) (database name NUTR_DEF__Nutr_No_idx) */
    val index2 = primaryKey("NUTR_DEF__Nutr_No_idx", nutrNo)

  }
  /** Collection-like TableQuery object for table NutrDef */
  lazy val NutrDef = new TableQuery(tag => new NutrDef(tag))

  /**
   * Entity class storing rows of table SrcCd
   *  @param srcCd Database column Src_Cd
   *  @param srccdDesc Database column SrcCd_Desc
   */
  case class SrcCdRow(srcCd: String, srccdDesc: String)
  /** GetResult implicit for fetching SrcCdRow objects using plain SQL queries */
  implicit def GetResultSrcCdRow(implicit e0: GR[String]): GR[SrcCdRow] = GR {
    prs =>
      import prs._
      SrcCdRow.tupled((<<[String], <<[String]))
  }
  /** Table description of table SRC_CD. Objects of this class serve as prototypes for rows in queries. */
  class SrcCd(tag: Tag) extends Table[SrcCdRow](tag, "SRC_CD") {
    def * = (srcCd, srccdDesc) <> (SrcCdRow.tupled, SrcCdRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (srcCd.?, srccdDesc.?).shaped.<>({ r => import r._; _1.map(_ => SrcCdRow.tupled((_1.get, _2.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column Src_Cd  */
    val srcCd: Column[String] = column[String]("Src_Cd")
    /** Database column SrcCd_Desc  */
    val srccdDesc: Column[String] = column[String]("SrcCd_Desc")
  }
  /** Collection-like TableQuery object for table SrcCd */
  lazy val SrcCd = new TableQuery(tag => new SrcCd(tag))

  /**
   * Entity class storing rows of table Weight
   *  @param ndbNo Database column NDB_No
   *  @param seq Database column Seq
   *  @param amount Database column Amount
   *  @param msreDesc Database column Msre_Desc
   *  @param gmWgt Database column Gm_Wgt
   *  @param numDataPts Database column Num_Data_Pts Default(0)
   *  @param stdDev Database column Std_Dev
   */
  case class WeightRow(ndbNo: String, seq: String, amount: scala.math.BigDecimal, msreDesc: String, gmWgt: scala.math.BigDecimal, numDataPts: Int = 0, stdDev: scala.math.BigDecimal)
  /** GetResult implicit for fetching WeightRow objects using plain SQL queries */
  implicit def GetResultWeightRow(implicit e0: GR[String], e1: GR[scala.math.BigDecimal], e2: GR[Int]): GR[WeightRow] = GR {
    prs =>
      import prs._
      WeightRow.tupled((<<[String], <<[String], <<[scala.math.BigDecimal], <<[String], <<[scala.math.BigDecimal], <<[Int], <<[scala.math.BigDecimal]))
  }
  /** Table description of table WEIGHT. Objects of this class serve as prototypes for rows in queries. */
  class Weight(tag: Tag) extends Table[WeightRow](tag, "WEIGHT") {
    def * = (ndbNo, seq, amount, msreDesc, gmWgt, numDataPts, stdDev) <> (WeightRow.tupled, WeightRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (ndbNo.?, seq.?, amount.?, msreDesc.?, gmWgt.?, numDataPts.?, stdDev.?).shaped.<>({ r => import r._; _1.map(_ => WeightRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column NDB_No  */
    val ndbNo: Column[String] = column[String]("NDB_No")
    /** Database column Seq  */
    val seq: Column[String] = column[String]("Seq")
    /** Database column Amount  */
    val amount: Column[scala.math.BigDecimal] = column[scala.math.BigDecimal]("Amount")
    /** Database column Msre_Desc  */
    val msreDesc: Column[String] = column[String]("Msre_Desc")
    /** Database column Gm_Wgt  */
    val gmWgt: Column[scala.math.BigDecimal] = column[scala.math.BigDecimal]("Gm_Wgt")
    /** Database column Num_Data_Pts Default(0) */
    val numDataPts: Column[Int] = column[Int]("Num_Data_Pts", O.Default(0))
    /** Database column Std_Dev  */
    val stdDev: Column[scala.math.BigDecimal] = column[scala.math.BigDecimal]("Std_Dev")

    /** Primary key over (ndbNo) (database name WEIGHT__NDB_No_idx) */
    val index1 = primaryKey("WEIGHT__NDB_No_idx", ndbNo)
    /** Foreign key (ndbNo) (database name WEIGHT__FOOD_DES__NDB_No_fk) */
    val food = foreignKey("WEIGHT__FOOD_DES__NDB_No_fk", ndbNo, FoodDes)(_.ndbNo)
  }
  /** Collection-like TableQuery object for table Weight */
  lazy val Weight = new TableQuery(tag => new Weight(tag))
}
