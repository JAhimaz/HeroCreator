package com.tiltedgear.herocreator.model

import scalafx.beans.property.{IntegerProperty, ObjectProperty, StringProperty}
import java.time.LocalDate
import com.tiltedgear.herocreator.util.Database

import scalikejdbc.DB

class Hero(heroNameS: String, heroRoleS: String, heroAffiliationS: String, heroLoreS: String, heroOccupationS: String, heroRaceS: String,
           heroHPI: Int, heroArmourI: Int, heroSpeedI: Int, heroBaseDamageI: Int) extends Database{

//  def this() = this(null, null)
  var heroName = new StringProperty(heroNameS)
  var heroRole = new StringProperty(heroRoleS)
  var heroAffiliation = new StringProperty(heroAffiliationS)
  var heroLore = new StringProperty(heroLoreS)
  var heroOccupation = new StringProperty(heroOccupationS)
  var heroRace = new StringProperty(heroRaceS)

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
					insert into hero (heroName, heroRole,
						heroAffiliation, heroLore, heroOccupation, heroRace, heroHealth, heroArmour, heroSpeed, heroBasedDamage, dateOfCreation) values
						(${heroName.value}, ${heroRole.value}, ${heroAffiliation.value},
							${heroLore.value},${heroOccupation.value},${heroRace.value},
							${heroHealth.value},${heroArmour.value},${heroSpeed.value},
							${heroBaseDamage.value},${dateOfCreation.value.asString})
				""".update.apply()
      })
    } else {
      Try(DB autoCommit { implicit session =>
        sql"""
				update person
				set
				heroName  = ${heroName.value},
				heroRole   = ${heroRole.value},
				heroAffiliation  = ${heroAffiliation.value},
				heroLore = ${heroLore.value},
				heroOccupation  = ${heroOccupation.value},
				heroRace  = ${heroRace.value},
				heroHealth  = ${heroHealth.value},
				heroArmour  = ${heroArmour.value},
				heroSpeed  = ${heroSpeed.value},
				heroBaseDamage  = ${heroBaseDamage.value},
				dateOfCreation  = ${dateOfCreation.value.asString}
				 where heroName = ${heroName.value} and
				 heroRole = ${heroRole.value}
				""".update.apply()
      })
    }

  }
  def delete() : Try[Int] = {
    if (isExist) {
      Try(DB autoCommit { implicit session =>
        sql"""
				delete from person where
					heroName = ${heroName.value} and heroRole = ${heroRole.value}
				""".update.apply()
      })
    } else
      throw new Exception("Hero not Exists in Database")
  }
  def isExist : Boolean =  {
    DB readOnly { implicit session =>
      sql"""
				select * from hero where
				heroName = ${heroName.value} and heroRole = ${heroRole.value}
			""".map(rs => rs.string("heroName")).single.apply()
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
              heroRaceS: String,
              heroHPI: Int,
              heroArmourI: Int,
              heroSpeedI: Int,
              heroBaseDamageI: Int
            ) : Hero = {

    new Hero(heroNameS, heroRoleS, heroAffiliationS, heroLoreS, heroOccupationS, heroRaceS, heroHPI, heroArmourI, heroSpeedI, heroBaseDamageI) {
      heroName.value = heroNameS
      heroRole.value = heroRoleS
      heroAffiliation.value = heroAffiliationS
      heroLore.value = heroLoreS
      heroOccupation.value = heroOccupationS
      heroRace.value = heroRaceS
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
        heroRace varchar(64),
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
        rs.string("heroRace"),
        rs.int("heroHealth"),
        rs.int("heroArmour"),
        rs.int("heroSpeed"),
        rs.int("heroBaseDamage"))).list.apply()
    }*/
  }*/
}



