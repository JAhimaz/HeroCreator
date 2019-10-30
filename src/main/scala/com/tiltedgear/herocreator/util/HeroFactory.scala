package com.tiltedgear.herocreator.util
import com.tiltedgear.herocreator.HeroApp
import com.tiltedgear.herocreator.model.Hero
import scala.util.Random

object HeroFactory {
    val rand = scala.util.Random


    def heroGenerator() = {

      for(i <- 1 to (rand.nextInt(24)+1)){
        HeroApp.heroData += new Hero(
          "Mei",
          "Damage",
          "CRP",
          "Lorem Ipsum",
          "Social Worker",
          "Chinese",
          (rand.nextInt(475)+25),
          (rand.nextInt(175)+25),
          (rand.nextInt(10)+3),
          (rand.nextInt(50)+25))
      }
    }

    def getRandomElement(list: Seq[String], random: Random): String =
      list(random.nextInt(list.length))
}
