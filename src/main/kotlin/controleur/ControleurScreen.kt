package controleur

import javafx.beans.property.SimpleIntegerProperty
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.Stage
import javafx.util.converter.NumberStringConverter
import view.Home.Center.Setting
import view.Home.HomeView
import view.MainView

class ControleurScreen(vue: HomeView, stage : Stage): EventHandler<ActionEvent> {
    var vue = vue
    var stage = stage
    override fun handle(event: ActionEvent?) {

        var actual = vue.center as Setting
        var actualChoice = actual.listener.value


        if (actualChoice == "Plein écran") {
            stage.setFullScreen(true);

        }else if (actualChoice == "Fenetré sans bordure") {
            stage.setFullScreen(false);

        }else {
            stage.setFullScreen(false);
            println(actualChoice)
        }


    }

}