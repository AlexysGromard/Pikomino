package modele

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

class PickominoTest {


    @Test
    fun test1_getValue() {
        var pick =Pickomino(21)
        assertEquals(21,pick.getValue(),"Il n'a pas renvoyer la bonne valeur")
    }
    @Test
    fun test2_getValue() {
        var pick =Pickomino(36)
        assertEquals(36,pick.getValue(),"Il n'a pas renvoyer la bonne valeur")
    }
    @Test
    fun test3_InitErrorValue() {
        assertThrows<Exception>{Pickomino(16)}
    }

    @Test
    fun test4_InitErrorValue() {
        assertThrows<Exception>{Pickomino(54)}
    }



    @Test
    fun test1_getnbWorms() {
        var pick =Pickomino(21)
        assertEquals(1,pick.getnbWorm(),"Il n'a pas renvoyer la bonne valeur")
    }
    @Test
    fun test2_getnbWorms() {
        var pick =Pickomino(25)
        assertEquals(2,pick.getnbWorm(),"Il n'a pas renvoyer la bonne valeur")
    }

    @Test
    fun test3_getnbWorms() {
        var pick =Pickomino(29)
        assertEquals(3,pick.getnbWorm(),"Il n'a pas renvoyer la bonne valeur")
    }


    @Test
    fun test4_getnbWorms() {
        var pick =Pickomino(35)
        assertEquals(4,pick.getnbWorm(),"Il n'a pas renvoyer la bonne valeur")
    }
    @Test
    fun test3_InitErrorWorms() {
        assertThrows<Exception>{Pickomino(10)}
    }

    @Test
    fun test4_InitErrorWorms() {
        assertThrows<Exception>{Pickomino(46)}
    }


}