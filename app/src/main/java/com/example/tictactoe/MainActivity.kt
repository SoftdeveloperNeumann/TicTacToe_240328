package com.example.tictactoe

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var currentPlayer = "X"
    lateinit var allFields: Array<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvStatus.text = getString(R.string.tv_status_start, currentPlayer)
        allFields = with(binding) {
            arrayOf(f0, f1, f2, f3, f4, f5, f6, f7, f8)
        }

    }

    fun onClick(view: View) {
        val textview = view as TextView

        if (textview.text == "") {
            if (currentPlayer == "X") textview.setTextColor(Color.GREEN) else textview.setTextColor(
                Color.RED
            )
            textview.text = currentPlayer

            currentPlayer = if (currentPlayer == "X") "O" else "X"

            if(allFields.all { it.text != "" }){
                resetGame()
            }
        }

    }

    fun resetGame() {
        allFields.forEach { it.text = "" }
    }
}