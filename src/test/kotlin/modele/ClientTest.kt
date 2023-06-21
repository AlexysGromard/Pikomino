package modele

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ClientTest {
    val game = Game(1)
    val client = Client(game)
    @Test
    fun getId() {
    }

    @Test
    fun setId() {
    }

    @Test
    fun getKey() {
    }

    @Test
    fun setKey() {
    }

    @Test
    fun getConnected() {
    }

    @Test
    fun setConnected() {
    }

    @Test
    fun getConnect() {
    }

    @Test
    fun setConnect() {
    }

    @Test
    fun getGame() {
    }

    @Test
    fun setGame() {
    }

    @Test
    fun getNbJoueur() {
    }

    @Test
    fun setNbJoueur() {
    }

    @Test
    fun getCanRoll() {
    }

    @Test
    fun setCanRoll() {
    }

    @Test
    fun getCankeepDice() {
    }

    @Test
    fun setCankeepDice() {
    }

    @Test
    fun getCankeepPICKO() {
    }

    @Test
    fun setCankeepPICKO() {
    }

    @Test
    fun createGame() {
    }

    @Test
    fun test_joinGame1() {
        client.JoinGame(1,1,1)
        assertEquals(1,client.id,"l'id est incorrecte")
        assertEquals(1,client.key,"l'id est incorrecte")
        assertEquals(1,client.nbJoueur,"l'id est incorrecte")
    }

    @Test
    fun test_joinGame2() {
    }

    @Test
    fun test_joinGame3() {
    }

    @Test
    fun test_joinGame4() {

    }

    @Test
    fun test_joinGame5() {
    }

    @Test
    fun update() {
    }

    @Test
    fun keepDice() {
    }

    @Test
    fun keepPickomino() {
    }
}