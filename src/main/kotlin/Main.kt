import iut.info1.pickomino.data.Game
import controleur.Menu.PlayButton
import controleur.Menu.QuitButton
import controleur.Menu.RulesContoleur
import controleur.Menu.SettingsButton
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage
import view.Home.Center.*
import view.Home.HomeView
import view.MainView
import view.game.GameView
import java.util.prefs.Preferences
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import javax.sound.sampled.DataLine

class Main : Application() {
    private val preferences: Preferences = Preferences.userRoot().node(this.javaClass.name)
    private var clip : Clip? = null

    override fun start(primaryStage: Stage) {



        val vue = MainView(primaryStage)//HomeView(primaryStage)
        //vue.update(Menu())

        // Initialisation de l'interface utilisateur et des composants JavaFX
        val scene = Scene(vue, 600.0, 400.0)
        scene.getStylesheets().add("file:src/main/kotlin/view/Home/Center/style.css")
        // scene.getStylesheets().add(getClass().getRessource("file:src/main/kotlin/view/Home/Center/style.css").toExternalForm())


        primaryStage.minWidth = 1100.0 // Définir une largeur minimale de 600 pixels
        primaryStage.minHeight = 700.0 // Définir une hauteur minimale de 400 pixels

        // LoadScreenConfig
        loadScreenConfig(primaryStage)

        //ajout de l'icone de page
        val iconPath = "file:src/main/kotlin/view/assets/Logo.png" // Spécifiez le chemin vers votre icône
        val icon = Image(iconPath)

        primaryStage.icons.add(icon)

        primaryStage.title = "Pikomino"
        primaryStage.scene = scene

        music()

        // Affichez la scène sur la fenêtre principale
        primaryStage.show()
    }

    fun loadScreenConfig(primaryStage : Stage){
        // Récupérer les préférences de taille de la fenêtre
        val storedWidth = preferences.getDouble("windowWidth", 800.0)
        val storedHeight = preferences.getDouble("windowHeight", 600.0)
        val storedIsFullscreen = preferences.getBoolean("isFullscreen", false)

        // Si la dernière fois la fenêtre était en plein écran, restaurer cet état
        if (storedIsFullscreen) {
            primaryStage.isFullScreen = true
        } else {
            primaryStage.width = storedWidth
            primaryStage.height = storedHeight
        }

        // Gérer le changement de taille de la fenêtre
        primaryStage.widthProperty().addListener { _, _, newWidth ->
            preferences.putDouble("windowWidth", newWidth.toDouble())
        }
        primaryStage.heightProperty().addListener { _, _, newHeight ->
            preferences.putDouble("windowHeight", newHeight.toDouble())
        }
        primaryStage.fullScreenProperty().addListener { _, _, isFullscreen ->
            preferences.putBoolean("isFullscreen", isFullscreen)
        }
    }

    fun music(){
        try {
            val musicPath = "src/main/kotlin/music/background_music.wav" // Replace with the actual path to your music file
            val musicFile = AudioSystem.getAudioInputStream(java.io.File(musicPath))

            val clipInfo = DataLine.Info(Clip::class.java, musicFile.format)
            clip = AudioSystem.getLine(clipInfo) as Clip
            clip!!.open(musicFile)

            clip!!.start()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    private fun stopMusic() {
        clip?.stop()
    }
}

fun main() {


    // Lancement de la vue
    Application.launch(Main::class.java)

}


