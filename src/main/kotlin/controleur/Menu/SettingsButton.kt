package controleur.Menu

import controleur.ControleurScreen
import javafx.beans.property.SimpleIntegerProperty
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.Stage
import javafx.util.converter.NumberStringConverter
import view.Home.Center.CreateOrJoin
import view.Home.Center.Setting
import view.Home.HomeView

class SettingsButton(vue: HomeView, stage : Stage): EventHandler<ActionEvent> {
    var vue = vue
    val i = SimpleIntegerProperty()
    var stage = stage
    override fun handle(event: ActionEvent?) {
        i.set(99)

        val newPage = Setting()
        newPage.nombre.textProperty().bindBidirectional(i, NumberStringConverter())
        newPage.slide.valueProperty().bindBidirectional(i)
        var controleurScreen = ControleurScreen(vue,stage)
        newPage.listener.onAction = controleurScreen
        vue.update(newPage)
    }

}