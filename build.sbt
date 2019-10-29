name := "HeroCreator"

version := "0.1"

scalaVersion := "2.12.10"

resolvers += "Local Maven Repository" at "file:///"+Path.userHome+ "/.ivy2/cache"

unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/ext/jfxrt.jar"))

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)

libraryDependencies ++= Seq(
  "org.scalafx" %% "scalafx" % "8.0.181-R13",
  "org.scalafx" %% "scalafxml-core-sfx8" % "0.4",
  "org.scalikejdbc" %% "scalikejdbc"       % "3.1.0",
  "com.h2database"  %  "h2"                % "1.4.196",
  "ch.qos.logback"  %  "logback-classic"   % "1.2.3",
  "org.apache.derby" % "derby" % "10.13.1.1"
)

fork := true
