package modele

import iut.info1.pickomino.data.DICE
import iut.info1.pickomino.data.STATUS
import iut.info1.pickomino.exceptions.IncorrectKeyException
/**
 *Classe représentant un jeu.
 *
 *@param numérojoueur le numero local du joueur
 *
 *@property numérojoueur Le numéro du joueur actuel.
 *@property listPickominosEnJeu La liste des Pickominos présents dans le jeu.
 *@property listPlayer La liste des joueurs dans le jeu.
 *@property diceKept Les dés gardés par le joueur.
 *@property diceChosen Les dés choisis par le joueur.
*/
class Game(numérojoueur : Int) {

    private var listPickominosEnJeu: MutableList<Pickomino> = mutableListOf<Pickomino>() //liste de Pickominos en jeu
    private var listPlayer: MutableList<Player> = mutableListOf<Player>()  //liste des joueur dans la partie
    var numérojoueur: Int = numérojoueur    //numero du joueur local


    var diceKept = listOf<DICE>() // dé conserver
    var diceChosen = listOf<DICE>() //dé qui peut etre choisie


    /**
     * Initialise tout les pickomino present dans le jeu en l'ajoutant dans la listPickominosEnJeu
     */
    fun preremplir() {
        // Ajoute les Pickominos avec des valeurs prédéfinies à la liste listPickominosEnJeu
        listPickominosEnJeu.add(Pickomino(21))
        listPickominosEnJeu.add(Pickomino(22))
        listPickominosEnJeu.add(Pickomino(23))
        listPickominosEnJeu.add(Pickomino(24))
        listPickominosEnJeu.add(Pickomino(25))
        listPickominosEnJeu.add(Pickomino(26))
        listPickominosEnJeu.add(Pickomino(27))
        listPickominosEnJeu.add(Pickomino(28))
        listPickominosEnJeu.add(Pickomino(29))
        listPickominosEnJeu.add(Pickomino(30))
        listPickominosEnJeu.add(Pickomino(31))
        listPickominosEnJeu.add(Pickomino(32))
        listPickominosEnJeu.add(Pickomino(33))
        listPickominosEnJeu.add(Pickomino(34))
        listPickominosEnJeu.add(Pickomino(35))
        listPickominosEnJeu.add(Pickomino(36))
    }

    /**
     * verifie si la listPickominosEnJeu est equivaut a 0
     *
     * @return true si la liste est vide,sinon false .
     */
    fun isEmpty(): Boolean {
        return listPickominosEnJeu.size == 0
    }

    /**
     * rajoute un player dans listPlayer et renvoie vrai
     *
     * @return true si il n'est pas present dans la liste, sinon false.
     */
    fun addPlayer(player: Player): Boolean {
        // Vérifie si le joueur n'est pas déjà présent dans la liste listPlayer
        if (player !in this.listPlayer) {
            // Ajoute le joueur à la liste
            this.listPlayer.add(player)
            return true
        }
        return false
    }


    /**
     * Supprime l'element en parametre de la liste listPickominosEnJeu
     *
     * @param Picko represente la pickomino qui est enlever de la liste
     * @throws IncorrectKeyException si  listPickominosEnJeu.remove ne fonctionne pas est lance IndexOutOfBoundsException
     */
    fun removeElement(Picko: Pickomino) {
        //teste si on peut suprimer l'element
        try {
            //supprime l'element de la liste
            listPickominosEnJeu.remove(Picko)
        } catch (e: IndexOutOfBoundsException) {
            throw IncorrectKeyException()
        }
    }


    /**
     * Renvoie la liste des joueurs
     *
     * @return listPlayer
     */
    fun playerList(): MutableList<Player> {
        return listPlayer
    }

    /**
     * Renvoie la liste des Pickominos
     *
     * @return listPickominosEnJeu
     *
     * @throws IncorrectKeyException si la listPickominosEnJeu.size == 0
     */
    fun getPickos(): MutableCollection<Pickomino> {
        // verifie que la listPickominosEnJeu.size == 0 si true return une exception ou bien il return la liste
        if (listPickominosEnJeu.size == 0) {
            throw IncorrectKeyException()
        }
        return listPickominosEnJeu
    }


