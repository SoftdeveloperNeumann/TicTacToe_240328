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
    var isPlaying = true

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

        if (!isPlaying) {
            resetGame()
            return
        }

        if (textview.text == "") {
            if (currentPlayer == "X") textview.setTextColor(Color.GREEN) else textview.setTextColor(
                Color.RED
            )
            textview.text = currentPlayer

            if (checkWin()) {
                binding.tvStatus.text = getString(R.string.tv_status_win, currentPlayer)
                isPlaying = false
            } else if (allFields.all { it.text != "" }) {
                binding.tvStatus.text = getString(R.string.tv_status_draw)
                isPlaying = false

            } else {
                currentPlayer = if (currentPlayer == "X") "O" else "X"
                binding.tvStatus.text = getString(R.string.tv_status_start, currentPlayer)
            }

        }

    }

    private fun checkWin(): Boolean {
        with(binding) {
            val horizontal =
                (f0.text == f1.text && f1.text == f2.text && f0.text != "") ||
                        (f3.text == f4.text && f4.text == f5.text && f3.text != "") ||
                        (f6.text == f7.text && f7.text == f8.text && f6.text != "")

            val vertical =
                (f0.text == f3.text && f3.text == f6.text && f0.text != "") ||
                        (f1.text == f4.text && f4.text == f7.text && f1.text != "") ||
                        (f2.text == f5.text && f5.text == f8.text && f2.text != "")

            val diagonal =
                (f0.text == f4.text && f4.text == f8.text && f0.text != "") ||
                        (f2.text == f4.text && f4.text == f6.text && f2.text != "")

            return horizontal || vertical || diagonal
        }

    }

    fun resetGame() {
        currentPlayer = "X"
        binding.tvStatus.text = getString(R.string.tv_status_start, currentPlayer)
        allFields.forEach { it.text = "" }
        isPlaying = true

    }


}