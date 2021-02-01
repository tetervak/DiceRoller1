package ca.tetervak.diceroller1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.tetervak.diceroller1.domain.Die

class MainViewModel: ViewModel() {

    private val die = Die()

    private val _dieValue = MutableLiveData<String>(" ")
    val dieValue: LiveData<String> = _dieValue

    fun rollDie(){
        die.roll()
        _dieValue.value = die.value!!.toString()
    }

    fun resetDie(){
        die.reset()
        _dieValue.value = " "
    }

}