package view.Home

import controleur.ControleurButtonBackHomeView
import controleur.Menu.PlayButton
import controleur.Menu.QuitButton
import controleur.Menu.RulesContoleur
import controleur.Menu.SettingsButton
import controleur.BindingFloor
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.geometry.Rectangle2D
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.shape.Arc
import javafx.scene.shape.ArcType
import javafx.scene.transform.Translate
import javafx.stage.Screen
import javafx.stage.Stage
import view.Home.Center.CreateServer
import view.Home.Center.HomeCenter
import view.Home.Center.Menu
import view.Home.Center.Setting
import view.MainView
import view.View
import view.components.TransparentButton
import java.io.FileInputStream
import javax.script.Bindings

class HomeView(stage: Stage,mainview: MainView): View() {
    var backButton : TransparentButton = TransparentButton("back")

    var mainview = mainview


    var homcenter : HomeCenter
    var historyofHomeCenter = mutableListOf<HomeCenter>()
    var stage = stage
    private var vbox : VBox

    init  {
        this.vbox = VBox()
        vbox.alignment = Pos.CENTER
        vbox.padding = Insets(10.0,10.0,10.0,10.0)

        backButton.onAction = ControleurButtonBackHomeView(this)

        vbox.children.add(backButton)

        homcenter = Menu()


        var playbut = PlayButton(mainview,this)
        var rulesbutt = RulesContoleur(this)
        var settingbutt = SettingsButton(this, stage)
        var Quitbutt = QuitButton(this,stage)
        this.homcenter.fixeButton(arrayOf(playbut,rulesbutt,settingbutt,Quitbutt))

        this.center = homcenter

        val vbox2 = VBox()
        vbox2.maxHeight = 80.0
        // Floor
        val screenBounds: Rectangle2D = Screen.getPrimary().bounds
        val screenWidth = screenBounds.width

        val radius = screenWidth / 2
        val arc = Arc(radius, 0.0, radius, 100.0, 0.0, 180.0)
        arc.type = ArcType.ROUND
        arc.fill = Color.web("#4DA35C")

        val imagesPane = Pane()
        imagesPane.layoutX = radius
        imagesPane.layoutY = radius

        val negativeTranslate = Translate()
        negativeTranslate.x = 0.0 // Valeur de translation horizontale
        negativeTranslate.y = 90.0  // Valeur de translation verticale

        imagesPane.transforms.add(negativeTranslate)

        // Images bottom
        val imagePaths = listOf(
            "file:src/main/kotlin/view/assets/chicken 1.png",
            "file:src/main/kotlin/view/assets/worm 1.png",
            "file:src/main/kotlin/view/assets/dice 1.png"
        )

        for (i in 0 until imagePaths.size) {
            val imagePath = imagePaths[i]
            val image = Image(imagePath)
            val imageView = ImageView(image)

            imageView.fitWidth = image.width * 0.7 // Réduire la largeur de l'image à 80% de sa taille d'origine
            imageView.fitHeight = image.height * 0.7 // Réduire la hauteur de l'image à 80% de sa taille d'origine

            imageView.layoutXProperty().bind((arc.radiusXProperty().multiply(2).divide(2.5).multiply(i).add(30.0)))

            imageView.layoutY = imagesPane.height - imageView.fitHeight

            imagesPane.children.add(imageView)
        }

        val floor = StackPane()
        floor.alignment = Pos.BOTTOM_CENTER
        floor.children.addAll(arc, imagesPane)

        vbox2.alignment = Pos.BOTTOM_LEFT
        vbox2.children.addAll(floor)
        this.bottom = vbox2


        // Bind for the floor
        val bindingFloor = BindingFloor(arc)
        bindingFloor.bindArcWidthToStageWidth(stage.widthProperty())

    }


    fun update(newhomecenter : HomeCenter){
        historyofHomeCenter.add(this.homcenter)
        this.homcenter = newhomecenter

        this.center = homcenter

        if (newhomecenter.isMenu){
            this.top = VBox()
        }
        else{
            this.top = vbox
        }
    }

    fun Goback(){
        if (historyofHomeCenter.size >= 1){
            update(historyofHomeCenter[historyofHomeCenter.size-1])
            historyofHomeCenter.removeAt(historyofHomeCenter.size-1)
            historyofHomeCenter.removeAt(historyofHomeCenter.size-1)
        }
    }
}