package com.tiltedgear.herocreator.model

import scalafx.beans.property.{IntegerProperty, ObjectProperty, StringProperty}
import java.time.LocalDate

class Hero(heroNameS: String, heroRoleS: String, heroAffiliationS: String, heroLoreS: String, heroOccupationS: String, heroNationalityS: String,
           heroHPI: Int, heroArmourI: Int, heroSpeedI: Int, heroBaseDamageI: Int) {

  var heroName = new StringProperty(heroNameS)
  var heroRole = new StringProperty(heroRoleS)
  var heroAffiliation = new StringProperty(heroAffiliationS)
  var heroLore = new StringProperty(heroLoreS)
  var heroOccupation = new StringProperty(heroOccupationS)
  var heroNationality = new StringProperty(heroNationalityS)

  var heroHealth = IntegerProperty(heroHPI)
  var heroArmour = IntegerProperty(heroArmourI)
  var heroSpeed = IntegerProperty(heroSpeedI)
  var heroBaseDamnage = IntegerProperty(heroBaseDamageI)
  var dateOfCreation = ObjectProperty[LocalDate](LocalDate.now())
}

