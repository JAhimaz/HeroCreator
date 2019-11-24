package com.tiltedgear.herocreator.model

import com.tiltedgear.herocreator.util.Database
import scalafx.beans.property.{IntegerProperty, StringProperty}
import scalikejdbc._

import scala.util.Try

class Role(roleNameS: String) extends Database{

  var name = new StringProperty(roleNameS)

  def save() : Try[Int] = {
      Try(DB autoCommit { implicit session =>
        sql"""
					insert into role (name) values
						(${name.value})
				""".update.apply()
      })
  }

  def isExist : Boolean =  {
    DB readOnly { implicit session =>
      sql"""
				select * from role where
				name = ${name.value}
			""".map(rs => rs.string("name")).single.apply()
    } match {
      case Some(x) => true
      case None => false
    }

  }
}

object Role extends Database {
  def apply (
              roleNameS: String
            ) : Role = {

    new Role(roleNameS) {
      name.value = roleNameS
    }

  }

  def initializeTable() = {
    DB autoCommit { implicit session =>
      sql"""
			create table role (
			  id int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
			  name varchar(64)
			)
			""".execute.apply()

      val Support = new Role("Support").save()
      val Damage = new Role("Damage").save()
      val Tank = new Role("Tank").save()

    }



  }

  def getAllRoles : List[Role] = {
    DB readOnly { implicit session =>
      sql"select * from role".map(rs => Role(
        rs.string("name"))).list.apply()
    }
  }
}



