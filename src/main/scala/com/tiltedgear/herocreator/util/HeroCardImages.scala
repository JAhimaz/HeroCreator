package com.tiltedgear.herocreator.util

import java.io.InputStream

object HeroCardImages {

  val rolePath: String = "/images/role_logos/"

  def fetchRoleLogo(roleName : String) : InputStream = roleName match {
    case "Damage" => getClass.getResourceAsStream(rolePath + "dps.png")
    case "Tank" => getClass.getResourceAsStream(rolePath + "tank.png")
    case "Support" => getClass.getResourceAsStream(rolePath + "support.png")
    case _ => getClass.getResourceAsStream("")
  }

  val factionPath: String = "/images/faction_logos/"

  def fetchFactionLogo(affiliationName : String) : InputStream = affiliationName match {
    case "Aedrussurean Empire" => getClass.getResourceAsStream(factionPath + "Aedrussurean_Empire.png")
    case "Aukiyeorith Kingdom" => getClass.getResourceAsStream(factionPath + "Aukiyeorith_Kingdom.png")
    case "Bourene Kingdom" => getClass.getResourceAsStream(factionPath + "Bourene_Kingdom.png")
    case "Chiterra Kingdom" => getClass.getResourceAsStream(factionPath + "Chiterra_Kingdom.png")
    case "Eadrakidora Dynasty" => getClass.getResourceAsStream(factionPath + "Eadrakidora_Dynasty.png")
    case "Higary Kingdom" => getClass.getResourceAsStream(factionPath + "Higary_Kingdom.png")
    case "Kugales Empire" => getClass.getResourceAsStream(factionPath + "Kugales_Empire.png")
    case "Odrouba Kingdom" => getClass.getResourceAsStream(factionPath + "Odrouba_Kingdom.png")
    case "Okhepitus Empire" => getClass.getResourceAsStream(factionPath + "Okhepitus_Empire.png")
    case _ => getClass.getResourceAsStream("")
  }
}
