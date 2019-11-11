package com.tiltedgear.herocreator.util.factory

import scala.util.Random

object Misc {
  def GetRandomElement(list: Seq[String], random: Random): String =
    list(random.nextInt(list.length))
}
