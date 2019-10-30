package com.tiltedgear.herocreator.view

import com.tiltedgear.herocreator.model.Hero
import scalafx.scene.control.{Alert, Button, Label, TableColumn, TableView}
import scalafxml.core.macros.sfxml
import com.tiltedgear.herocreator.HeroApp
import javafx.fxml.FXML
import scalafx.Includes._
import scalafx.application.Platform
import scalafx.event.ActionEvent
import scalafx.beans.property.StringProperty

import scala.util.{Failure, Success}

@sfxml
class HeroOverviewController(
  private val heroTable: TableView[Hero],
  private val heroNameColumn : TableColumn[Hero, String],
  private val heroNameLabel : Label,
  private val heroRoleLabel : Label,
  private val heroAffiliationLabel : Label,
  private val heroLoreLabel : Label,
  private val heroOccupationLabel : Label,
  private val heroRaceLabel : Label,
  private val heroHealthLabel : Label,
  private val heroArmourLabel : Label,
  private val heroSpeedLabel : Label,
  private val heroBaseDamageLabel : Label
) {

    heroTable.items = HeroApp.heroData
  heroNameColumn.cellValueFactory = {_.value.heroName}

  showHeroDetails(None);

  heroTable.selectionModel().selectedItem.onChange(
    (_, _, newValue) => showHeroDetails(Some(newValue))
  )


  def handleApplicationExit(action: ActionEvent) = {
    Platform.exit();
    System.exit(0);
  }

  private def showHeroDetails (hero : Option[Hero]) = {
    hero match {
      case Some(hero) =>
        // Fill the labels with info from the person object.
        heroNameLabel.text <== hero.heroName
        heroRoleLabel.text <== hero.heroRole
        heroAffiliationLabel.text <== hero.heroAffiliation
        heroLoreLabel.text <== hero.heroLore
        heroOccupationLabel.text <== hero.heroOccupation
        heroRaceLabel.text <== hero.heroRace
        heroHealthLabel.text = hero.heroHealth.value.toString + " HP"
        heroArmourLabel.text = hero.heroArmour.value.toString + " AP"
        heroSpeedLabel.text = hero.heroSpeed.value.toString + " km\\h"
        heroBaseDamageLabel.text = hero.heroBaseDamage.value.toString + " Dps";

      case None =>
        // Person is null, remove all the text.
        heroNameLabel.text = ""
        heroRoleLabel.text = ""
        heroAffiliationLabel.text = ""
        heroLoreLabel.text = ""
        heroOccupationLabel.text = ""
        heroRaceLabel.text = ""
        heroHealthLabel.text = ""
        heroArmourLabel.text = ""
        heroSpeedLabel.text = ""
        heroBaseDamageLabel.text = ""
    }
  }


  def handleNewHero(action : ActionEvent) = {
    val hero = new Hero(
      "", "", "", "", "", "",
      100,100,5,25)
    val okClicked = HeroApp.showCreatorOverview(hero);
    if (okClicked) {
/*      hero.save() match {
        case Success(x) =>
          HeroApp.heroData += hero
        case Failure(e) =>
          val alert = new Alert(Alert.AlertType.Warning) {
            initOwner(HeroApp.stage)
            title = "Failed to Save"
            headerText = "Database Error"
            contentText = "Database problem filed to save changes"
          }.showAndWait()
      }*/
    }
  }
}
