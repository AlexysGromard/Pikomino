package controleur.Game

import iut.info1.pickomino.data.DICE
import javafx.animation.Timeline
import modele.Client
import modele.Pickomino
import view.MainView
import view.components.Pawn
import view.endView.EndView
import view.game.GameView

class ActualiserGameView(mainvue : MainView,vue: GameView, model : Client) {

    val vue = vue
    val model = model
    val mainvue = mainvue
    var timeline :Timeline? = null

    val id :Int = model.id!!
    val key :Int = model.key!!




    fun acctualiser(){
        model.update()
        //println(model.game.diceKept)

        var topPokomino = model.connect!!.gameState(id,key).pickosStackTops()
        var midelPokomino = model.connect!!.gameState(id,key).accessiblePickos()

        var isMyTurne = model.canRoll

        var dicerolls = model.connect!!.gameState(id,key).current.rolls
        var Intdicerolls = model.game.convertArrayDiceIntoInt(dicerolls.toTypedArray())

        var diceKept = model.connect!!.gameState(id,key).current.keptDices
        var IntdiceKept = model.game.convertArrayDiceIntoInt(diceKept.toTypedArray())

        model.game.setDice(dicerolls,diceKept)
        model.game.setPickomino(midelPokomino)

        if( model.estmodifier){
            vue.UpDatePickomino(midelPokomino.toTypedArray())
            vue.UpDatePickominoPlayer(topPokomino.toTypedArray())

            if (isMyTurne){
                vue.can_play()
            }else{
                vue.cant_play()
            }
            vue.pickoMessage.text = "Player N°${model.connect!!.gameState(id,key).current.player +1 } is playing"

            vue.UpDateDiceRoll(Intdicerolls,ControleurButtonKeepDice(vue,model),IntdiceKept,model.cankeepDice)
            vue.UpDateDiceKeep(IntdiceKept)




            var ValueDice = model.game.AllDiceNumber()
            vue.countDice.text = "count : ${ValueDice.toString()}"
            if (21 <= ValueDice && DICE.worm in diceKept){
                vue.UpDateSelectionPickomino(ValueDice,ControleurPickomino(vue,model))
                vue.UpDateSelectionPickominoPlayer(ValueDice,ControleurPickomino(vue,model))
            }


        }
        
        if (model.gameFinish ){
            model.update()
            var ListePawnPlayer = mutableListOf<MutableList<Pawn>>()
            var ListePickominoPlayer = model.game.playerList()

            for (p in 0..ListePickominoPlayer.size-1){
                ListePawnPlayer.add(mutableListOf())

                for (PickominoPlayer in ListePickominoPlayer[p].allPickomino()){

                    ListePawnPlayer[p].add(Pawn(PickominoPlayer.getValue()))
                }
            }

            val NewPage = EndView(ListePawnPlayer)
            NewPage.fixButtonMenu(ControleurButtonMenu(mainvue,mainvue.stage,timeline!!))
            mainvue.updateView(NewPage)
        }

    }

    fun getTimeline(timeline: Timeline){
        this.timeline = timeline
    }


}


