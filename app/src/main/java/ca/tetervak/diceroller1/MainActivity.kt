package ca.tetervak.diceroller1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import ca.tetervak.diceroller1.databinding.ActivityMainBinding
import ca.tetervak.diceroller1.domain.Die

class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG = "DieRoller"
        const val CURRENT_DIE_VALUE = "current_die_value"
    }

    private lateinit var  binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        displayDieValue()
        binding.rollButton.setOnClickListener { onRoll() }
        binding.resetButton.setOnClickListener { onReset() }

    }

    private fun onRoll() {
        Log.d(TAG, "onRoll() called")
        viewModel.die.roll()
        displayDieValue()
        Toast.makeText(this, getString(R.string.die_rolled), Toast.LENGTH_SHORT).show()
    }

    private fun onReset() {
        Log.d(TAG, "onReset() called ")
        viewModel.die.reset()
        displayDieValue()
        Toast.makeText(this, getString(R.string.app_reset), Toast.LENGTH_SHORT).show()
    }

    private fun displayDieValue() {
        Log.d(TAG, "displayDieValue() called ")
        binding.dieValue.text = viewModel.die.value?.toString() ?: " "
    }

}