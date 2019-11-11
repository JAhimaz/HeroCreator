package com.tiltedgear.herocreator.util

import java.io.InputStream

import scalafx.beans.property.StringProperty

object HeroCardImages {
  def fetchRoleLogo(roleName : StringProperty) : InputStream = {
    val path: String = "/images/role_logos/"

    if (roleName.value == "Damage"){
      return getClass.getResourceAsStream(path + "dps.png")
    }
    if (roleName.value == "Tank"){
      return getClass.getResourceAsStream(path + "tank.png")
    }
    if (roleName.value == "Support"){
      return getClass.getResourceAsStream(path + "support.png")
    }

    getClass.getResourceAsStream("")
  }

  def fetchFactionLogo(affiliationName : StringProperty) : InputStream = {
    val path: String = "/images/faction_logos/"

    if (affiliationName.value == "Aedrussurean Empire"){
      return getClass.getResourceAsStream(path + "Aedrussurean_Empire.png")
    }
    if (affiliationName.value == "Aukiyeorith Kingdom"){
      return getClass.getResourceAsStream(path + "Aukiyeorith_Kingdom.png")
    }
    if (affiliationName.value == "Bourene Kingdom"){
      return getClass.getResourceAsStream(path + "Bourene_Kingdom.png")
    }
    if (affiliationName.value == "Chiterra Kingdom"){
      return getClass.getResourceAsStream(path + "Chiterra_Kingdom.png")
    }
    if (affiliationName.value == "Eadrakidora Dynasty"){
      return getClass.getResourceAsStream(path + "Eadrakidora_Dynasty.png")
    }
    if (affiliationName.value == "Higary Kingdom"){
      return getClass.getResourceAsStream(path + "Higary_Kingdom.png")
    }
    if (affiliationName.value == "Kugales Empire"){
      return getClass.getResourceAsStream(path + "Kugales_Empire.png")
    }
    if (affiliationName.value == "Odrouba Kingdom"){
      return getClass.getResourceAsStream(path + "Odrouba_Kingdom.png")
    }
    if (affiliationName.value == "Okhepitus Empire"){
      return getClass.getResourceAsStream(path + "Okhepitus_Empire.png")
    }

    getClass.getResourceAsStream("")
  }
}
