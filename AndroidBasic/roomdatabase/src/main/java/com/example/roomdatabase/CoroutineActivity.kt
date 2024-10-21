package com.example.roomdatabase

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

const val TAG123 = "log여"

class CoroutineActivity : AppCompatActivity() {

    //코루틴 이해
    suspend fun example1(): String {
        delay(2000)
        return "result 1"
    }

    suspend fun example2(): String {
        delay(1000)
        return "result2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //코루틴은 비동기 같은거고
        //suspend는 일시 중단 가능한 함수이고, 비동기적인 코드에서 사용
        //suspend를 호출하려면, suspend함수이거나 or 코루틴 스코프 이어야 합니다.
        lifecycleScope.launch { //1 >> 3 >> 2
            Log.d(TAG123, "oncreate: 1. 코루틴 실행")

            val time = measureTimeMillis {//시간이 얼마나 걸리는가
                val result1 = example1()

                val result2 = example2()

                Log.d(TAG123, "onCreate: $result1")
                Log.d(TAG123, "onCreate: $result2")
            }
            Log.d(TAG123, "oncreate: 2. 코루틴 실행")
        }
        Log.d(TAG123, "oncreate: 3. 코루틴 실행")




    }
}