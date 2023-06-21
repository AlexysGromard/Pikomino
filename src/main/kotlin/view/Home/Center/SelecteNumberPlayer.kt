package view.Home.Center

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.paint.Color
import javafx.scene.text.Font
import view.components.SmallTitle
import view.components.SubTitle
import view.components.Title
import view.components.WhiteButton
import javax.swing.ComboBoxEditor

class SelecteNumberPlayer(NumberPlayer : Int,isHost: Boolean,id : Int?,key :Int?) : HomeCenter(false){

    val NumberPlayer = NumberPlayer
    var isHost = isHost
    var id = id
    var key = key

    val button =  WhiteButton("Validate")
    val Combobox = ComboBox<String>()

    init {
        val title = Title()

        val labelQuote = Label("Draw the worms from the dice !")
        labelQuote.setTextFill(Color.web("#FFFFFF"))
        val HelveFont = Font.loadFont("file:src/main/kotlin/view/fonts/helvetica/Helvetica.ttf", 20.0)
        labelQuote.font = HelveFont

        val text = Label("with one play you want to be : ")
        text.font = Font("Helvetica", 24.0)
        text.textFill = Color.web("#FBFBF2")


        for (i in 1..this.NumberPlayer){
            Combobox.items.add("Player N°$i")
        }

        this.alignment = Pos.TOP_CENTER
        this.maxWidth = 800.0

        this.spacing = 20.0

        this.children.addAll(title,labelQuote,text,Combobox,button)

    }
    override fun fixeButton(listControleur: Array<EventHandler<ActionEvent>>) {
        button.onAction = listControleur[0]
    }
}