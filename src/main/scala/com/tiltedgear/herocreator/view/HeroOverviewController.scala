package com.tiltedgear.herocreator.view

import com.tiltedgear.herocreator.model.Hero
import scalafx.scene.control.Button
import scalafxml.core.macros.sfxml
import com.tiltedgear.herocreator.HeroApp
import javafx.fxml.FXML
import scalafx.Includes._
import scalafx.application.Platform
import scalafx.event.ActionEvent
import scalafx.scene.control.{Label, TableColumn, TableView}
import scalafx.beans.property.StringProperty

@sfxml
class HeroOverviewController(
  private val heroTable: TableView[Hero],
  private val heroNameColumn : TableColumn[Hero, String],
  private val heroNameLabel : Label,
  private val heroRoleLabel : Label,
  private val heroAffiliationLabel : Label,
  private val heroLoreLabel : Label,
  private val heroOccupationLabel : Label,
  private val heroNationalityLabel : Label,
  private val heroHealthLabel : Label,
  private val heroArmourLabel : Label,
  private val heroSpeedLabel : Label,
  private val heroBaseDamageLabel : Label
) {

    heroTable.items = HeroApp.heroData
  heroNameColumn.cellValueFactory = {_.value.heroName}

  def handleApplicationExit(action: ActionEvent) = {
    Platform.exit();
    System.exit(0);
  }

  def handleNewHero(action : ActionEvent) = {
    val hero = new Hero(
      "", "", "", "", "", "",
      100,100,5,25)
    val okClicked = HeroApp.showCreatorOverview(hero);
/*    if (okClicked) {
      hero.save() match {
        case Success(x) =>
          MainApp.heroData += person
        case Failure(e) =>
          val alert = new Alert(Alert.AlertType.Warning) {
            initOwner(MainApp.stage)
            title = "Failed to Save"
            headerText = "Database Error"
            contentText = "Database problem filed to save changes"
          }.showAndWait()
      }
    }*/
  }
}
