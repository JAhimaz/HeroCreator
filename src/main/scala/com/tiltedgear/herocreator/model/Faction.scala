package com.tiltedgear.herocreator.model

import com.tiltedgear.herocreator.util.Database
import scalafx.beans.property.{IntegerProperty, StringProperty}
import scalikejdbc._

import scala.util.Try

class Faction(factionNameS: String) extends Database{

  var name = new StringProperty(factionNameS)

  def save() : Try[Int] = {
      Try(DB autoCommit { implicit session =>
        sql"""
					insert into faction (name) values
						(${name.value})
				""".update.apply()
      })
  }

  def isExist : Boolean =  {
    DB readOnly { implicit session =>
      sql"""
				select * from faction where
				name = ${name.value}
			""".map(rs => rs.string("name")).single.apply()
    } match {
      case Some(x) => true
      case None => false
    }

  }
}

object Faction extends Database {
  def apply (
              factionNameS: String
            ) : Faction = {

    new Faction(factionNameS) {
      name.value = factionNameS
    }

  }

  def initializeTable() = {
    DB autoCommit { implicit session =>
      sql"""
			create table faction (
			  id int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
			  name varchar(64)
			)
			""".execute.apply()

      val fc = new Faction("Aedrussurean Empire").save()
      val fc1 = new Faction("Aukiyeorith Kingdom").save()
      val fc2 = new Faction("Bourene Kingdom").save()
      val fc3 = new Faction("Chiterra Kingdom").save()
      val fc4 = new Faction("Eadrakidora Dynasty").save()
      val fc5 = new Faction("Higary Kingdom").save()
      val fc6 = new Faction("Kugales Empire").save()
      val fc7 = new Faction("Odrouba Kingdom").save()
      val fc8 = new Faction("Okhepitus Empire").save()
    }
  }

  def getAllFactions : List[Faction] = {
    DB readOnly { implicit session =>
      sql"select * from faction".map(rs => Faction(
        rs.string("name"))).list.apply()
    }
  }
}



