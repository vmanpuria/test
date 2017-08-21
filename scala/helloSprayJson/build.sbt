lazy val root = (project in file(".")).
  settings(
    organization := "com.example",
    scalaVersion := "2.12.1",
    version      := "0.1.0-SNAPSHOT",
    name := "helloSprayJson",
    libraryDependencies += "io.spray" %%  "spray-json" % "1.3.3"
  )
