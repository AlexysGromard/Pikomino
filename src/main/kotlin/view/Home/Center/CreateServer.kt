package view.Home.Center

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.Slider
import javafx.scene.control.TextField
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import view.components.SmallTitle
import view.components.SubTitle
import view.components.Title
import view.components.WhiteButton

class CreateServer() : HomeCenter(false) {

    var slider : Slider
    var nombre : Label
    var button : WhiteButton
    init {
        val title = Title()

        val labelQuote = Label("Draw the worms from the dice !")
        labelQuote.setTextFill(Color.web("#FFFFFF"))
        val HelveFont = Font.loadFont("file:src/main/kotlin/view/fonts/helvetica/Helvetica.ttf", 20.0)
        labelQuote.font = HelveFont


        val text = Label("How many players at most ?")
        text.font = Font("Helvetica", 24.0)
        text.textFill = Color.web("#FBFBF2")

        var hbox = HBox()

        slider = Slider(2.0, 4.0, 2.0)
        slider.prefWidth = 400.0

        slider.prefHeight = 3.0


        nombre = Label("2")
        nombre.font =  Font("Helvetica", 24.0)
        nombre.padding = Insets(0.0, 0.0, 0.0, 7.0)
        nombre.textFill = Color.web("#FBFBF2")
        hbox.children.add(slider)
        hbox.children.add(nombre)
        hbox.alignment = Pos.CENTER

        button = WhiteButton("Validate")

        this.alignment = Pos.TOP_CENTER
        this.maxWidth = 800.0

        this.spacing = 20.0

        this.children.add(title)
        this.children.add(labelQuote)
        this.children.add(text)
        this.children.add(hbox)
        this.children.add(button)
    }
    override fun fixeButton(listControleur: Array<EventHandler<ActionEvent>>) {
        button.onAction = listControleur[0]
    }
}