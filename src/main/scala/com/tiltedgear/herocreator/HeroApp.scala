package com.tiltedgear.herocreator

import java.beans.EventHandler

import com.tiltedgear.herocreator.model.Hero
import com.tiltedgear.herocreator.util.Database
import com.tiltedgear.herocreator.util.factory.HeroFactory
import com.tiltedgear.herocreator.view.{CreatorOverviewController, HeroOverviewController}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._
import scalafxml.core.{FXMLLoader, FXMLView, NoDependencyResolver}
import javafx.{scene => jfxs}
import scalafx.collections.ObservableBuffer
import scalafx.scene.input.MouseEvent
import scalafx.stage.{Modality, Stage, StageStyle}

object HeroApp extends JFXApp {
  Database.setupDB()
  val heroData = new ObservableBuffer[Hero]()

  var screenPosX: Double = 0
  var screenPosY: Double = 0

  /*HeroFactory.Generator(15)*/

  heroData ++= Hero.getAllHeroes

  val resource = getClass.getResource("view/HeroOverview.fxml")
  val loader = new FXMLLoader(resource, NoDependencyResolver)
  loader.load()
  val roots = loader.getRoot[jfxs.layout.AnchorPane]
  val cssResource = getClass.getResource("view/stylesheet.css")
  roots.stylesheets = List(cssResource.toExternalForm)
  val control = loader.getController[HeroOverviewController#Controller]()

  stage = new PrimaryStage{
      title = "Hero Creator | TiltedGear Studios"
      initStyle(StageStyle.Undecorated)
      resizable = false
      scene = new Scene{
        root = roots
      }
  }


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

/*
val appWindow = HeroApp.stage


def getMouseLocation(mouseEvent: MouseEvent){

}

  def moveWindow(mouseDragEvent: MouseDragEvent): Unit ={
  appWindow.x_= (appWindow.getX - screenPosX)
  appWindow.y_= (appWindow.getY - screenPosY)
}*/
