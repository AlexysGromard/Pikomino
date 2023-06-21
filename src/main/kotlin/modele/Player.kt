package modele

import iut.info1.pickomino.exceptions.IncorrectKeyException

/**
 * Classe représentant un joueur.
 *
 * @param numberPlayer Numéro attribué au joueur.
 * @param localPlayer Indique si le joueur est local (true) ou distant (false).
 *
 * @property numberPlayer Numéro attribué au joueur.
 * @property localPlayer Indique si le joueur est local (true) ou distant (false).
 * @property ListPickomino liste de Pickomino possede par le joueur
 */
class Player(numberPlayer:Int,localPlayer : Boolean) {
    private val numberPlayer : Int = numberPlayer
    val localPlayer : Boolean = localPlayer
    private var ListPickomino :MutableList<Pickomino> = mutableListOf<Pickomino>()

    /**
     * Renvoie le nombre total de vers possédés par le joueur.
     *
     * @return Le nombre total de vers possédés par le joueur.
     */
    fun countWorms():Int{
        var nb_worms = 0

        for (i in 0..ListPickomino.size-1) {

            nb_worms +=ListPickomino[i].getnbWorm()
        }
        return nb_worms
    }

    /**
     * Renvoie le premier élément de la liste des Pickominos du joueur.
     *
     * @return Le premier élément de la liste des Pickominos du joueur.
     *
     * @throws IncorrectKeyException si la liste des Pickominos est vide.
     */
    fun firstElement():Pickomino {
        if (ListPickomino.size == 0) {
            throw IncorrectKeyException()
        }
        return ListPickomino[ListPickomino.size-1]
    }

    /**
     * Ajoute un Pickomino à la liste des Pickominos du joueur.
     *
     * @param Picko Le Pickomino à ajouter.
     */
    fun addElement(Picko: Pickomino) {
        if (ListPickomino.size == 16) {
            return
        }else {
            ListPickomino.add(Picko)
        }
    }

    /**
     * Supprime le dernier Pickomino de la liste des Pickominos du joueur et le renvoie.
     *
     * @return Le dernier Pickomino de la liste des Pickominos du joueur.
     *
     * @throws IncorrectKeyException si la liste des Pickominos est vide.
     */
    public fun removeElement():Pickomino {
        var picko = ListPickomino[ListPickomino.size-1]
        try {
            ListPickomino.removeAt(ListPickomino.size-1)
        }
        catch (e:IndexOutOfBoundsException) {
            throw  IncorrectKeyException()
        }
        return picko
    }


    /**
     * Met à jour le dernier Pickomino de la liste des Pickominos du joueur avec le Pickomino donné.
     * Si le Pickomino donné est déjà présent dans la liste, il devient le dernier élément.
     * Sinon, il est ajouté à la liste en tant que dernier élément.
     *
     * @param picko Le Pickomino à mettre à jour.
     */
    fun topPickominoIs(picko : Pickomino){


        if (picko in ListPickomino){
            if (picko != ListPickomino[ListPickomino.size-1]){
                this.removeElement()
                this.topPickominoIs(picko)
            }
        }
        else{
            this.addElement(picko)
        }

        if (picko.getValue() == 0 && ListPickomino.size != 0) {
            this.removeElement()
        }
    }

    fun allPickomino() : MutableList<Pickomino>{

        return ListPickomino
    }



}