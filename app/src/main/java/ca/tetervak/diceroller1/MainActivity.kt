package ca.tetervak.diceroller1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import ca.tetervak.diceroller1.domain.Die

class MainActivity : AppCompatActivity() {

    companion object{
        const val CURRENT_DIE_VALUE = "current_die_value"
    }

    private val die = Die()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState is Bundle){
            val savedValue: Int = savedInstanceState.getInt(CURRENT_DIE_VALUE)
            if(savedValue > 0){
                die.value = savedValue
                displayDieValue()
            }
        }

        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener { onRoll() }

        val resetButton: Button = findViewById(R.id.reset_button)
        resetButton.setOnClickListener { onReset() }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // store zero if not rolled
        outState.putInt(CURRENT_DIE_VALUE, die.value ?: 0)
    }

    private fun onRoll() {
        die.roll()
        displayDieValue()
        Toast.makeText(this, getString(R.string.die_rolled), Toast.LENGTH_SHORT).show()
    }

    private fun onReset() {
        die.reset()
        displayDieValue()
        Toast.makeText(this, getString(R.string.app_reset), Toast.LENGTH_SHORT).show()
    }

    private fun displayDieValue() {
        val resultText: TextView = findViewById(R.id.die_value)
        resultText.text = die.value?.toString() ?: " "
    }

}