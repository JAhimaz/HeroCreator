package com.tiltedgear.herocreator.view

import java.io.InputStream

import com.tiltedgear.herocreator.model.Hero
import scalafx.scene.control.{Alert, Button, Label, TableColumn, TableView}
import scalafxml.core.macros.sfxml
import com.tiltedgear.herocreator.HeroApp
import com.tiltedgear.herocreator.util.factory.HeroFactory
import scalafx.Includes._
import scalafx.application.Platform
import scalafx.event.ActionEvent
import scalafx.beans.property.StringProperty
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.image.{Image, ImageView}

import scala.collection.mutable.ListBuffer
import scala.util.{Failure, Success}

@sfxml
class HeroOverviewController(
  private val heroTable: TableView[Hero],
  private val heroNameColumn : TableColumn[Hero, String],
  private val heroNameLabel : Label,
  private val heroRoleLabel : Label,
  private val heroFactionLabel : Label,
  private val heroLoreLabel : Label,
  private val heroOccupationLabel : Label,
  private val heroRaceLabel : Label,
  private val heroHealthLabel : Label,
  private val heroArmourLabel : Label,
  private val heroSpeedLabel : Label,
  private val heroBaseDamageLabel : Label,
  private val factionLogo : ImageView,

  private val statsLabel : Label,
  private val staticHealthLabel : Label,
  private val staticArmourLabel : Label,
  private val staticSpeedLabel : Label,
  private val staticDamageLabel : Label,
) {

  var staticLabels: List[Label] = List(statsLabel, staticSpeedLabel, staticHealthLabel, staticDamageLabel, staticArmourLabel)

  heroTable.items = HeroApp.heroData
  heroNameColumn.cellValueFactory = {_.value.heroName}

  showHeroDetails(None);

  heroTable.selectionModel().selectedItem.onChange(
    (_, _, newValue) => showHeroDetails(Option(newValue))
  )

  def doGenerateHeroes(): Unit ={
    HeroFactory.Generator(2)
  }

  def handleApplicationExit(action: ActionEvent) = {
    Platform.exit();
    System.exit(0);
  }

  private def showHeroDetails (hero : Option[Hero]) = {
    hero match {
      case Some(hero) =>

        checkStaticLabels(true)

        heroNameLabel.text <== hero.heroName
        heroRoleLabel.text <== hero.heroRole
        heroFactionLabel.text <== hero.heroAffiliation
        heroLoreLabel.text <== hero.heroLore
        heroOccupationLabel.text <== hero.heroOccupation
        heroRaceLabel.text <== hero.heroRace
        heroHealthLabel.text = hero.heroHealth.value.toString
        heroArmourLabel.text = hero.heroArmour.value.toString
        heroSpeedLabel.text = hero.heroSpeed.value.toString
        heroBaseDamageLabel.text = hero.heroBaseDamage.value.toString;

        val chosenFactionURI : InputStream = fetchFactionLogo(hero.heroAffiliation)
        val image : Image = new Image(chosenFactionURI)

        factionLogo.setImage(image)

      case None =>
        heroNameLabel.text.unbind()
        heroRoleLabel.text.unbind()
        heroFactionLabel.text.unbind()
        heroLoreLabel.text.unbind()
        heroOccupationLabel.text.unbind()
        heroRaceLabel.text.unbind()

        checkStaticLabels(false)

        heroNameLabel.text = ""
        heroRoleLabel.text = ""
        heroFactionLabel.text = ""
        heroLoreLabel.text = ""
        heroOccupationLabel.text = ""
        heroRaceLabel.text = ""
        heroHealthLabel.text = ""
        heroArmourLabel.text = ""
        heroSpeedLabel.text = ""
        heroBaseDamageLabel.text = ""
    }
  }

  def handleDeleteHero(action : ActionEvent) = {
    val selectedIndex = heroTable.selectionModel().selectedIndex.value
    val selectedHero = heroTable.selectionModel().selectedItem.value
    if (selectedIndex >= 0) {
      selectedHero.delete match {
        case Success(x) =>
          heroTable.items().remove(selectedIndex);
        case Failure(e) =>
          val alert = new Alert(Alert.AlertType.Warning) {
            initOwner(HeroApp.stage)
            title = "Failed to Save"
            headerText = "Database Error"
            contentText = "Database problem failed to save changes"
          }.showAndWait()
      }
    } else {
      // Nothing selected.
      val alert = new Alert(AlertType.Warning) {
        initOwner(HeroApp.stage)
        title = "No Selection"
        headerText = "No Hero Selected"
        contentText = "Please select a hero in the table."
      }.showAndWait()
    }
  }

  def handleEditHero(action : ActionEvent) = {
    val selectedHero = heroTable.selectionModel().selectedItem.value
    if (selectedHero != null) {
      val okClicked = HeroApp.showCreatorOverview(selectedHero)

      if (okClicked == true) {
        selectedHero.save() match {
          case Success(x) =>
            showHeroDetails(Some(selectedHero))
          case Failure(e) =>
            val alert = new Alert(Alert.AlertType.Warning) {
              initOwner(HeroApp.stage)
              title = "Failed to Save"
              headerText = "Database Error"
              contentText = "Database problem filed to save changes"
            }.showAndWait()
        }
      }

    } else {
      // Nothing selected.
      val alert = new Alert(Alert.AlertType.Warning){
        initOwner(HeroApp.stage)
        title       = "No Selection"
        headerText  = "No Hero Was Selected"
        contentText = "Please select a Hero in the table."
      }.showAndWait()
    }
  }

  def handleNewHero(action : ActionEvent) = {
    val hero = new Hero(
      "", "", "", "", "", "",
      0,0,0,0)
    val okClicked = HeroApp.showCreatorOverview(hero);
    if (okClicked == true) {
      hero.save() match {
        case Success(x) =>
          HeroApp.heroData += hero
        case Failure(e) =>
          val alert = new Alert(Alert.AlertType.Warning) {
            initOwner(HeroApp.stage)
            title = "Failed to Save"
            headerText = "Database Error"
            contentText = "Database problem filed to save changes"
          }.showAndWait()
      }
    }
  }

  def checkStaticLabels(hasHero: Boolean): Unit ={
    for(i <- staticLabels){
      i.setVisible(hasHero)
    }
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
