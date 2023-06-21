package controleur

import controleur.Game.ActualiserGameView
import controleur.Game.ContoleurButtonRollsDice
import controleur.Game.ControleurButtonMenu
import javafx.animation.Animation
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Alert
import javafx.stage.Stage
import javafx.util.Duration
import modele.Client
import modele.Game
import view.Home.Center.SelecteNumberPlayer
import view.Home.HomeView
import view.MainView
import view.View
import view.game.GameView
import java.lang.Exception

class ControleurLancerPartie(vue : HomeView, mainvue : MainView) : EventHandler<ActionEvent> {

    var mainvue = mainvue
    var vue = vue
    var timeline : Timeline? = null
    override fun handle(event: ActionEvent?) {

        val actualvue = vue.center as SelecteNumberPlayer
        val nbPlayer = actualvue.NumberPlayer

        val actual = actualvue.Combobox.value

        if (actual == null){
            val dialog = Alert(Alert.AlertType.INFORMATION)
            dialog.title="Error"
            dialog.headerText="Vous n'avez pas choisi votre numéro"
            dialog.showAndWait()
        }
        else{
            val actualPlayer = (actual.substring(actual.length-1)).toInt()



            var model = Client(Game(actualPlayer))

            if (actualvue.isHost) {
                model.CreateGame(nbPlayer,actualPlayer)
            }
            else{
                val id : Int = actualvue.id!!
                val key : Int = actualvue.key!!
                model.JoinGame(id,key,nbPlayer,actualPlayer)
            }

            val NewPage = GameView(nbPlayer,actualPlayer,model.id!!,model.key!!)
            mainvue.updateView(NewPage)

            updateLoop(NewPage,model)
            NewPage.fixButtonRolls(ContoleurButtonRollsDice(NewPage,model))
            NewPage.fixButtonMenu(ControleurButtonMenu(mainvue,vue.stage,timeline!!))
        }
    }

    fun updateLoop(vue: GameView, model : Client){
        var ActuGameView = ActualiserGameView(mainvue,vue,model)
        timeline = Timeline(KeyFrame(Duration.seconds(0.5), {
            ActuGameView.acctualiser()
            println(model.connect!!.gameState(model.id!!,model.key!!))
        }))
        timeline!!.cycleCount = Animation.INDEFINITE
        timeline!!.play()
    }
}