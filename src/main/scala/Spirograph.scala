package net.nineclue.spirograph

import javafx.application.Application
import javafx.stage.Stage
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.layout.{HBox, VBox}
import javafx.scene.Scene
import javafx.scene.shape.Circle
import javafx.scene.image.ImageView

object Spirograph {
	val (width, height) = (800, 600)
	def main(as:Array[String]) = Application.launch(classOf[Spirograph], as:_*)
}

class Spirograph extends Application {
	@FXML
	var dImg:ImageView = _
	@FXML
	var cclO:Circle = _
	@FXML
	var cclI:Circle = _
	@FXML
	var dotO:Circle = _
	@FXML
	var dotI:Circle = _
	@FXML
	var dotP:Circle = _

	def start(ps:Stage) = {
		var loader = new FXMLLoader(getClass.getResource("/main.fxml"))
		loader.setController(this)
		val root = loader.load.asInstanceOf[HBox]
		root.setStyle("-fx-font-family: D2Coding; -fx-font-size: 16")
		ps.setScene(new Scene(root, Spirograph.width, Spirograph.height))
		ps.setTitle("Spirograph")
		ps.show
	}
}