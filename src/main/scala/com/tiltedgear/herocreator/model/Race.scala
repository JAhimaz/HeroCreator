package com.tiltedgear.herocreator.model

import com.tiltedgear.herocreator.util.Database
import scalafx.beans.property.{IntegerProperty, StringProperty}
import scalikejdbc._

import scala.util.Try

class Race(raceNameS: String) extends Database{

  var name = new StringProperty(raceNameS)

  def save() : Try[Int] = {
      Try(DB autoCommit { implicit session =>
        sql"""
					insert into race (name) values
						(${name.value})
				""".update.apply()
      })
  }

  def isExist : Boolean =  {
    DB readOnly { implicit session =>
      sql"""
				select * from race where
				name = ${name.value}
			""".map(rs => rs.string("name")).single.apply()
    } match {
      case Some(x) => true
      case None => false
    }
  }
}

object Race extends Database {
  def apply (
              raceNameS: String
            ) : Race = {

    new Race(raceNameS) {
      name.value = raceNameS
    }
  }

  def initializeTable() = {
    DB autoCommit { implicit session =>
      sql"""
			create table race (
			  id int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
			  name varchar(64)
			)
			""".execute.apply()

      val Human = new Race("Human").save()
      val NightElf = new Race("Night Elf").save()
      val Orc = new Race("Orc").save()
      val Undead = new Race("Undead").save()
    }
  }

  def getAllRaces : List[Race] = {
    DB readOnly { implicit session =>
      sql"select * from race".map(rs => Race(
        rs.string("name"))).list.apply()
    }
  }
}



