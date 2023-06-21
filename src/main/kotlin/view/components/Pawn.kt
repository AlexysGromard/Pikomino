package view.components

import javafx.animation.TranslateTransition
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.effect.DropShadow
import javafx.scene.effect.InnerShadow
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.util.Duration

class Pawn(value: Int) : StackPane() {

    var value = value
    val thworm : Int
    init {
        val rectangle = Rectangle()
        rectangle.width = 86.0
        rectangle.height = 165.0
        rectangle.fill = Color.web("#FBFBF3") // Background
        rectangle.arcHeight = 16.0 // Radius
        rectangle.arcWidth = 16.0 // Radius

        // Shadow
        val boxShadow = DropShadow()
        boxShadow.radius = 4.0
        boxShadow.color = Color.rgb(0, 0, 0, 0.25)

        val insetShadow1 = InnerShadow()
        insetShadow1.radius = 2.0
        insetShadow1.offsetX = 1.0
        insetShadow1.offsetY = 0.0
        insetShadow1.color = Color.rgb(0, 0, 0, 0.25)

        val insetShadow2 = InnerShadow()
        insetShadow2.radius = 4.0
        insetShadow2.offsetX = -5.0
        insetShadow2.offsetY = -5.0
        insetShadow2.color = Color.rgb(0, 0, 0, 0.25)

        rectangle.effect = boxShadow
        boxShadow.input = insetShadow1
        insetShadow1.input = insetShadow2

        // Value
        val valueLabel = Label(value.toString())
        valueLabel.textFill = Color.web("#0F1820")

        // Add Font
        val helveticaFont = Font.loadFont(
            "file:src/main/kotlin/view/fonts/helvetica/Helvetica-Bold.ttf",
            48.0)

        valueLabel.font = helveticaFont

        // Line
        val line = Rectangle()
        line.width = 70.0
        line.height = 3.0
        line.fill = Color.web("#0F1820")
        line.arcWidth = 5.0
        line.arcHeight = 5.0

        // Image
        //diffinire le nombre de worm

        if (value < 25) {
            thworm = 1
        } else if (value < 29) {
            thworm = 2
        } else if (value < 33) {
            thworm = 3
        } else {
            thworm = 4
        }

        // Créer une ImageView
        val imageView = ImageView()
        val thwormImage : Image

        when (thworm) {
            2 -> thwormImage = Image("file:src/main/kotlin/view/assets/thworm2.png")
            3 -> thwormImage = Image("file:src/main/kotlin/view/assets/thworm3.png")
            4 -> thwormImage = Image("file:src/main/kotlin/view/assets/thworm4.png")
            else -> thwormImage = Image("file:src/main/kotlin/view/assets/thworm1.png")
        }

        imageView.fitWidth = 60.0 // Largeur fixe de 200 pixels
        val imageRatio: Double = thwormImage.getWidth() / thwormImage.getHeight()
        val height = imageView.fitWidth / imageRatio
        imageView.fitHeight = height

        imageView.image = thwormImage

        // StackPane for line and value label
        val lineAndValuePane = VBox()
        lineAndValuePane.alignment = Pos.CENTER
        lineAndValuePane.children.addAll(valueLabel, line, imageView)
        VBox.setMargin(line, Insets(5.0, 0.0, 15.0, 0.0))

        // Set lineAndValuePane as the center node of the VBox
        this.alignment = Pos.CENTER
        this.children.addAll(rectangle, lineAndValuePane)
    }
    // Clickable() craete the border around the pickoPawn
    fun clickable(){
        var w = 86.0
        var h = 165.0
        val rectangle = Rectangle(w, h)
        rectangle.stroke = Color.web("#F5D849")
        rectangle.fill = Color.TRANSPARENT
        // Corner Radius
        rectangle.arcHeight = 16.0 // Radius
        rectangle.arcWidth = 16.0 // Radius

        // Cursor
        this.cursor = javafx.scene.Cursor.HAND

        rectangle.strokeWidth = 3.0
        this.children.add(rectangle)
    }

    // not_clickable() remove the border around the pickoPawn
    fun not_clickable(){
        this.cursor = javafx.scene.Cursor.DEFAULT
        this.children.removeIf { it is Rectangle && it.stroke == Color.web("#F5D849") && it.fill == Color.TRANSPARENT }
    }
}