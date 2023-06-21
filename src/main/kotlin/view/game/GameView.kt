package view.game

import controleur.Game.ActualiserGameView
import iut.info1.pickomino.data.DICE
import javafx.animation.Animation
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.collections.ObservableList
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Cursor
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.util.Duration
import modele.Player
import org.controlsfx.control.spreadsheet.Grid

import view.View
import view.components.*
import java.util.Stack
import java.awt.event.MouseEvent as MouseEvent1

class GameView(NombresJoueur : Int, actualNumberPlayer : Int , id :Int,key :Int) : View() {
    //paramettre

    val nombreJoueurs = NombresJoueur
    val actualNumberPlayer = actualNumberPlayer

    val id = id
    val key = key


    // CenterPart (Header/Center/Footer)
    private val centerPart : BorderPane
    // HEADER
    private val headerCenter : VBox
    private val pageTitle : SmallTitle
    private val menuButton : TransparentButton

    // LEFT : Players and Desk
    private val playersList : VBox

    // RIGHT : Dices
    private val diceSection : VBox

    private val diceKeptSection : VBox
    private val diceKeptTitle : Label
    var countDice : Label
    val diceKept : GridPane

    private val dicePlayedSection : VBox
    private val dicePlayedTitle :Label
    val dicePlayed : GridPane

    // CENTER : Pickomino
    var pickominoSection : FlowPane

    // Bottom : PlayerSpace
    private val playerSpace : HBox
    private val rollDiceSection : VBox
    private val rollDiceBtn : Button
    private val circleBtn : Circle
    private val rollDiceImage : Image
    private val rollDiceImageView: ImageView
    private val rollDiceLabel : Label

    private var playerPawnSection : VBox
    var playerPawnPile : StackPane
    private var playerPawnDotted : Dotted
    var playerPawn : Pawn?
    private val playerPawnLabel : Label

    private val pickoSection : VBox
    val pickoMessage : Label
    private val pickoImage : Image



