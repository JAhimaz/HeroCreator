package com.tiltedgear.herocreator.util

import scalikejdbc._
import com.tiltedgear.herocreator.model.{Faction, Hero, Race, Role}

trait Database {
  val derbyDriverClassname = "org.apache.derby.jdbc.EmbeddedDriver"

  val dbURL = "jdbc:derby:myDB;create=true;";
  // initialize JDBC driver & connection pool
  Class.forName(derbyDriverClassname)

  ConnectionPool.singleton(dbURL, "root", "secret")

  // ad-hoc session provider on the REPL
  implicit val session = AutoSession


}

object Database extends Database{
/*  def setupDB() = {
    if (!hasRoleTable) {
      Role.initializeTable()
    }
    if (!hasHeroTable) {
      Hero.initializeTable()
    }

  }
  def hasRoleTable : Boolean = {
    DB getTable "Role" match {
      case Some(x) => true
      case None => false
    }
  }

  def hasHeroTable : Boolean = {
    DB getTable "Hero" match {
      case Some(x) => true
      case None => false
    }

  }*/

  /* Initialize multiple Tables */
  def setupDB() = {
    val tables: List[String] = List("Hero", "Role", "Race", "Faction")
    for(table <- tables){
      if(!tableExists(table)){
        if(table == "Hero"){
          Hero.initializeTable()
        }
        if(table == "Role"){
          Role.initializeTable()
        }
        if(table == "Faction"){
          Faction.initializeTable()
        }
        if(table == "Race"){
          Race.initializeTable()
        }
      }
    }
  }

  def tableExists(tableName: String) : Boolean = {
    DB getTable tableName match {
      case Some(x) => true
      case None => false
    }
  }
}
