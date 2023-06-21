package view.Home.Center

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.paint.Color
import javafx.scene.text.Font
import view.components.SubTitle
import view.components.Title
import view.components.WhiteButton

class CreateOrJoin() : HomeCenter(false) {

    val ButtonCreate : WhiteButton
    val ButtonJoin : WhiteButton


    init {
        val title = Title()
        val labelQuote = Label("Draw the worms from the dice !")
        labelQuote.setTextFill(Color.web("#FFFFFF"))
        val HelveFont = Font.loadFont("file:src/main/kotlin/view/fonts/helvetica/Helvetica.ttf", 20.0)
        labelQuote.font = HelveFont


        ButtonCreate = WhiteButton("Create a server")
        ButtonJoin = WhiteButton("Join a server")

        this.alignment = Pos.TOP_CENTER
        this.maxWidth = 800.0

        this.spacing = 20.0

        this.children.addAll(title, labelQuote, ButtonCreate, ButtonJoin)
    }

    override fun fixeButton(listControleur: Array<EventHandler<ActionEvent>>) {
        ButtonCreate.onAction = listControleur[0]
        ButtonJoin.onAction = listControleur[1]
    }
}