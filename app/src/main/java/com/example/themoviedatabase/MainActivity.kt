package com.example.themoviedatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.themoviedatabase.databinding.MainLayoutBinding
import com.example.themoviedatabase.ui.theme.TheMovieDatabaseTheme

class MainActivity : ComponentActivity() {

    private lateinit var binding: MainLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var number_of_taps: Int = 0;


        binding = MainLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        binding.buttonTap.setOnClickListener {
            number_of_taps++
            binding.textCounterTap.text = "You've tapped the button $number_of_taps times"
        }

        setContentView(view)

//        setContentView(R.layout.main_layout)


    }
}