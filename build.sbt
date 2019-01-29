import com.typesafe.config.ConfigFactory
name := """dyaus"""
organization := "amritoit"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.8"

libraryDependencies += guice
libraryDependencies += ws
//libraryDependencies += "mysql" % "mysql-connector-java" % "8.0"
libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "8.0.14"
)
libraryDependencies += guice
//libraryDependencies += "com.h2database" % "h2" % "1.4.197"
libraryDependencies ++= Seq(
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "5.4.1.Final" // replace by your jpa implementation
)

// flyway-play 5.2.0 supports only flyway 5.2.4a
libraryDependencies += "org.flywaydb" % "flyway-core" % "5.2.4"
libraryDependencies ++= Seq(
  "org.flywaydb" %% "flyway-play" % "5.2.0"
)

// https://www.playframework.com/documentation/2.5.x/JavaJPA#Deploying-Play-with-JPA
PlayKeys.externalizeResources := false

//libraryDependencies ++= Seq(evolutions, jdbc)
enablePlugins(FlywayPlugin)
val conf = ConfigFactory.parseFile(new File("conf/application.conf")).resolve()
flywayUrl := conf.getString("db.default.url") + "?useSSL=false"
flywayDriver := "com.mysql.cj.jdbc.Driver"
flywayUser := "root"
flywayPassword := "root1234"
//flywayLocations += "db/migration"
//flywaySchemas := Seq("schema3")
flywayPlaceholders := Map(
  "ssl" -> "false"
)
