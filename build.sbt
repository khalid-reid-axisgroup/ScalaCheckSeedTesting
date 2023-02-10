ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.14"

lazy val root = (project in file("."))
  .settings(
    name := "ScalaCheckSeedTesting"
  )


// https://mvnrepository.com/artifact/org.scalatest/scalatest
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % Test

// https://mvnrepository.com/artifact/org.scalatestplus/scalacheck-1-17
libraryDependencies += "org.scalatestplus" %% "scalacheck-1-17" % "3.2.15.0" % Test