    init {
        // Padding page
        this.padding = Insets(19.0, 20.0, 10.0, 20.0)

        // CenterPart
        centerPart = BorderPane()

        // HEADER Init
        this.headerCenter = VBox()
        this.headerCenter.style = "-fx-alignment: center;"

        this.pageTitle = SmallTitle("Pickomino")

        this.menuButton = TransparentButton("Menu")
        val margin = Insets(8.0, 0.0, 0.0, 0.0)
        VBox.setMargin(menuButton, margin)

        this.headerCenter.children.addAll(pageTitle,menuButton)

        val headerRight = VBox()

        val labelid = Label("Id : $id")
        labelid.styleClass.add("txt")
        val labelkey = Label("Key : $key")
        labelkey.styleClass.add("txt")

        headerRight.children.addAll(labelid, labelkey)
        headerRight.alignment = Pos.TOP_RIGHT

        val header = BorderPane()
        header.center = headerCenter
        header.right =  headerRight
        centerPart.top = header

        // LEFT : PlayerList
        playersList = VBox()
        playersList.padding = Insets(0.0, 240.0, 0.0 , 0.0)

        playersList.alignment = Pos.TOP_CENTER

        // Loop into players
        val playersListName = mutableListOf<String>()
        for (numj in 1..NombresJoueur){

            if (numj != actualNumberPlayer){
                playersListName.add("Player N°$numj")
            }
        }

        for (playerFor in playersListName){
            // Player location
            val player = VBox()
            player.alignment = Pos.CENTER
            // PlayerName
            val playerName = Label(playerFor)
            playerName.textFill = Color.web("#FBFBF3")
            playerName.style = "-fx-font-size: 24;"
            playerName.padding = Insets(0.0, 0.0, 10.0, 0.0)

            // StackPane
            val pile = StackPane()

            // PlayerDotted
            val dotted = Dotted(84.0, 163.0) //Dotted

            pile.children.add(dotted)

            player.children.addAll(playerName, pile)
            VBox.setVgrow(player, Priority.ALWAYS)
            playersList.children.add(player)
        }

        this.left = playersList

        // RIGHT : Dices
        diceSection = VBox()
        diceSection.spacing = 40.0
        diceSection.alignment = Pos.BOTTOM_RIGHT
        diceSection.padding = Insets(0.0,0.0,50.0,0.0)

        // diceKeptSection
        diceKeptSection = VBox()
        diceKeptSection.alignment = Pos.TOP_RIGHT // Alignement à droite du VBox

        diceKeptTitle = Label("Dice kept")
        diceKeptTitle.style = "-fx-font-size: 24; -fx-font-weight: bold;"
        diceKeptTitle.textFill = Color.web("#FBFBF3")
        diceKeptTitle.padding = Insets(0.0, 0.0, 10.0, 0.0)


        diceKept = GridPane()
        diceKept.hgap = 8.0 // Espacement horizontal entre les colonnes
        diceKept.vgap = 8.0 // Espacement vertical entre les lignes
        diceKept.alignment = Pos.TOP_RIGHT
        // Création des carrés et ajout dans le GridPane
        for (row in 0 until 4) {
            for (col in 0 until 2) {
                // Stack pane (Dotted & dice)
                val stackPaneDice = StackPane()
                val dotted = Dotted(68.0, 68.0)

                stackPaneDice.children.add(dotted)
                diceKept.add(stackPaneDice, col, row)
            }
        }
        countDice = Label("d")
        //countDice
        diceKeptSection.children.addAll(diceKeptTitle,countDice,diceKept)

        // dicePlayedSection
        dicePlayedSection = VBox()
        dicePlayedSection.alignment = Pos.TOP_RIGHT // Alignement à droite du VBox

        dicePlayedTitle = Label("Dice played")
        dicePlayedTitle.style = "-fx-font-size: 24; -fx-font-weight: bold;"
        dicePlayedTitle.textFill = Color.web("#FBFBF3")
        dicePlayedTitle.padding = Insets(0.0, 0.0, 10.0, 0.0)

        dicePlayed = GridPane()
        dicePlayed.hgap = 8.0 // Espacement horizontal entre les colonnes
        dicePlayed.vgap = 8.0 // Espacement vertical entre les lignes
        dicePlayed.alignment = Pos.TOP_RIGHT
        for (row in 0 until 2) {
            for (col in 0 until 4) {
                // Stack pane (Dotted & dice)
                val stackPaneDice = StackPane()
                val dotted = Dotted(68.0, 68.0)

                stackPaneDice.children.add(dotted)
                dicePlayed.add(stackPaneDice, col, row)
            }
        }

        dicePlayedSection.children.addAll(dicePlayedTitle, dicePlayed)

        diceSection.children.addAll(diceKeptSection, dicePlayedSection)
        this.right = diceSection

        // CENTER : Pickomino
        pickominoSection = FlowPane()
        pickominoSection.hgap = 10.0
        pickominoSection.vgap = 10.0
        pickominoSection.maxWidth = 800.0

        UpDatePickomino(arrayOf(21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36))

        pickominoSection.alignment = Pos.CENTER
        centerPart.center = pickominoSection

        // BOTTOM : PlayerSpace
        playerSpace = HBox()
        playerSpace.alignment = Pos.CENTER
        playerSpace.spacing = 70.0

        // Roll Dice
        rollDiceSection = VBox()
        rollDiceSection.alignment = Pos.CENTER

        // RollDiceBTN
        rollDiceBtn = Button()
        circleBtn = Circle(50.0)
        circleBtn.fill = Color.web("#FBFBF3")
        // AddImage into the button
        rollDiceImage = Image("file:src/main/kotlin/view/assets/diceIcon.png")
        rollDiceImageView = ImageView(rollDiceImage)
        rollDiceImageView.fitWidth = 80.0 // Largeur de l'image
        rollDiceImageView.fitHeight = 80.0 // Hauteur de l'image


        rollDiceBtn.graphic = StackPane(circleBtn, rollDiceImageView) // Add circle and image into BTN
        rollDiceBtn.style = "-fx-background-color: transparent;" // Remove default background
        rollDiceBtn.cursor = Cursor.HAND

        rollDiceLabel = Label("Roll the dice")
        rollDiceLabel.textFill = Color.web("#FBFBF3")
        rollDiceLabel.style = "-fx-font-size: 20; -fx-font-weight: bold;"
        rollDiceLabel.padding = Insets(10.0 , 0.0 , 0.0,0.0)


        rollDiceSection.children.addAll(rollDiceBtn, rollDiceLabel)

        // playerPawnSection
        playerPawnSection = VBox() // Section
        playerPawnSection.alignment = Pos.CENTER
        playerPawnPile = StackPane() //Dotted and pawn
        playerPawnDotted = Dotted(84.0, 163.0) //Dotted

        playerPawnPile.children.add(playerPawnDotted)

        playerPawn = null// Pawn(28,4) //Correspond au Pikomino le plus récent (Ici c'est un test pour IHM)
        if (playerPawn != null){
            playerPawnPile.children.add(playerPawn)
        }
        playerPawnLabel = Label("Player N°$actualNumberPlayer")
        playerPawnLabel.textFill = Color.web("#FBFBF3")
        playerPawnLabel.style = "-fx-font-size: 24;"
        playerPawnLabel.padding = Insets(10.0, 0.0, 0.0, 0.0)

        playerPawnSection.children.addAll(playerPawnPile, playerPawnLabel)

        // Picko and message
        pickoSection = VBox()
        pickoSection.alignment = Pos.CENTER

        pickoMessage = Label("Tom is playing...")
        pickoMessage.textFill = Color.web("#FBFBF3")
        pickoMessage.style = "-fx-font-size: 20;"

        pickoImage = Image("file:src/main/kotlin/view/assets/worm-16 1.png")
        val pickoImageView = ImageView(pickoImage)

        pickoSection.children.addAll(pickoMessage,pickoImageView)


        playerSpace.children.addAll(rollDiceSection, playerPawnSection, pickoSection)

        centerPart.bottom = playerSpace


        this.center = centerPart
    }

