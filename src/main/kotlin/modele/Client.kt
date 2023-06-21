package modele

import iut.info1.pickomino.Connector
import iut.info1.pickomino.data.DICE
import iut.info1.pickomino.data.STATUS
import javafx.animation.Animation
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.util.Duration
import java.security.cert.TrustAnchor

/**
 *
 *Classe représentant un client pour le jeu.
 *
 *@property game Le jeu auquel le client est associé.
 *@property id L'ID du client.
 *@property key La clé du client.
 *@property connected Indique si le client est connecté ou non.
 *@property connect Le connecteur utilisé par le client pour la communication.
 *@property nbJoueur Le nombre de joueurs dans le jeu.
 *@property canRoll Indique si le client peut lancer les dés.
 *@property cankeepDice Indique si le client peut garder des dés.
 *@property cankeepPICKO Indique si le client peut prendre un Pickomino.
 */
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

    var gameFinish = false


    /**
     * creation  du jeux avec ma class game et creation de la connection avec le seveur
     *
     * @param nbJoueur  nombre de joueur dans la partie
     * @param numJoueur id du joueur local
     */
    fun CreateGame(nbJoueur : Int,numJoueur:Int){
        println("Hello students !!!")

        //creation du serveur avec l'IP et le port
        this.connect = Connector.factory("172.26.82.76", "8080")
        // verifie que l'objet existe
        if (connect != null){

            //montre tout les partie present dans le serveur
            println("Parties actives sur le serveur = ${connect!!.listOfGameIds()}")

            //parametrage de la nouvelle partie
            val identification = connect!!.newGame(nbJoueur)

            // Initialise les variables id, key et nbJoueur avec les informations de la partie créée
            this.nbJoueur = nbJoueur
            this.id = identification.first
            this.key = identification.second

            // Ajoute les joueurs à la partie en fonction de leur numéro, en marquant le joueur local
            for (numJ in 1..nbJoueur){
                if (numJ == numJoueur){
                    game.addPlayer(Player(numJ,true))
                }else{
                    game.addPlayer(Player(numJ,false))
                }
            }

            // Marque la connexion comme établie
            connected = true
        }
    }

    fun JoinGame(id:Int, key:Int,NbJoueur :Int){
        this.connect = Connector.factory("172.26.82.76", "8080")

        // Définir les attributs de l'objet courant
        this.id = id
        this.key = key
        this.nbJoueur = NbJoueur
        connected = true
    }

    /**
     * Met a jour l'etat du jeu
     */
    fun update(){
        // Vérifie si les variables connected, id, key et connect ne sont pas nulles
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
                // Met à jour les dés et les Pickominos du joueur local avec les informations du serveur
                game.setDice(currentGame.current.rolls,currentGame.current.keptDices)
                game.setPickomino(currentGame.accessiblePickos())


                    var playerList = game.playerList()
                    var pickominoOfplayer = currentGame.pickosStackTops()
                    for ( numj in 0..playerList.size-1){
                        if (!playerList[numj].localPlayer && numj+1 != currentGame.current.player && pickominoOfplayer[numj] != 0)  playerList[numj].topPickominoIs(Pickomino(pickominoOfplayer[numj]))

                    }
                }

                if(ActualStatu == STATUS.GAME_FINISHED){
                    gameFinish = true
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