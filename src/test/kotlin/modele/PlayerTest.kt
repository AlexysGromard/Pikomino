package modele

import iut.info1.pickomino.exceptions.IncorrectKeyException
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class PlayerTest {

    @Test
    fun countWorms() {
        var player = Player(2,true)
        assertEquals(0,player.countWorms())
    }
    @Test
    fun countWorms2() {
        var player = Player(2,true)
        player.addElement(Pickomino(21))
        player.addElement(Pickomino(35))
        assertEquals(5,player.countWorms())
    }

    @Test
    fun firstElement() {
        var player = Player(2,true)
        var picko = Pickomino(21)
        player.addElement(picko)
        assertEquals(picko,player.firstElement())
    }
    @Test
    fun firstElementEmpty() {
        var player = Player(2,true)
        assertThrows(IncorrectKeyException::class.java){player.firstElement()}
    }



    @Test
    fun removeElement() {
        var player = Player(2,true)
        var picko = Pickomino(21)
        var picko2 = Pickomino(22)
        player.addElement(picko)
        player.addElement(picko2)
        assertEquals(picko2,player.removeElement())
    }
    @Test
    fun removeElementEmpty() {
        var player = Player(2,true)
        assertThrows(IndexOutOfBoundsException::class.java){player.removeElement()}
    }
}