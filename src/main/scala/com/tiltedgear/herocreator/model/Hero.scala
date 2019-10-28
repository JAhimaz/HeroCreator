package com.tiltedgear.herocreator.model

import scalafx.beans.property.{IntegerProperty, ObjectProperty, StringProperty}
import java.time.LocalDate
import com.tiltedgear.herocreator.util.Database

import scalikejdbc.DB

class Hero(heroNameS: String, heroRoleS: String, heroAffiliationS: String, heroLoreS: String, heroOccupationS: String, heroNationalityS: String,
           heroHPI: Int, heroArmourI: Int, heroSpeedI: Int, heroBaseDamageI: Int) extends Database{

//  def this() = this(null, null)
  var heroName = new StringProperty(heroNameS)
  var heroRole = new StringProperty(heroRoleS)
  var heroAffiliation = new StringProperty(heroAffiliationS)
  var heroLore = new StringProperty(heroLoreS)
  var heroOccupation = new StringProperty(heroOccupationS)
  var heroNationality = new StringProperty(heroNationalityS)

  var heroHealth = IntegerProperty(heroHPI)
  var heroArmour = IntegerProperty(heroArmourI)
  var heroSpeed = IntegerProperty(heroSpeedI)
  var heroBaseDamage = IntegerProperty(heroBaseDamageI)
  var dateOfCreation = ObjectProperty[LocalDate](LocalDate.now())

  //Refactor This To Match Hero
/*  def save() : Try[Int] = {
    if (!(isExist)) {
      Try(DB autoCommit { implicit session =>
        sql"""
					insert into person (firstName, lastName,
						street, postalCode, city, date) values
						(${firstName.value}, ${lastName.value}, ${street.value},
							${postalCode.value},${city.value}, ${date.value.asString})
				""".update.apply()
      })
    } else {
      Try(DB autoCommit { implicit session =>
        sql"""
				update person
				set
				firstName  = ${firstName.value} ,
				lastName   = ${lastName.value},
				street     = ${street.value},
				postalCode = ${postalCode.value},
				city       = ${city.value},
				date       = ${date.value.asString}
				 where firstName = ${firstName.value} and
				 lastName = ${lastName.value}
				""".update.apply()
      })
    }

  }
  def delete() : Try[Int] = {
    if (isExist) {
      Try(DB autoCommit { implicit session =>
        sql"""
				delete from person where
					firstName = ${firstName.value} and lastName = ${lastName.value}
				""".update.apply()
      })
    } else
      throw new Exception("Person not Exists in Database")
  }
  def isExist : Boolean =  {
    DB readOnly { implicit session =>
      sql"""
				select * from person where
				firstName = ${firstName.value} and lastName = ${lastName.value}
			""".map(rs => rs.string("firstName")).single.apply()
    } match {
      case Some(x) => true
      case None => false
    }

  }*/
}

object Hero extends Database {
  def apply (
              heroNameS: String,
              heroRoleS: String,
              heroAffiliationS: String,
              heroLoreS: String,
              heroOccupationS: String,
              heroNationalityS: String,
              heroHPI: Int,
              heroArmourI: Int,
              heroSpeedI: Int,
              heroBaseDamageI: Int
            ) : Hero = {

    new Hero(heroNameS, heroRoleS, heroAffiliationS, heroLoreS, heroOccupationS, heroNationalityS, heroHPI, heroArmourI, heroSpeedI, heroBaseDamageI) {
      heroName.value = heroNameS
      heroRole.value = heroRoleS
      heroAffiliation.value = heroAffiliationS
      heroLore.value = heroLoreS
      heroOccupation.value = heroOccupationS
      heroNationality.value = heroNationalityS
      heroHealth.value = heroHPI
      heroArmour.value = heroArmourI
      heroSpeed.value = heroSpeedI
      heroBaseDamage.value = heroBaseDamageI
    }

  }

  def initializeTable() = {
/*    DB autoCommit { implicit session =>
      sql"""
			create table hero (
			  id int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
			  heroName varchar(64),
			  heroRole varchar(64),
			  heroAffiliation varchar(64),
        heroLore varchar(200),
        heroOccupation varchar(64),
        heroNationality varchar(64),
			  heroHealth int,
        heroArmour int,
        heroSpeed int,
        heroBaseDamage int,
			)
			""".execute.apply()
    }*/
  }

/*  def getAllHeroes : List[Hero] = {
/*    DB readOnly { implicit session =>
      sql"select * from person".map(rs => Hero(
        rs.string("heroName"),
        rs.string("heroRole"),
        rs.string("heroAffiliation"),
        rs.string("heroLore"),
        rs.string("heroOccupation"),
        rs.string("heroNationality"),
        rs.int("heroHealth"),
        rs.int("heroArmour"),
        rs.int("heroSpeed"),
        rs.int("heroBaseDamage"))).list.apply()
    }*/
  }*/
}



