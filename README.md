USDA
=====

The United States Department of Agriculture (USDA) maintains the
Nutrient Database for Standard Reference which contains over 8,000
different food items.  In July of 2013, they released
[SR26](http://www.ars.usda.gov/Services/docs.htm?docid=23634).

The following is a library to interact with the data with
[Slick](http://slick.typesafe.com/) and
[ElasticSearch](http://www.elasticsearch.org).

## Importing an existing database

    $ mysqladmin5 -u root create usda
    $ bzip2 -dc usda-sr26.sql.bz2 | mysql -u root usda

## Running the test suite

Unit tests are written using [Specs2](http://specs2.org/)

### All tests

Start [SBT](http://www.scala-sbt.org/) and start the "test" task.

    $ sbt
    > ~test
    [...]
    [info] Passed: Total 41, Failed 0, Errors 0, Passed 41
    [success] Total time: 4 s, completed Apr 25, 2014 11:16:57 AM

### Particular tests

    $ sbt
    > testOnly usda.test.FoodsSpec
    SLF4J: Class path contains multiple SLF4J bindings.
    SLF4J: Found binding in [jar:file:/Users/ahawley/.ivy2/cache/org.slf4j/slf4j-nop/jars/slf4j-nop-1.7.7.jar!/org/slf4j/impl/StaticLoggerBinder.class]
    SLF4J: Found binding in [jar:file:/Users/ahawley/.ivy2/cache/ch.qos.logback/logback-classic/jars/logback-classic-1.1.2.jar!/org/slf4j/impl/StaticLoggerBinder.class]
    SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
    SLF4J: Actual binding is of type [org.slf4j.helpers.NOPLoggerFactory]
    [info] FoodsSpec
    [info]
    [info] Foods should
    [info] + find the Gov code from a description
    [info] + get the food group code for a Gov code
    [info] + get the food group name for a group code
    [info] + get the food group name for a Gov code
    [info] + starting with the 1 letter
    [info] + starting with the 2 letters
    [info] + starting with the 9 letters
    [info] + list all foods
    [info] + list one food
    [info]
    [info] Total for specification FoodsSpec
    [info] Finished in 33 ms
    [info] 9 examples, 0 failure, 0 error
    [info] Passed: Total 9, Failed 0, Errors 0, Passed 9
    [success] Total time: 6 s, completed Apr 25, 2014 1:10:58 PM

## Running examples

There are a few "applications" available in the project.

### Use the data modeling

This application just makes some queries of the database.

    $ sbt
    > runMain usda.App
    [Nothing is printed.]

## Serializing to JSON files

This application writes 100 recipe objects to JSON in the "data/" directory.

    $ sbt
    > runMain usda.Json

## Loading in Elasticsearch and running a search

Load a single recipe and search for it

    $ sbt
    > runMain usda.ElasticSearch

## Importing a new release from USDA

Here were the instructions for importing the SR26 data into MySQL.

### Convert to CSV

    $ echo $LANG
    en_US.UTF-8
    $ export LANG=C
    $ for f in sr26/*.txt; do 
        sed -e 's/"/""/g; s/~/"/g; s/\^/,/g;' ${f} \
        | tr -d '\r' > ${f%%.txt}.csv;
    done

### Import into MySQL

    $ mysql --local-infile -u root
    
    mysql> create database usda;
    Query OK, 1 row affected (0.00 sec)
    
    mysql> use usda;
    Database changed
    
    mysql> \. src/main/sql/sr26/create.sql
    Query OK, 0 rows affected (0.11 sec)
    
    Query OK, 0 rows affected (0.20 sec)
    
    Query OK, 0 rows affected (0.13 sec)
    
    Query OK, 0 rows affected (0.13 sec)
    
    Query OK, 0 rows affected (0.12 sec)
    
    Query OK, 0 rows affected (0.14 sec)
    
    Query OK, 0 rows affected (0.12 sec)
    
    Query OK, 0 rows affected (0.12 sec)
    
    Query OK, 0 rows affected (0.13 sec)
    
    Query OK, 0 rows affected (0.12 sec)
    
    Query OK, 0 rows affected (0.12 sec)
    
    mysql> \. src/main/sql/sr26/load.sql
    Query OK, 0 rows affected (0.00 sec)
    
    Query OK, 8463 rows affected, 13434 warnings (0.03 sec)
    Records: 8463  Deleted: 0  Skipped: 0  Warnings: 0
    
    Query OK, 0 rows affected (0.00 sec)
    
    Query OK, 632894 rows affected, 65535 warnings (2.97 sec)
    Records: 632894  Deleted: 0  Skipped: 0  Warnings: 631166
    
    Query OK, 0 rows affected (0.00 sec)
    
    Query OK, 150 rows affected (0.06 sec)
    Records: 150  Deleted: 0  Skipped: 0  Warnings: 0
    
    Query OK, 0 rows affected (0.00 sec)
    
    Query OK, 15137 rows affected, 24526 warnings (0.03 sec)
    Records: 15137  Deleted: 0  Skipped: 0  Warnings: 5
    
    Query OK, 0 rows affected (0.00 sec)
    
    Query OK, 541 rows affected (0.00 sec)
    Records: 541  Deleted: 0  Skipped: 0  Warnings: 0
    
    Query OK, 0 rows affected (0.00 sec)
    
    Query OK, 25 rows affected (0.00 sec)
    Records: 25  Deleted: 0  Skipped: 0  Warnings: 0
    
    Query OK, 0 rows affected (0.00 sec)
    
    Query OK, 38804 rows affected (0.02 sec)
    Records: 38804  Deleted: 0  Skipped: 0  Warnings: 0
    
    Query OK, 0 rows affected (0.00 sec)
    
    Query OK, 774 rows affected (0.00 sec)
    Records: 774  Deleted: 0  Skipped: 0  Warnings: 0
    
    Query OK, 0 rows affected (0.00 sec)
    
    Query OK, 10 rows affected (0.03 sec)
    Records: 10  Deleted: 0  Skipped: 0  Warnings: 0
    
    Query OK, 0 rows affected (0.00 sec)
    
    Query OK, 55 rows affected (0.00 sec)
    Records: 55  Deleted: 0  Skipped: 0  Warnings: 0
    
    Query OK, 0 rows affected (0.01 sec)
    
    Query OK, 570 rows affected (0.00 sec)
    Records: 570  Deleted: 0  Skipped: 0  Warnings: 0


### Add indexes

    mysql> create index `FD_GROUP__FdGrp_Cd_idx` on `FD_GROUP` (FdGrp_Cd);
    Query OK, 25 rows affected (0.13 sec)
    Records: 25  Duplicates: 0  Warnings: 0
    
    mysql> create index `FOOD_DES__Long_Desc_idx` on `FOOD_DES` (Long_Desc);
    Query OK, 8463 rows affected (0.12 sec)
    Records: 8463  Duplicates: 0  Warnings: 0
    
    mysql> create index `NUTR_DEF__NutrDesc_idx` on `NUTR_DEF` (NutrDesc);
    Query OK, 150 rows affected (0.11 sec)
    Records: 150  Duplicates: 0  Warnings: 0
    
    mysql> create index `NUTR_DEF__Nutr_No_idx` on `NUTR_DEF` (Nutr_No);
    Query OK, 150 rows affected (0.12 sec)
    Records: 150  Duplicates: 0  Warnings: 0
    
    mysql> create index `NUT_DATA__NDB_No_idx` on `NUT_DATA` (NDB_No);
    Query OK, 632894 rows affected (6.06 sec)
    Records: 632894  Duplicates: 0  Warnings: 0
    
    mysql> create index `NUT_DATA__Nutr_No_idx` on `NUT_DATA` (Nutr_No);
    Query OK, 632894 rows affected (5.98 sec)
    Records: 632894  Duplicates: 0  Warnings: 0
    
    mysql> create index `WEIGHT__NDB_No_idx` on `WEIGHT` (NDB_No);
    Query OK, 15137 rows affected (0.12 sec)
    Records: 15137  Duplicates: 0  Warnings: 0

### Add foreign keys

    mysql> alter table `NUT_DATA` add constraint `NUT_DATA__NUTR_DEF__Nutr_No_fk` foreign key (Nutr_No) references `NUTR_DEF` (Nutr_No);
    Query OK, 632894 rows affected (6.17 sec)
    Records: 632894  Duplicates: 0  Warnings: 0
    
    mysql> alter table `FD_GROUP` add constraint `FD_GROUP__FdGrp_Cd_idx` foreign key (FdGrp_Cd) references `FOOD_DES` (FdGrp_Cd);
    Query OK, 25 rows affected (0.14 sec)
    Records: 25  Duplicates: 0  Warnings: 0
    
    mysql> alter table `FOOD_DES` add constraint `FOOD_DES__FD_GROUP__FdGrp_Cd_fk` foreign key (FdGrp_Cd) references `FOOD_DES` (FdGrp_Cd);
    Query OK, 8463 rows affected (0.14 sec)
    Records: 8463  Duplicates: 0  Warnings: 0
    
    mysql> alter table `NUT_DATA` add constraint `NUT_DATA__FOOD__DES__ndbNo_fk` foreign key (NDB_No) references `FOOD_DES` (NDB_No);
    Query OK, 632894 rows affected (6.07 sec)
    Records: 632894  Duplicates: 0  Warnings: 0

### Code generation in Slick

Automatically creates a new version of the Scala source file
[Tables.scala](src/main/scala/usda/Tables.scala).  This file contains
the [generated code](http://slick.typesafe.com/doc/2.0.1/code-generation.html)
for Slick lifted tables based on the "usda" schema loaded above in to
MySQL.

    $ sbt
    > runMain usda.CodeGen

### Dumping the database

    $ mysqldump5 -u root usda | bzip2 > usda-sr26.sql.bz2

## FIXME

- ElasticSearch indexing doesn't work
