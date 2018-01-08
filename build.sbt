name := """library"""
organization := "waseda"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.3"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += "javax.xml.bind" % "jaxb-api" % "2.1"
libraryDependencies += "com.adrianhurt" %% "play-bootstrap" % "1.2-P26-B3"
libraryDependencies += "com.typesafe.play" %% "play-slick" % "3.0.0"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "3.0.0"
libraryDependencies += "com.h2database" % "h2" % "1.4.196"
libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.21.0"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "waseda.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "waseda.binders._"

EclipseKeys.preTasks := Seq(compile in Compile, compile in Test)

import play.sbt.routes.RoutesKeys
RoutesKeys.routesImport += "controllers.Binders._"