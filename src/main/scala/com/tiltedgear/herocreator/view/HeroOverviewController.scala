package com.tiltedgear.herocreator.view

import com.tiltedgear.herocreator.model.Hero
import scalafx.scene.control.Button
import scalafxml.core.macros.sfxml
import com.tiltedgear.herocreator.HeroApp
import scalafx.Includes._
import scalafx.application.Platform
import scalafx.event.ActionEvent

@sfxml
class HeroOverviewController {
  def handleApplicationExit(action: ActionEvent) = {
    Platform.exit();
    System.exit(0);
  }
}
