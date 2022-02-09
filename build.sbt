name := "ticket-cli-app"

version := "0.1"

scalaVersion := "2.13.7"

libraryDependencies ++= Seq(
  "io.monix" %% "monix" % "3.4.0",
  "com.disneystreaming" %% "weaver-monix" % "0.6.7" % Test
)