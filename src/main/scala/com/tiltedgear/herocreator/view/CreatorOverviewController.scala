package com.tiltedgear.herocreator.view

import com.tiltedgear.herocreator.model.Hero
import com.tiltedgear.herocreator.util.factory.HeroFactory
import scalafx.scene.control.{Alert, ComboBox, TextArea, TextField}
import scalafx.stage.Stage
import scalafxml.core.macros.sfxml
import scalafx.event.ActionEvent
import scalafx.Includes._
import scalafx.scene.layout.AnchorPane

@sfxml
class CreatorOverviewController(
  private val heroNameField : TextField,
  private val heroRoleField : ComboBox[String],
  private val heroAffiliationField : ComboBox[String],
  private val heroLoreField : TextArea,
  private val heroOccupationField : TextField,
  private val heroRaceField : ComboBox[String],
  private val heroHPField : TextField,
  private val heroArmourField : TextField,
  private val heroSpeedField : TextField,
  private val heroBaseDamageField : TextField,
  private val backgroundFaction: AnchorPane,
) {

  for (i <- HeroFactory.possibleRoles) {
    heroRoleField += i
  }

  for (i <- HeroFactory.possibleRaces) {
    heroRaceField += i
  }

  for (i <- HeroFactory.possibleFactions) {
    heroAffiliationField += i
  }

  var dialogStage: Stage = null
  private var _hero: Hero = null
  var okClicked = false
  var isEditing = false

  def hero: Hero = _hero

  def hero_=(x: Hero) {
    _hero = x

    heroNameField.text = _hero.heroName.value
    heroRoleField.value = _hero.heroRole.value
    heroAffiliationField.value = _hero.heroAffiliation.value
    heroLoreField.text = _hero.heroLore.value
    heroOccupationField.text = _hero.heroOccupation.value
    heroRaceField.value = _hero.heroRace.value
    heroHPField.text = _hero.heroHealth.value.toString
    heroArmourField.text = _hero.heroArmour.value.toString
    heroSpeedField.text = _hero.heroSpeed.value.toString
    heroBaseDamageField.text = _hero.heroBaseDamage.value.toString

    if (_hero.heroName.value != "") {
      isEditing = true;
      heroNameField.setEditable(false)
    }
  }

  def doRandomStats(action: ActionEvent): Unit = {
    val generatedHero = HeroFactory.Generator()

    if (!isEditing) {
      heroNameField.text = generatedHero.heroName.value
    }

    heroRoleField.value = generatedHero.heroRole.value
    heroAffiliationField.value = generatedHero.heroAffiliation.value
    heroLoreField.text = generatedHero.heroLore.value
    heroOccupationField.text = generatedHero.heroOccupation.value
    heroRaceField.value = generatedHero.heroRace.value
    heroHPField.text = generatedHero.heroHealth.value.toString
    heroArmourField.text = generatedHero.heroArmour.value.toString
    heroSpeedField.text = generatedHero.heroSpeed.value.toString
    heroBaseDamageField.text = generatedHero.heroBaseDamage.value.toString
  }

  def handleOk(action: ActionEvent) {
    if (isInputValid()) {
      _hero.heroName <== heroNameField.text
      _hero.heroRole <== heroRoleField.getSelectionModel.selectedItemProperty
      _hero.heroAffiliation <== heroAffiliationField.getSelectionModel.selectedItemProperty
      _hero.heroLore <== heroLoreField.text
      _hero.heroOccupation <== heroOccupationField.text
      _hero.heroRace <== heroRaceField.getSelectionModel.selectedItemProperty
      _hero.heroHealth <== heroHPField.getText().toInt
      _hero.heroArmour <== heroArmourField.getText().toInt
      _hero.heroSpeed <== heroSpeedField.getText().toInt
      _hero.heroBaseDamage <== heroBaseDamageField.getText().toInt

      okClicked = true;
      dialogStage.close()
    }
  }

  def handleCancel(action: ActionEvent) {
    dialogStage.close();
  }

  def nullChecking(x: String) = x == null || x.length == 0

  def isInputValid() : Boolean = {
    var errorMessage = ""

    if (nullChecking(heroNameField.text.value))
      errorMessage += "Please Enter A Valid Name!\n"
    if (nullChecking(heroOccupationField.text.value))
      errorMessage += "Please Enter A Occupation!\n"
    if (nullChecking(heroRoleField.getSelectionModel.selectedItemProperty().value))
      errorMessage += "Please Choose A Role!\n"
    if (nullChecking(heroAffiliationField.getSelectionModel.selectedItemProperty().value))
      errorMessage += "Please Choose A Affiliation!\n"
    if (nullChecking(heroRaceField.getSelectionModel.selectedItemProperty().value))
      errorMessage += "Please Choose A Race!\n"

    if (nullChecking(heroHPField.text.value))
      errorMessage += "Please Enter A Value For Health!\n"
    else {
      try {
        Integer.parseInt(heroHPField.getText());
      } catch {
        case e: NumberFormatException =>
          errorMessage += "Health is Not Valid (must be an integer)!\n"
      }
    }

    if (nullChecking(heroArmourField.text.value))
      errorMessage += "Please Enter A Value For Armour!\n"
    else {
      try {
        Integer.parseInt(heroArmourField.getText());
      } catch {
        case e: NumberFormatException =>
          errorMessage += "Armour is Not Valid (must be an integer)!\n"
      }
    }

    if (nullChecking(heroSpeedField.text.value))
      errorMessage += "Please Enter A Value For Speed!\n"
    else {
      try {
        Integer.parseInt(heroSpeedField.getText());
      } catch {
        case e: NumberFormatException =>
          errorMessage += "Speed is Not Valid (must be an integer)!\n"
      }
    }

    if (nullChecking(heroBaseDamageField.text.value))
      errorMessage += "Please Enter A Value For Base Damage!\n"
    else {
      try {
        Integer.parseInt(heroBaseDamageField.getText());
      } catch {
        case e: NumberFormatException =>
          errorMessage += "Base Damage is Not Valid (must be an integer)!\n"
      }
    }

    if (errorMessage.length() == 0) {
      return true;
    } else {
      // Show the error message.
      val alert = new Alert(Alert.AlertType.Error) {
        initOwner(dialogStage)
        title = "Invalid Fields"
        headerText = "Please correct invalid fields"
        contentText = errorMessage
      }.showAndWait()

      return false;
    }
  }
}
