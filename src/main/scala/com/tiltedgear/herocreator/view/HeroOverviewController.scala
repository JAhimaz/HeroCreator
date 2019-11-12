package com.tiltedgear.herocreator.view

import java.awt.image.BufferedImage
import java.io.{File, IOException, InputStream}

import javax.imageio.ImageIO
import javax.imageio.ImageIO
import com.tiltedgear.herocreator.model.Hero
import scalafx.scene.control.{Alert, Button, Label, TableColumn, TableView, TextInputDialog}
import scalafxml.core.macros.sfxml
import com.tiltedgear.herocreator.HeroApp
import com.tiltedgear.herocreator.util.HeroCardImages
import com.tiltedgear.herocreator.util.HeroCardImages.getClass
import com.tiltedgear.herocreator.util.factory.HeroFactory
import scalafx.Includes._
import scalafx.application.Platform
import scalafx.event.ActionEvent
import scalafx.beans.property.StringProperty
import scalafx.embed.swing.SwingFXUtils
import scalafx.geometry.Rectangle2D
import scalafx.scene.SnapshotParameters
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.image.{Image, ImageView, WritableImage}
import scalafx.scene.layout.AnchorPane
import scalafx.scene.shape.Rectangle
import scalafx.scene.transform.Transform
import scalafx.stage.DirectoryChooser

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
  private val roleIcon : ImageView,

  private val statsLabel : Label,
  private val staticHealthLabel : Label,
  private val staticArmourLabel : Label,
  private val staticSpeedLabel : Label,
  private val staticDamageLabel : Label,

  private val heroPane : AnchorPane,
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

        val chosenRoleURI : InputStream = HeroCardImages.fetchRoleLogo(hero.heroRole.value)
        val roleImage : Image = new Image(chosenRoleURI)
        roleIcon.setImage(roleImage)

        val chosenFactionURI : InputStream = HeroCardImages.fetchFactionLogo(hero.heroAffiliation.value)
        val factionImage : Image = new Image(chosenFactionURI)
        factionLogo.setImage(factionImage)

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

  def handleGenerateHero(action : ActionEvent) = {
    var errorMessage = ""
    try {
      val dialog = new TextInputDialog(defaultValue = "5") {
        initOwner(HeroApp.stage)
        title = "Mass Hero Generation"
        headerText = "Generate Multiple Heroes At Once"
        contentText = "Number Of Heroes To Create:"
      }
      val result = dialog.showAndWait()
      result match {
        case Some(noOfHeroes) =>
          Integer.parseInt(noOfHeroes)
          if(noOfHeroes.toInt > 25){
            errorMessage += "Cannot Generate Over 25 Heroes To Prevent Crashing"
          }else if(noOfHeroes.toInt < 1){
            errorMessage += "Please Choose A Number Greater Than 0"
          }else{
            HeroFactory.Generator(noOfHeroes.toInt)
          }
        case None =>
      }
    } catch {
      case e: NumberFormatException =>
        errorMessage += "Health is Not Valid (must be an integer)!\n"
    }

    if (errorMessage.length() == 0) {
    } else {
      // Show the error message.
      val alert = new Alert(Alert.AlertType.Error) {
        initOwner(HeroApp.stage)
        title = "Invalid Fields"
        headerText = "Please correct invalid fields"
        contentText = errorMessage
      }.showAndWait()
    }

  }

  def handleSnapshotHero(action : ActionEvent) = {
    val selectedIndex = heroTable.selectionModel().selectedIndex.value
    val selectedHero = heroTable.selectionModel().selectedItem.value

    if(selectedIndex >= 0){
      val directoryChooser : DirectoryChooser = new DirectoryChooser()
      val selectedDirectory : File = directoryChooser.showDialog(HeroApp.stage)

      val node = heroPane
      val sp : SnapshotParameters = new SnapshotParameters()
      sp.setTransform(Transform.scale(5, 5))

      val snapshot : WritableImage = node.snapshot(sp, null)
      val name : String = selectedHero.heroName.value
      val file : File = new File(selectedDirectory.getAbsolutePath + "/" + name + ".png")
      val bImage : BufferedImage = SwingFXUtils.fromFXImage(snapshot, null)

      try {
        ImageIO.write(bImage, "png", file)
        new Alert(AlertType.Information) {
          initOwner(HeroApp.stage)
          title = "HeroCreator | Image Saved"
          headerText = "Successfully Saved Image"
          contentText = "The Image Was Saved At " + file.getAbsolutePath
        }.showAndWait()
      }
      catch {
        case exception: IOException =>
          print("Error Saving Image, Reason: " + exception)
      }
    }else {
      val alert = new Alert(AlertType.Warning) {
        initOwner(HeroApp.stage)
        title = "No Selection"
        headerText = "No Hero Selected"
        contentText = "Please select a hero in the table."
      }.showAndWait()
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

}
