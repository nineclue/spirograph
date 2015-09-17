package net.nineclue.spirograph

import javafx.application.Application
import javafx.stage.Stage
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.layout.{HBox, VBox}
import javafx.scene.Scene
import javafx.scene.shape.Circle
import javafx.scene.image.{ImageView, WritableImage}
import javafx.scene.paint.Color

object Spirograph {
	val (width, height) = (800, 600)
	def main(as:Array[String]) = Application.launch(classOf[Spirograph], as:_*)
}

class Spirograph extends Application {
	// FXML stuffs
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

	private def gcd(a:Int, b:Int):Int = if ((a % b) == 0) b else gcd(b, a%b)
	private def mapMaker(minA:Double, maxA:Double, minB:Double, maxB:Double)(a:Double) =
		(a - minA) * (maxB - minB) / (maxA - maxB) + minB

	def start(ps:Stage) = {
		var loader = new FXMLLoader(getClass.getResource("/main.fxml"))
		loader.setController(this)
		val root = loader.load.asInstanceOf[HBox]
		root.setStyle("-fx-font-family: D2Coding; -fx-font-size: 16")

		val mainScene = new Scene(root, Spirograph.width, Spirograph.height)
		ps.setScene(mainScene)
		ps.setTitle("Spirograph 스파이로그래프")
		ps.setResizable(false)

		initImage
		ps.show
	}

	private val image = new WritableImage(600, 600)
	def initImage() = {
		dImg.setImage(image)
		/*
 		val iWriter = image.getPixelWriter
		val cMap = mapMaker(0, 600, 0, 360)_
		Range(0, 600).foreach(i => {
				iWriter.setColor(i, 300, Color.hsb(cMap(i), 1.0, 1.0))
			})
		*/
		drawSpiro(image, 220, 65, 0.8)
	}

	def drawSpiro(im:WritableImage, outr:Double, inr:Double, ratio:Double) = {
		def d2r(d:Double) = d * math.Pi / 180
		val p = ratio * inr
		val pCon = (outr - inr) / inr
		val cd = gcd(outr.toInt, inr.toInt)
		val rounds = (outr / cd) * 360
		val writer = im.getPixelWriter
		(0 to rounds.toInt).foreach(a => {
			val r = d2r(a)
			val x = (outr - inr) * math.cos(r) + p * math.cos(pCon*r)
			val y = (outr - inr) * math.sin(r) - p * math.sin(pCon*r)
			writer.setColor(x.toInt + 300, y.toInt + 300, Color.BLACK)
			})
	}
}
