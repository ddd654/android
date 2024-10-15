package com.example.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activity.databinding.ActivityCocoIntentBinding

class cocoIntent : AppCompatActivity() {

    private lateinit var binding : ActivityCocoIntentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_my_intent)

        binding = ActivityCocoIntentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //extra 데이터 받기
//        var data1 = intent.getStringExtra("data1")
//        var data2 = intent.getIntExtra("data2", 23)
//
//        Toast.makeText(this, "넘어온 데이터 $data1, $data2", Toast.LENGTH_SHORT).show()

        //액티비티 콜백
        binding.returnBtn.setOnClickListener {

            intent.putExtra("callback", "return할 데이터")
            setResult(RESULT_OK, intent)


            finish() //현재 액티비티 종료

        }



    }
}