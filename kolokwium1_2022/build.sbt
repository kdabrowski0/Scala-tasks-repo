name := "Kolokwium_1"
version := "1.0.0"

scalaVersion := "3.1.2"
scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.6.19"
  val scalaTestV = "3.2.12"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "org.scalatest" %% "scalatest" % scalaTestV % "test"
  )
}


