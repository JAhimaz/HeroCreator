package com.tiltedgear.herocreator

import com.tiltedgear.herocreator.model.Hero
import com.tiltedgear.herocreator.util.{Database, HeroFactory}
import com.tiltedgear.herocreator.view.{CreatorOverviewController, HeroOverviewController}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._
import scalafxml.core.{FXMLLoader, FXMLView, NoDependencyResolver}
import javafx.{scene => jfxs}
import scalafx.collections.ObservableBuffer
import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType
import scalafx.stage.{Modality, Stage, StageStyle}

object HeroApp extends JFXApp {

  Database.setupDB()
  val heroData = new ObservableBuffer[Hero]()

  /*Hardcoded Data*/
  HeroFactory.heroGenerator()

  val resource = getClass.getResource("view/HeroOverview.fxml")
  val loader = new FXMLLoader(resource, NoDependencyResolver)
  loader.load()
  val roots = loader.getRoot[jfxs.layout.AnchorPane]
  val cssResource = getClass.getResource("view/stylesheet.css")
  roots.stylesheets = List(cssResource.toExternalForm)
  val control = loader.getController[HeroOverviewController#Controller]()

  stage = new PrimaryStage{
      title = "Hero Creator | TiltedGear Studios"
      resizable = false
      scene = new Scene{
        root = roots
      }
  }

  stage.initStyle(StageStyle.Undecorated)

  def showCreatorOverview(hero: Hero) = {
    val resource = getClass.getResourceAsStream("view/CreatorOverview.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource)

    val roots2 = loader.getRoot[jfxs.layout.AnchorPane]
    val control2 = loader.getController[CreatorOverviewController#Controller]()
    roots2.stylesheets = List(cssResource.toExternalForm)
    val dialog = new Stage() {
      initModality(Modality.ApplicationModal)
      initOwner(stage)
      scene = new Scene {
        root = roots2
      }
    }

    dialog.initStyle(StageStyle.Undecorated)

    control2.dialogStage = dialog
    control2.hero = hero
    dialog.showAndWait()
    control2.okClicked
  }


}
