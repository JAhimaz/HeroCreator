package com.tiltedgear.herocreator

import com.tiltedgear.herocreator.model.Hero
import com.tiltedgear.herocreator.util.Database
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
  heroData += new Hero("Mei", "Damage", "Chinese Federation", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut nec massa id magna ultrices euismod vitae et odio. Pellentesque fringilla magna ac tellus ornare, eget tempus nisi volutpat. Nam pulvinar blandit ipsum, ut vehicula turpis luctus vel. Quisque feugiat nunc odio. Duis varius elit ipsum, a aliquam lacus placerat ut. In ut augue vel elit lacinia sodales non ut nunc. Aenean finibus nisl metus, ut tincidunt erat molestie vitae. Fusce placerat mauris at felis fermentum egestas. Proin dignissim, metus commodo consectetur egestas, enim tellus tincidunt orci, et gravida nibh nulla at nisl. Maecenas dictum, ex sed faucibus tempus, nisi lorem blandit felis, sed venenatis sem odio feugiat purus. Cras elit orci, venenatis sit amet nibh at, convallis tempus odio. Vivamus facilisis massa et tellus volutpat varius. Sed nisi ipsum, consectetur at elit dictum, accumsan tristique dolor. Etiam cursus, ante quis ultricies dictum, eros velit maximus nulla, sit amet bibendum tortor nulla et orci. Cras facilisis commodo nisl, vitae scelerisque dui sollicitudin vitae. ", "Social Worker", "Chinese", 100, 250, 50, 75)
  heroData += new Hero("John", "Damage", "Chinese Federation", "Lorem Ipsum", "Social Worker", "Chinese", 100, 250, 50, 75)
  heroData += new Hero("Timmy", "Damage", "Chinese Federation", "Lorem Ipsum", "Social Worker", "Chinese", 100, 250, 50, 75)
  heroData += new Hero("Test", "Damage", "Chinese Federation", "Lorem Ipsum", "Social Worker", "Chinese", 100, 250, 50, 75)

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
