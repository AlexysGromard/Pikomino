package view.Home.Center

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.paint.Color
import javafx.scene.text.Font
import view.components.SmallTitle
import view.components.SubTitle
import view.components.Title
import view.components.WhiteButton

class JoinServeur() : HomeCenter(false) {

    val button : WhiteButton

    val textFilierid : TextField
    val textFilierkey : TextField
    init {
        val title = Title()
        val labelQuote = Label("Draw the worms from the dice !")
        labelQuote.setTextFill(Color.web("#FFFFFF"))
        val HelveFont = Font.loadFont("file:src/main/kotlin/view/fonts/helvetica/Helvetica.ttf", 20.0)
        labelQuote.font = HelveFont

        val textid = Label("What is the ID :")
        textid.font = Font("Helvetica", 24.0)
        textid.textFill = Color.web("#FBFBF2")

        textFilierid = TextField()
        textFilierid.promptText = "15684"

        val textkey = Label("What is the key :")
        textkey.font = Font("Helvetica", 24.0)
        textkey.textFill = Color.web("#FBFBF2")

        textFilierkey = TextField()
        textFilierkey.promptText = "123"

        button = WhiteButton("Validate")

        this.alignment = Pos.TOP_CENTER
        this.maxWidth =500.0

        this.spacing = 20.0

        this.children.addAll(title,labelQuote,textid,textFilierid,textkey,textFilierkey,button)

    }
    override fun fixeButton(listControleur: Array<EventHandler<ActionEvent>>) {
        button.onAction = listControleur[0]
    }
}