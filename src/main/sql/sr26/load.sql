TRUNCATE TABLE `FOOD_DES`;

LOAD DATA LOCAL INFILE '/Users/ahawley/git/tools/ingredStr2Xml/data/sr26/FOOD_DES.csv'
INTO TABLE `FOOD_DES`
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n' (
`NDB_No`, 
`FdGrp_Cd`, 
`Long_Desc`, 
`Shrt_Desc`, 
`ComName`, 
`ManufacName`, 
`Survey`, 
`Ref_desc`, 
`Refuse`, 
`SciName`, 
`N_Factor`, 
`Pro_Factor`, 
`Fat_Factor`, 
`CHO_Factor`);

TRUNCATE TABLE `NUT_DATA`;

LOAD DATA LOCAL INFILE '/Users/ahawley/git/tools/ingredStr2Xml/data/sr26/NUT_DATA.csv'
INTO TABLE `NUT_DATA`
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n' (
`NDB_No`, 
`Nutr_No`, 
`Nutr_Val`, 
`Num_Data_Pts`, 
`Std_Error`, 
`Src_Cd`, 
`Deriv_Cd`, 
`Ref_NDB_No`, 
`Add_Nutr_Mark`, 
`Num_Studies`, 
`Min`, 
`Max`, 
`DF`, 
`Low_EB`, 
`Up_EB`, 
`Stat_cmt`, 
`CC`);

TRUNCATE TABLE `NUTR_DEF`;

LOAD DATA LOCAL INFILE '/Users/ahawley/git/tools/ingredStr2Xml/data/sr26/NUTR_DEF.csv'
INTO TABLE `NUTR_DEF`
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n' (
`Nutr_No`, 
`Units`, 
`Tagname`, 
`NutrDesc`, 
`Num_Dec`, 
`SR_Order`);

TRUNCATE TABLE `WEIGHT`;

LOAD DATA LOCAL INFILE '/Users/ahawley/git/tools/ingredStr2Xml/data/sr26/WEIGHT.csv'
INTO TABLE `WEIGHT`
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n' (
`NDB_No`, 
`Seq`, 
`Amount`, 
`Msre_Desc`, 
`Gm_Wgt`, 
`Num_Data_Pts`, 
`Std_Dev`);

TRUNCATE TABLE `FOOTNOTE`;

LOAD DATA LOCAL INFILE '/Users/ahawley/git/tools/ingredStr2Xml/data/sr26/FOOTNOTE.csv'
INTO TABLE `FOOTNOTE`
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n' (
`NDB_No`, 
`Footnt_No`, 
`Footnt_Typ`, 
`Nutr_No`, 
`Footnt_Txt`);

TRUNCATE TABLE `FD_GROUP`;

LOAD DATA LOCAL INFILE '/Users/ahawley/git/tools/ingredStr2Xml/data/sr26/FD_GROUP.csv'
INTO TABLE `FD_GROUP`
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n' (
`FdGrp_Cd`, 
`FdGrp_Desc`);

TRUNCATE TABLE `LANGUAL`;

LOAD DATA LOCAL INFILE '/Users/ahawley/git/tools/ingredStr2Xml/data/sr26/LANGUAL.csv'
INTO TABLE `LANGUAL`
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n' (
`NDB_No`, 
`Factor_Code`);

TRUNCATE TABLE `LANGDESC`;

LOAD DATA LOCAL INFILE '/Users/ahawley/git/tools/ingredStr2Xml/data/sr26/LANGDESC.csv'
INTO TABLE `LANGDESC`
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n' (
`Factor_Code`, 
`Description`);

TRUNCATE TABLE `SRC_CD`;

LOAD DATA LOCAL INFILE '/Users/ahawley/git/tools/ingredStr2Xml/data/sr26/SRC_CD.csv'
INTO TABLE `SRC_CD`
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n' (
`Src_Cd`, 
`SrcCd_Desc`);

TRUNCATE TABLE `DERIV_CD`;

LOAD DATA LOCAL INFILE '/Users/ahawley/git/tools/ingredStr2Xml/data/sr26/DERIV_CD.csv'
INTO TABLE `DERIV_CD`
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n' (
`Deriv_Cd`, 
`Deriv_Desc`);

TRUNCATE TABLE `DATA_SRC`;

LOAD DATA LOCAL INFILE '/Users/ahawley/git/tools/ingredStr2Xml/data/sr26/DATA_SRC.csv'
INTO TABLE `DATA_SRC`
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n' (
`DataSrc_ID`, 
`Authors`, 
`Title`, 
`Year`, 
`Journal`, 
`Vol_City`, 
`Issue_State`, 
`Start_Page`, 
`End_Page`);