    fun can_play(){
        rollDiceSection.children.removeAll(rollDiceBtn, rollDiceLabel)

        circleBtn.fill = Color.web("#F5D849")
        circleBtn.cursor = javafx.scene.Cursor.HAND

        rollDiceBtn.graphic = StackPane(circleBtn, rollDiceImageView)

        rollDiceSection.children.addAll(rollDiceBtn, rollDiceLabel)
    }
    fun cant_play(){
        rollDiceSection.children.removeAll(rollDiceBtn, rollDiceLabel)
        circleBtn.fill = Color.web("#FBFBF3")

        rollDiceBtn.graphic = StackPane(circleBtn, rollDiceImageView)

        rollDiceSection.children.addAll(rollDiceBtn, rollDiceLabel)
    }

    fun UpDatePickomino(listePickomino: Array<Int>){
        // CENTER : Pickomino
        pickominoSection.children.clear()

        for (pickomino in listePickomino) {
            val pickoPawn: Pawn
            pickoPawn = Pawn(pickomino)
            pickominoSection.children.add(pickoPawn)
        }
    }

    fun UpDatePickominoPlayer(listePickomino: Array<Int>) {
        val playerPawnValue = playerPawn?.value ?: 0 //Pour le joueur qui joue

        for (i in 0 until nombreJoueurs) {
            val currentPickominoValue = listePickomino[i]

            // Si I est le joueur qui joue
            if (actualNumberPlayer == i + 1 && listePickomino[i] != 0) {
                if (playerPawnPile.children.size == 2 && listePickomino[i] != playerPawnValue) {
                    playerPawnPile.children.removeIf { it is Pawn }
                }

                if (listePickomino[i] != playerPawnValue) {
                    playerPawn = Pawn(listePickomino[i])

                    // Supprimer les éléments existants avant l'ajout
                    playerSpace.children.removeAt(1) // Remove index 1
                    playerPawnSection.children.removeIf { it is StackPane }

                    playerPawnPile.children.add(playerPawn)
                    playerPawnSection.children.add(0, playerPawnPile)
                    playerSpace.children.add(1, playerPawnSection)
                    centerPart.bottom = playerSpace
                }
            } else if (actualNumberPlayer == i + 1 && listePickomino[i] == 0){
                if (playerPawnPile.children.size == 2 && listePickomino[i] != playerPawnValue) {
                    playerPawnPile.children.removeIf { it is Pawn }
                }
                // Supprimer les éléments existants avant l'ajout
                playerSpace.children.removeAt(1) // Remove index 1
                playerPawnSection.children.removeIf { it is StackPane }

                playerPawnSection.children.add(0, playerPawnPile)
                playerSpace.children.add(1, playerPawnSection)
                centerPart.bottom = playerSpace

            }
            // Sinon c'est un autre joueur
            else if (currentPickominoValue != 0) {
                val pileIndex = if (i > actualNumberPlayer - 1) i - 1 else i

                val playerList = playersList as VBox//VBOX
                val player = playersList.children[pileIndex] as VBox // VBox
                val pile = player.children[1] as StackPane // StackPane

                if (pile.children.size == 2) {
                    pile.children.removeIf { it is Pawn }
                }

                val newPawn = Pawn(listePickomino[i])

                // Supprimer les éléments existannts avant l'ajout
                player.children.removeAt(1)
                playerList.children.removeAt(pileIndex)

                // Rajouter les enfants
                pile.children.add(1, newPawn)
                player.children.add(1, pile)
                playerList.children.add(pileIndex, player)

                this.left = playersList

            } else if (currentPickominoValue == 0){
                val pileIndex = if (i > actualNumberPlayer - 1) i - 1 else i

                val playerList = playersList as VBox//VBOX
                val player = playersList.children[pileIndex] as VBox // VBox
                val pile = player.children[1] as StackPane // StackPane

                if (pile.children.size == 2) {
                    pile.children.removeIf { it is Pawn }
                }

                // Supprimer les éléments existannts avant l'ajout
                player.children.removeAt(1)
                playerList.children.removeAt(pileIndex)

                // Rajouter les enfants
                player.children.add(1, pile)
                playerList.children.add(pileIndex, player)

                this.left = playersList

            }
        }
    }

