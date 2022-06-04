ThisBuild / organization      := "innomic.ml"
ThisBuild / organizationName  := "Innomic"
ThisBuild / scalaVersion      := "2.13.8"
ThisBuild / scalafixOnCompile := true

ThisBuild / dockerRepository := Some("ghcr.io/innomic")
ThisBuild / publishTo        := Some("github-maven".at("https://maven.pkg.github.com/innomic/tapir-scala-template"))

name             := "tapir-scala-template"
Docker / version := "latest"

dependencyOverrides += "org.scala-lang.modules" %% "scala-java8-compat" % "1.0.2"

libraryDependencies += "ch.qos.logback"               % "logback-classic"         % "1.2.11"
libraryDependencies += "com.typesafe.akka"           %% "akka-actor-typed"        % "2.6.19"
libraryDependencies += "org.postgresql"               % "postgresql"              % "42.3.4"
libraryDependencies += "io.getquill"                 %% "quill-jdbc"              % "3.18.0"
libraryDependencies += "io.getquill"                 %% "quill-cassandra"         % "3.18.0"
libraryDependencies += "com.softwaremill.sttp.tapir" %% "tapir-core"              % "1.0.0-RC2"
libraryDependencies += "com.softwaremill.sttp.tapir" %% "tapir-json-circe"        % "1.0.0-RC2"
libraryDependencies += "com.softwaremill.sttp.tapir" %% "tapir-akka-http-server"  % "1.0.0-RC2"
libraryDependencies += "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-bundle" % "1.0.0-RC2"
libraryDependencies += "com.softwaremill.macwire"    %% "macros"                  % "2.5.7"    % Provided
libraryDependencies += "org.scalameta"               %% "munit"                   % "1.0.0-M4" % Test

enablePlugins(GitVersioning, JavaServerAppPackaging, DockerPlugin)
dockerExposedPorts := Seq(9000)

dockerLabels ++= Map("org.opencontainers.image.source" -> "https://github.com/innomic/tapir-scala-template")
