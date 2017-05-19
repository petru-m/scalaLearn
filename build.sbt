name := "scalaLearn"

version := "1.0"

scalaVersion := "2.11.8"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

initialize := {
  val _ = initialize.value
  if (sys.props("java.specification.version") != "1.8")
    sys.error("Java 8 is required for this project!")
}

libraryDependencies ++= Seq(
  "com.typesafe.akka"          %% "akka-http"                % "10.0.5",
  "de.heikoseeberger"          %% "akka-http-circe"          % "1.11.0",

  "com.typesafe.slick"         %% "slick"                    % "3.2.0-M2",
  "org.postgresql"             % "postgresql"                % "9.4-1201-jdbc41",
  "com.zaxxer"                 % "HikariCP"                  % "2.4.5",

  "io.circe"                   %% "circe-core"               % "0.6.1",
  "io.circe"                   %% "circe-generic"            % "0.6.1",
  "io.circe"                   %% "circe-parser"             % "0.6.1"
)

scalacOptions := Seq(
  "-deprecation",
  "-feature",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-Ywarn-dead-code",
  "-Ywarn-unused-import"
)