    fun UpDateDiceRoll(listeDice: Array<Int>, controleur : EventHandler<javafx.scene.input.MouseEvent>, dicesKeeped : Array<Int>, canplay : Boolean){
        dicePlayedSection.children.clear()
        dicePlayed.children.clear()

        var cpt = 0
        for (row in 0 until 2) {
            for (col in 0 until 4) {
                // Stack pane (Dotted & dice)
                val stackPaneDice = StackPane()
                val dotted = Dotted(68.0, 68.0)
                stackPaneDice.children.add(dotted)


                if (cpt < listeDice.size){
                    var dice = Dice(listeDice[cpt])
                    if (dice.diceNumber !in dicesKeeped){
                        stackPaneDice.children.add(Iluminate(70.0,70.0))
                        dice.onMousePressed = controleur
                        dice.cursor = javafx.scene.Cursor.HAND
                    }

                    stackPaneDice.children.add(dice)

                }

                cpt++
                dicePlayed.add(stackPaneDice, col, row)
            }
        }

        dicePlayedSection.children.addAll(dicePlayedTitle, dicePlayed)
    }

    fun UpDateDiceKeep(listeDice: Array<Int>){
        diceKeptSection.children.clear()
        diceKept.children.clear()
        var cpt = 0
        for (row in 0 until 4) {
            for (col in 0 until 2) {
                // Stack pane (Dotted & dice)
                val stackPaneDice = StackPane()
                val dotted = Dotted(68.0, 68.0)

                stackPaneDice.children.add(dotted)
                if (cpt < listeDice.size){
                    var dice = Dice(listeDice[cpt])
                    stackPaneDice.children.add(dice)
                }

                diceKept.add(stackPaneDice, col, row)
                cpt++
            }
        }

        diceKeptSection.children.addAll(diceKeptTitle, diceKept)
    }


    fun fixButtonRolls(controleur : EventHandler<ActionEvent>){
        rollDiceBtn.onAction = controleur
        rollDiceBtn.onMousePressed
    }
    fun fixButtonMenu(controleur : EventHandler<ActionEvent>){
        menuButton.onAction = controleur
        rollDiceBtn.onMousePressed
    }

    fun UpDateSelectionPickomino(number:Int,Contoleur : EventHandler<javafx.scene.input.MouseEvent>){

        if (21 <= number) {
            var findit = false
            for (pickomino in pickominoSection.children) {
                var picko = pickomino as Pawn
                if (picko.value == number) {
                    findit = true
                    picko.clickable()
                    picko.onMouseClicked = Contoleur
                } else picko.not_clickable()
            }

            if (findit == false) {
                UpDateSelectionPickomino(number - 1, Contoleur)
            }
        }
    }

}