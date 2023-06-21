package view.components

import javafx.geometry.Insets
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.shape.StrokeType

class Dotted(w :Double, h : Double) : StackPane()  {
    init {
        val margin = 2.0 // Définir la taille de la marge souhaitée

        val rectangle = Rectangle(w, h)
        rectangle.arcHeight = 18.0
        rectangle.arcWidth = 18.0
        rectangle.fill = Color.TRANSPARENT
        rectangle.stroke = Color.web("#0F1820")  // Couleur de la bordure
        rectangle.strokeWidth = 2.0  // Largeur de la bordure
        rectangle.strokeType = StrokeType.CENTERED  // Type de trait (pointillés)
        rectangle.strokeDashArray.addAll(5.0, 5.0)  // Motif de pointillés (5 pixels pleins, 5 pixels vides)

        val container = StackPane(rectangle)
        container.padding = Insets(margin)

        this.children.add(container)
    }
}