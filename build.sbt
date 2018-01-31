name := "Sykes"

version := "0.1"

scalaVersion := "2.12.4"

PB.targets in Compile := Seq(
  scalapb.gen(flatPackage = true, singleLineToString = true) -> (sourceManaged in Compile).value
)

libraryDependencies ++= Seq(
  // NOTE: we are adding it to our entire project, not just for the "test"
  "org.scalacheck" %% "scalacheck" % "1.13.5",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "com.google.guava" % "guava" % "23.0",
  "com.typesafe.akka" %% "akka-actor" % "2.5.9"
)