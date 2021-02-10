import Dependencies._

ThisBuild / scalaVersion := "3.0.0-M3"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "io.lexi-compiler"
ThisBuild / organizationName := "lexi"

lazy val root = (project in file("."))
  .enablePlugins(NativeImagePlugin)
  .settings(
    name := "lexi",
    libraryDependencies ++= Seq(
      antlr4,
      asm,
      scala3Compiler,
      tastyInspector,
      munit % Test
    ),
    Compile / mainClass:= Some("lexi.CLI"),
    testFrameworks += new TestFramework("munit.Framework")
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