    /**
     * ??? ok pas compris
     *
     * @param diceskept liste des dé selectionner
     * @param diceschosen lsite des dé non selectionner
     */
    fun setDice(diceskept: List<DICE>, diceschosen: List<DICE>) {
        this.diceKept = diceskept
        this.diceChosen = diceschosen
    }

    /**
     * Update la liste des Pickominos en jeu par rapport a la liste de valeur
     *
     * @param Pickominos liste de valeur de pickomino
     */
    fun setPickomino(Pickominos: List<Int>) {
        // creation de la nouvelle liste de Pickomino
        var newlistPickominosEnJeu = mutableListOf<Pickomino>()

        // Chaque valeur present dans la liste vont etre ajouter dans newlistPickominosEnJeu
        for (Picko in Pickominos) {
            newlistPickominosEnJeu.add(Pickomino(Picko))
        }

        // update de l'instance listPickominosEnJeu
        this.listPickominosEnJeu = newlistPickominosEnJeu
    }

    /**
     * Convertie les int en objet de DICE
     *
     * @param valeur represente la valeur du dé
     *
     * @return DICE c'est l'objet de DICE
     */
    fun convertIntintoDice(valeur: Int): DICE {
        // renvoie l'objet dé par rapport au dé
        when (valeur) {
            1 -> return DICE.d1
            2 -> return DICE.d2
            3 -> return DICE.d3
            4 -> return DICE.d4
            5 -> return DICE.d5
            else -> return DICE.worm
        }
    }

    /**
     * Convertie de la liste de DICE en valeur INT
     *
     * @param array array des DICE (dé)
     *
     * @return listeInt
     */
    fun convertArrayDiceIntoInt(array: Array<DICE>): Array<Int> {
        //creation de la liste des int dans les dé
        val listeInt = mutableListOf<Int>()

        // pour chaque dé dans la liste returné dans la lsite en int
        for (Dice in array) {
            when (Dice) {
                DICE.d1 -> listeInt.add(1)
                DICE.d2 -> listeInt.add(2)
                DICE.d3 -> listeInt.add(3)
                DICE.d4 -> listeInt.add(4)
                DICE.d5 -> listeInt.add(5)
                DICE.worm -> listeInt.add(6)
            }
        }

        return listeInt.toTypedArray()
    }

    /**
     *
     *
     * @return sum represente la somme des valeur de chaque dé
     */
    fun AllDiceNumber(): Int {
        // creation de la variable qui stocke la somme des valeur des dé
        var sum = 0

        //
        for (dice in convertArrayDiceIntoInt(this.diceChosen.toTypedArray())) {
            if (dice !in arrayOf(1, 2, 3, 4, 5)) {
                sum += 5// Ajoute 5 à la somme si la valeur du dé est un worm
            } else {
                sum += dice
            }

        }
        return sum
    }

    /**
     * Affecte les Pickominos aux joueurs.
     *
     * @param pickominos La liste des Pickominos à affecter aux joueurs.
     */
    fun setpickominoPlayer(pickominos: List<Pickomino>) {
        for (i in 0..listPlayer.size - 1) {
            listPlayer[i].topPickominoIs(pickominos[i])
        }
    }


    /**
     * Récupère les Pickominos des joueurs.
     *
     * @return Une liste mutable contenant les Pickominos des joueurs.
     */
    fun pickominoPlayer(): MutableList<Pickomino> {
        val list = mutableListOf<Pickomino>()

        // Parcourt chaque joueur pour récupérer son premier Pickomino
        for (player in listPlayer) {
            try {
                list.add(player.firstElement())// Ajoute le premier Pickomino du joueur à la liste
            } catch (e: IncorrectKeyException) {// Gère l'exception IncorrectKeyException si le joueur n'a pas de Pickomino
                // Aucun Pickomino n'est ajouté à la liste dans ce cas
                continue
            }
        }
        return list
    }
}
