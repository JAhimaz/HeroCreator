package com.tiltedgear.herocreator
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._
import scalafxml.core.{FXMLLoader, FXMLView, NoDependencyResolver}
import javafx.{scene => jfxs}
import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType
import scalafx.stage.StageStyle

object HeroApp extends JFXApp {

  val resource = getClass.getResource("view/CreatorOverview.fxml")
  val loader = new FXMLLoader(resource, NoDependencyResolver)
  loader.load()
  val roots = loader.getRoot[jfxs.layout.AnchorPane]

  stage = new PrimaryStage{
      title = "Hero Creator | TiltedGear Studios"
      resizable = false
      scene = new Scene{
        root = roots
      }
  }

  stage.initStyle(StageStyle.Undecorated)
}
