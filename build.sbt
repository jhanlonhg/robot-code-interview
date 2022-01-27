ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "robot-code-interview",
    idePackagePrefix := Some("org.hanlonjohn23")
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9"