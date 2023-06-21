package modele

/**
 * Classe représentant un Pickomino.
 *
 * @param value La valeur attribuée au Pickomino.
 *
 * @property value Valeur attribuée au Pickomino.
 * @property nbWorms Nombre de vers présents sur le Pickomino.
 *
 * @throws Exception si la valeur du Pickomino est en dehors de la plage valide.
 */
class Pickomino(value :Int) {
    private val value :Int      //Valeur du Pickomino
    private val nbWorms :Int    //Nombre de verre au Pickomino

    init {
        if (value == 0) {
            this.value = 0
            nbWorms = 0
        }
        else{
            if (value >=21 &&  value <=36) this.value = value
            else throw Exception()

        //test du when
        nbWorms = when (value) {
            in 21..24 -> 1
            in 25..28 -> 2
            in 29..32 -> 3
            in 33..36 -> 4
            else -> throw Exception("La valeur du Pickomino est en dehors de la plage valide.")
        }
    }

    /**
     * Renvoie la valeur du Pickomino.
     *
     * @return La valeur du Pickomino.
     */
    fun getValue(): Int {
        return this.value
    }

    /**
     * Renvoie le nombre de vers présents sur le Pickomino.
     *
     * @return Le nombre de vers présents sur le Pickomino.
     */
    fun getnbWorm(): Int {
        return this.nbWorms
    }

    /**
     * Renvoie une représentation sous forme de chaîne de caractères du Pickomino.
     *
     * @return La représentation du Pickomino en tant que chaîne de caractères.
     */
    override fun toString(): String {
        return "$value"
    }

    /**
     * Vérifie si le Pickomino est égal à un autre objet donné.
     *
     * @param other L'objet avec lequel le Pickomino est comparé.
     *
     * @return true si les deux objets sont égaux, false sinon.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pickomino

        if (value != other.value) return false

        return true
    }



}