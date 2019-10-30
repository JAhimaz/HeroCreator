package com.tiltedgear.herocreator.util

import scalikejdbc._
import com.tiltedgear.herocreator.model.Hero

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
  def setupDB() = {
    if (!hasDBInitialize)
      Hero.initializeTable()
  }
  def hasDBInitialize : Boolean = {

    DB getTable "Hero" match {
      case Some(x) => true
      case None => false
    }

  }
}
