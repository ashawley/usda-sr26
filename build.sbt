// build.sbt --- Scala build tool settings

scalaVersion := "2.10.4"

scalacOptions := Seq("-unchecked", "-deprecation", "-feature", "-encoding", "utf8", "-Ywarn-adapted-args", "-Ywarn-dead-code", "-Ywarn-numeric-widen", "-Ywarn-inaccessible")

javaOptions ++= Seq("-Djava.awt.headless=true", "-Xmx2G", "-XX:MaxPermSize=1G")

libraryDependencies ++= List(
  "com.typesafe" % "config" % "1.2.0",
  "org.specs2" %% "specs2" % "2.3.10" % "test",
  "org.slf4j" % "slf4j-nop" % "1.7.7",
  "ch.qos.logback" % "logback-core" % "1.1.2",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "org.joda" % "joda-convert" % "1.6",
  "joda-time" % "joda-time" % "2.3",
  "io.spray" % "spray-client" % "1.1.1",
  "org.json4s" %% "json4s-native" % "3.2.9",
  "org.json4s" %% "json4s-jackson" % "3.2.9",
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.10" % "2.3.2",
  "com.sksamuel.elastic4s" %% "elastic4s" % "1.1.1.0",
  "com.typesafe.slick" %% "slick" % "2.0.1",
  "mysql" % "mysql-connector-java" % "5.1.24",
  "com.h2database" % "h2" % "1.3.176"
)

resolvers += Resolver.sonatypeRepo("releases")

addCompilerPlugin("org.brianmckenna" %% "wartremover" % "0.8")

scalacOptions in (Compile, compile) += "-P:wartremover:only-warn-traverser:org.brianmckenna.wartremover.warts.Unsafe"

scalariformSettings
