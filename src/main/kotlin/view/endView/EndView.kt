package view.endView

import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import view.View
import view.components.Pawn

class EndView(listpickominoPlayer: MutableList<MutableList<Pawn>>) : View() {

    val listpickominoPlayer = listpickominoPlayer

    init {
        //top
        //this.top =





        //center
        val vbox = VBox()
        val sumworm = mutableListOf<Int>()
        val rulesFont = Font.loadFont("file:src/main/kotlin/view/fonts/helvetica/Helvetica.ttf", 19.0)

        var numj = 0
        for (pickominoPlayer in  listpickominoPlayer){

            numj++
            val vbocplayer = VBox()
            val nomjoueur = Label("Player N°$numj")
            nomjoueur.styleClass.add("txt")

            nomjoueur.font = rulesFont
            val hboxpicko = HBox()

            var sum = 0
            for (picko in pickominoPlayer) {
                hboxpicko.children.add(picko)
                sum += picko.thworm
            }

            vbocplayer.children.addAll(nomjoueur,hboxpicko)
            sumworm.add(sum)
            vbocplayer.alignment = Pos.CENTER
            vbox.children.add(vbocplayer)
        }


        var max = sumworm[0]
        for (i in 1..sumworm.size-1){
            if (max < sumworm[i]){
                max = sumworm[i]
            }
        }
        var joueurGagner :String = ""
        var nbGagnant = 0
        for (i in 0..sumworm.size-1){
            if (sumworm[i] == max){
                nbGagnant++
                joueurGagner += " Joueur N°${i+1},"
            }
        }

        joueurGagner.dropLast(1)

        val MessageVictoire : Label
        if (nbGagnant > 1){
            MessageVictoire = Label("Les ${joueurGagner} On Gagnés")
        }
        else{
            MessageVictoire = Label("Le ${joueurGagner} a Gagnés")
        }
        MessageVictoire.font = rulesFont
        vbox.children.add(MessageVictoire)
        vbox.alignment = Pos.CENTER

        this.center = vbox


    }
}