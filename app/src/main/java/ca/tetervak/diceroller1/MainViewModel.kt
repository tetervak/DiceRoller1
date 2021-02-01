package ca.tetervak.diceroller1

import androidx.lifecycle.ViewModel
import ca.tetervak.diceroller1.domain.Die

class MainViewModel: ViewModel() {

    private val die = Die()

    fun dieValue() = die.value?.toString() ?: " "

    fun rollDie(){
        die.roll()
    }

    fun resetDie(){
        die.reset()
    }

}