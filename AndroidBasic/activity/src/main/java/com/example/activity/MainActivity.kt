package com.example.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activity.databinding.ActivityMainBinding

const val TAG = "Log11"

class MainActivity : AppCompatActivity() {

    //뷰바인딩 2가지 방법
    //private var binding : ActivityMainBinding? =null
    private lateinit var binding: ActivityMainBinding

    var number: Int = 1 // 화면에 보일 변수

    //전역으로 사용할 변수
    private var cnt = 1

    //콜백용 멤버변수
    var activityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            Log.d(TAG, "콜백 실행123 $it")

            if (it.resultCode == Activity.RESULT_OK) {
                //성공 시 실행할 코드를 동작 > data를 받음
//            var callback = it.data?.getStringExtra("callback")
//            Toast.makeText(this, "콜백데이터 : $callback", Toast.LENGTH_SHORT).show()

                //연락처애 접근하기
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //뷰의 연결방식을 이렇게 바꿔준다

        //setContentView(R.layout.activity_main)

        //기존 방식
        //var View12 : TextView = findViewById(R.id.ex_text)
        //View12.setOnClickListener{}

        //뷰바인딩 사용하기 = 좀 더 간단해진다
        binding.exText.setOnClickListener {
            Toast.makeText(this, "click!", Toast.LENGTH_SHORT).show()
        }

        //Q. 숫자 더하기
        // 위에 변수 만들어주고
        binding.plusBtn.setOnClickListener { //클릭하면
//            number++ //숫자 더하고
//            binding.exText.text= number.toString() // 더한 숫자를 넣는다

            //이렇게도 사용가능하다
            var num = binding.exText.text.toString().toInt().plus(1)
            binding.exText.text = num.toString()

        }

        //인텐트 전환하기
        binding.changeBtn.setOnClickListener {//누르면
            //전환한다

//            val intent = Intent(this, cocoIntent::class.java)
            // Intent객체, 매개변수에는 Context와 실행시키고픈 클래스
//
//            //그 사이 코드
//            intent.putExtra("data1", "사과")
//            intent.putExtra("data2", "바나")
//
//            startActivity(intent) // 만든 intent를 여기에 넣는다

            // activity 콜백 런처
            // 위에 멤버변수 만들기
            val intent = Intent(this, cocoIntent::class.java)
            activityLauncher.launch(intent)


            //finish() // 지금 액티비티 종료~~

        }


        //암시적 인텐트
        binding.changeBtn2.setOnClickListener {

            //한 앱에서 다른 앱화면 띄우기
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"))
            startActivity(intent)

            //구글의 경우
            val intent2 = Intent(Intent.ACTION_VIEW, Uri.parse("geo: 123.00, -321.00"))
            startActivity(intent2)
        }



        Log.d(TAG, "onCreate !!!!!!!!!!")

    }

    // onCreate > start > resume > destroy
    // 대략적인 앱 실행 순서들
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")

    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")

    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: stop~~~") //앱 정지, 배터리 안달게
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: activity end~~~") //액티비티 종료
    }


    //화면 돌리면 앱이 onCreate되는데 그거 고치기
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        //onStop 이후에 동작하고 bundle에 값을 저장한다
        Log.d(TAG, "onSaveInstanceState~~~ save")
        outState.putString("data11", "사과") //값 저장~~
        outState.putInt("data22", cnt)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)

        //onStop 이후에 동작하고 bundle에 값을 저장한다
        Log.d(TAG, "onRestoreInstanceState~~~ restore")

        var name = savedInstanceState.getString("data11")
        cnt = savedInstanceState.getInt("data22")
        binding.exText.text = cnt.toString() //값을 여기다 다시 넣어서 유지된것처럼 보인다
    }



}





