package com.tiltedgear.herocreator
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._
import scalafxml.core.{NoDependencyResolver, FXMLView, FXMLLoader}
import javafx.{scene => jfxs}

object HeroApp extends JFXApp {
  val rootResource = getClass.getResource("view/RootLayout.fxml")
  val loader = new FXMLLoader(rootResource, NoDependencyResolver)

  loader.load();
  val roots = loader.getRoot[jfxs.layout.BorderPane]

  stage = new PrimaryStage{
    title = "Hero Creator | TiltedGear Studios"
    resizable = false
    scene = new Scene{
      root = roots
    }
  }

  def showAbilityCreatorOverview() = {
    val resource = getClass.getResource("view/CreatorOverview.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.Pane]
    this.roots.setCenter(roots)
  }

  showAbilityCreatorOverview()
}
