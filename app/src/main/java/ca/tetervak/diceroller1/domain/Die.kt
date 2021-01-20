package ca.tetervak.diceroller1.domain

import android.util.Log
import java.lang.IllegalArgumentException

class Die() {

    // null means not rolled yet
    var value: Int? = null
        set(n) {
            if (n in 1..6 || n == null) {
                field = n
            } else {
                Log.e("Die", "Illegal die value $n")
                //throw IllegalArgumentException("Illegal die value $n")
            }
        }

    constructor(n: Int) : this() {
        value = n
    }

    fun roll() {
        value = (1..6).random()
    }

    fun reset() {
        value = null
    }
}