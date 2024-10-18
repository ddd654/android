package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nameBtn.setOnClickListener {

            if (binding.nameText.text.toString().isEmpty()) {
                Toast.makeText(this, "이름을 입력하세여", Toast.LENGTH_SHORT).show()
            } else {
                var intent = Intent(this, QuizActivity::class.java)
                startActivity(intent)
            }

        }
    }
}