package com.tiltedgear.herocreator.view

import com.tiltedgear.herocreator.model.Hero
import com.tiltedgear.herocreator.HeroApp
import scalafx.scene.control.{ComboBox, TextArea, TextField}
import scalafx.stage.Stage
import scalafxml.core.macros.sfxml
import scalafx.event.ActionEvent
import scalafx.Includes._

class CreatorOverviewController(
  private val heroNameField : TextField,
  private val heroRoleField : ComboBox.type ,
  private val heroAffiliationField : ComboBox.type,
  private val heroLoreField : TextArea,
  private val heroOccupationField : TextField,
  private val heroNationalityField : ComboBox.type,
  private val heroHPField : TextField,
  private val heroArmourField : TextField,
  private val heroSpeedField : TextField,
  private val heroBaseDamageIField : TextField
) {

  var         dialogStage : Stage  = null
  private var _hero : Hero = null
  var         okClicked = false

  def hero = _hero
  def hero_=(x : Hero) {
    _hero = x

    heroNameField.text = _hero.heroName.value
//    heroRoleField.text = _hero.heroRole.value
//    heroAffiliationField.text = _hero.heroAffiliation.value
    heroLoreField.text = _hero.heroLore.value
    heroOccupationField.text = _hero.heroOccupation.value
//    heroNationalityField.text = _hero.heroNationality.value
    heroHPField.text = _hero.heroHealth.value.toString
    heroArmourField.text = _hero.heroArmour.value.toString
    heroSpeedField.text = _hero.heroSpeed.value.toString
    heroBaseDamageIField.text = _hero.heroBaseDamage.value.toString
  }

  def handleOk(action :ActionEvent){

/*    if (isInputValid()) {
      _person.firstName <== firstNameField.text
      _person.lastName  <== lastNameField.text
      _person.street    <== streetField.text
      _person.city      <== cityField.text
      _person.postalCode.value = postalCodeField.getText().toInt
      _person.date.value       = birthdayField.text.value.parseLocalDate;

      okClicked = true;
      dialogStage.close()
    }*/
    okClicked = true //Temporary
  }

  def handleCancel(action :ActionEvent) {
    dialogStage.close();
  }

/*  def nullChecking (x : String) = x == null || x.length == 0*/

 /* def isInputValid() : Boolean = {
    var errorMessage = ""

    if (nullChecking(firstNameField.text.value))
      errorMessage += "No valid first name!\n"
    if (nullChecking(lastNameField.text.value))
      errorMessage += "No valid last name!\n"
    if (nullChecking(streetField.text.value))
      errorMessage += "No valid street!\n"
    if (nullChecking(postalCodeField.text.value))
      errorMessage += "No valid postal code!\n"
    else {
      try {
        Integer.parseInt(postalCodeField.getText());
      } catch {
        case e : NumberFormatException =>
          errorMessage += "No valid postal code (must be an integer)!\n"
      }
    }
    if (nullChecking(cityField.text.value))
      errorMessage += "No valid city!\n"
    if (nullChecking(birthdayField.text.value))
      errorMessage += "No valid birtday!\n"
    else {
      if (!birthdayField.text.value.isValid) {
        errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
      }
    }

    if (errorMessage.length() == 0) {
      return true;
    } else {
      // Show the error message.
      val alert = new Alert(Alert.AlertType.Error){
        initOwner(dialogStage)
        title = "Invalid Fields"
        headerText = "Please correct invalid fields"
        contentText = errorMessage
      }.showAndWait()

      return false;
    }
  }*/
}
