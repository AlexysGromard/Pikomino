package modele

import iut.info1.pickomino.data.DICE
import iut.info1.pickomino.data.STATUS
import iut.info1.pickomino.exceptions.IncorrectKeyException

class Game(numérojoueur : Int) {
    //private val id = id
    private var listPickominosEnJeu :MutableList<Pickomino> = mutableListOf<Pickomino>()
    private var listPlayer :MutableList<Player> = mutableListOf<Player>()
    var numérojoueur :Int = numérojoueur


    var diceKept = listOf<DICE>() //Dice
    var diceChosen = listOf<DICE>()





    public fun preremplir() {
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

    public fun isEmpty():Boolean {
        return listPickominosEnJeu.size ==0
    }

    public fun addPlayer( player : Player) : Boolean{
        if (player !in this.listPlayer){
            this.listPlayer.add(player)
            return true
        }
        return false
    }

    public fun removeElement(Picko:Pickomino) {

        try {
            listPickominosEnJeu.remove(Picko)
        }
        catch (e:IndexOutOfBoundsException) {
            throw  IncorrectKeyException()
        }

    }
    fun playerList():MutableList<Player> {
        return listPlayer
    }

    public fun getPickos(): MutableCollection<Pickomino> {
        if (listPickominosEnJeu.size == 0) {
            throw IncorrectKeyException()
        }
        return listPickominosEnJeu
    }


    fun setDice(diceskept : List<DICE>, diceschosen : List<DICE>){
        diceKept = diceskept
        diceChosen = diceschosen
    }

    fun setPickomino(Pickominos : List<Int>){
        var newlistPickominosEnJeu = mutableListOf<Pickomino>()
        for ( Picko in Pickominos){
            newlistPickominosEnJeu.add(Pickomino(Picko))
        }

        this.listPickominosEnJeu = newlistPickominosEnJeu
    }

    fun convertIntintoDice(valeur: Int) : DICE{
        when (valeur){
            1 -> return DICE.d1
            2 -> return DICE.d2
            3 -> return DICE.d3
            4 -> return DICE.d4
            5 -> return DICE.d5
            else -> return DICE.worm
        }
    }

    fun convertArrayDiceIntoInt(array : Array<DICE>) : Array<Int>{
        val listeInt = mutableListOf<Int>()

        for (Dice in array){
            when (Dice){
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

    fun AllDiceNumber() : Int{
        var sum = 0
        for (dice in convertArrayDiceIntoInt(this.diceChosen.toTypedArray())){
            if (dice !in arrayOf(1,2,3,4,5)) {
                sum += 5
            }
            else{
                sum += dice
            }

        }
        return sum
    }

    fun setpickominoPlayer(pickominos : List<Pickomino>){
        for ( i in 0..listPlayer.size-1){
            listPlayer[i].topPickominoIs(pickominos[i])
        }
    }

    fun pickominoPlayer() : MutableList<Pickomino>{
        val list = mutableListOf<Pickomino>()
        for (player in  listPlayer){
            try {
                list.add(player.firstElement())
            }
            catch (e : IncorrectKeyException){

            }
            //list.add(player.firstElement())
        }
        return list
    }

}