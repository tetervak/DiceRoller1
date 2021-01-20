package ca.tetervak.diceroller1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import ca.tetervak.diceroller1.databinding.ActivityMainBinding
import ca.tetervak.diceroller1.domain.Die

class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG = "DieRoller"
        const val CURRENT_DIE_VALUE = "current_die_value"
    }

    private lateinit var  binding: ActivityMainBinding
    private val die = Die()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if(savedInstanceState is Bundle){
            val savedValue: Int = savedInstanceState.getInt(CURRENT_DIE_VALUE)
            if(savedValue > 0){
                Log.d(TAG, "onCreate: recovering the saved instance.")
                die.value = savedValue
                displayDieValue()
            }
        }

        binding.rollButton.setOnClickListener { onRoll() }
        binding.resetButton.setOnClickListener { onReset() }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // store zero if not rolled
        outState.putInt(CURRENT_DIE_VALUE, die.value ?: 0)
    }

    private fun onRoll() {
        Log.d(TAG, "onRoll() called")
        die.roll()
        displayDieValue()
        Toast.makeText(this, getString(R.string.die_rolled), Toast.LENGTH_SHORT).show()
    }

    private fun onReset() {
        Log.d(TAG, "onReset() called ")
        die.reset()
        displayDieValue()
        Toast.makeText(this, getString(R.string.app_reset), Toast.LENGTH_SHORT).show()
    }

    private fun displayDieValue() {
        Log.d(TAG, "displayDieValue() called ")
        binding.dieValue.text = die.value?.toString() ?: " "
    }

}