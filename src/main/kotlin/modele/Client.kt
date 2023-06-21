package modele

import iut.info1.pickomino.Connector
import iut.info1.pickomino.data.DICE
import iut.info1.pickomino.data.STATUS
import javafx.animation.Animation
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.util.Duration
import java.security.cert.TrustAnchor

class Client(game: Game) {

    var id : Int? = null
    var key : Int? = null
    var connected : Boolean = false
    var connect : Connector? = null
    var game : Game = game
    var nbJoueur : Int = 0

    var canRoll = false
    var cankeepDice = false
    var cankeepPICKO = false



    fun CreateGame(nbJoueur : Int,numJoueur:Int){
        println("Hello students !!!")
        this.connect = Connector.factory("172.26.82.76", "8080")
        if (connect != null){
            println("Parties actives sur le serveur = ${connect!!.listOfGameIds()}")
            val identification = connect!!.newGame(nbJoueur)
            this.nbJoueur = nbJoueur
            this.id = identification.first
            this.key = identification.second

            for ( numJ in 1..nbJoueur){
                if (numJ == numJoueur){
                    game.addPlayer(Player(numJ,true))
                }
                else{
                    game.addPlayer(Player(numJ,false))
                }
            }

            connected = true
        }
    }

    /**
     * Rejoint une partie en se connectant à un serveur avec l'ID de la partie, la clé d'accès et le nombre de joueurs.
     *
     * @param id L'ID de la partie à rejoindre.
     * @param key La clé d'accès pour rejoindre la partie.
     * @param nbJoueur Le nombre de joueurs dans la partie.
     */
    fun JoinGame(id:Int, key:Int,NbJoueur :Int){
        // Établir une connexion avec le serveur
        this.connect = Connector.factory("172.26.82.76", "8080")

        // Définir les attributs de l'objet courant
        this.id = id
        this.key = key
        this.nbJoueur = nbJoueur

        // Marquer la connexion comme établie
        connected = true
    }

    fun update(){
        if (connected != null && id != null && key != null && connect != null){
            canRoll = false
            cankeepDice = false
            cankeepPICKO = false



                var currentGame = connect!!.gameState(this.id!!, this.key!!)

                var ActualStatu =  currentGame.current.status

                if (currentGame.current.player+1 == game.numérojoueur){
                    if (( currentGame.current.status == STATUS.ROLL_DICE || currentGame.current.status == STATUS.ROLL_DICE_OR_TAKE_PICKOMINO)) {
                        canRoll = true
                    }
                    if (currentGame.current.status == STATUS.KEEP_DICE){
                        cankeepDice = true
                    }
                    if (currentGame.current.status == STATUS.ROLL_DICE_OR_TAKE_PICKOMINO || currentGame.current.status == STATUS.TAKE_PICKOMINO){
                        cankeepPICKO = true
                    }

                }

                else{
                    game.setDice(currentGame.current.rolls,currentGame.current.keptDices)
                    game.setPickomino(currentGame.accessiblePickos())


                    var playerList = game.playerList()
                    var pickominoOfplayer = currentGame.pickosStackTops()
                    for ( numj in 0..playerList.size-1){
                        if (!playerList[numj].localPlayer && numj+1 != currentGame.current.player && pickominoOfplayer[numj] != 0)  playerList[numj].topPickominoIs(Pickomino(pickominoOfplayer[numj]))

                    }
                }

        }
    }

    /**
     * Garde un dé spécifié en convertissant sa valeur entière en dé réel à l'aide de la méthode convertIntintoDice().
     * Si le dé n'a pas déjà été choisi, la fonction envoie la requête "keepDices" au serveur.
     *
     * @param valeur La valeur entière du dé à garder.
     */
    fun keepDice(valeur: Int) {
        // Convertir la valeur entière en dé réel à l'aide de la méthode convertIntintoDice()
        val v = game.convertIntintoDice(valeur)

        // Vérifier si le dé n'a pas déjà été choisi
        if (v !in game.diceChosen) {
            // Envoyer la requête "keepDices" au serveur avec l'ID de la partie et la clé d'accès
            connect!!.keepDices(id!!, key!!, v)
        }
    }

    /**
     * Garde un Pickomino spécifié en vérifiant s'il est présent soit dans la liste des Pickominos disponibles (getPickos()),
     * soit dans la liste des Pickominos du joueur actuel (pickominoPlayer()).
     * Si le Pickomino est présent dans l'une de ces listes, la fonction envoie la requête "takePickomino" au serveur.
     *
     * @param valeur La valeur du Pickomino à garder.
     */
    fun keepPickomino(valeur :Int){
        // Affiche les informations pour le débogage
        println("$valeur : ${game.getPickos()} : ${game.pickominoPlayer()}")
        println("${Pickomino(valeur) in game.getPickos()} :: ${Pickomino(valeur) in game.pickominoPlayer()}")

        // Vérifie si le Pickomino est présent dans la liste des Pickominos disponibles ou dans la liste du joueur actuel
        if (Pickomino(valeur) in game.getPickos() || Pickomino(valeur) in game.pickominoPlayer()){
            // Envoie la requête "takePickomino" au serveur avec l'ID de la partie, la clé d'accès et la valeur du Pickomino
            connect!!.takePickomino(id!!,key!!,valeur)
        }

    }




}