package com.example.androidbasic

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.jar.Attributes.Name

const val TAG = "myLog" // 로그용 상수 하나 만들어놓기


class MainActivity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main5)

        //xml에 위젯을
        var genderBtn: Button = findViewById(R.id.gender_btn)

        var genderText: TextView = findViewById(R.id.gender_text)

        var birthBtn: Button = findViewById(R.id.birth_btn)
        var birthText: TextView = findViewById(R.id.birth_text)

        //genderBtn.setOnClickListner(View.OnClickListener( { v: View? ->}) )
        genderBtn.setOnClickListener {
            //Toast.makeText(this, "성별버튼 클릭", Toast.LENGTH_SHORT).show()

//            AlertDialog.Builder(this)
//                .setIcon(R.drawable.ic_launcher_background)
//                .setTitle("제목123")
//                .setMessage("종료하시겠습니까")
//                //.setPositiveButton("예", DialogInterface.OnClickListener({dialog, which -> finish()}))
//                //listener 부분 마우스 올려서 뭐들어가는지 보고 쓸 수 있다
//                .setPositiveButton("예") {dialog, which ->
//                    Toast.makeText(this,"예 버튼 클릭, sleeping life", Toast.LENGTH_SHORT).show()
//                }
//                .setNegativeButton("아니요"){dialog, which ->
//                    Toast.makeText(this,"아니여 버튼 클릭, sleeping life", Toast.LENGTH_SHORT).show()
//                    dialog.dismiss() // 종료되는 거, 그냥 팝업창 종료되는 기능인듯
//                }
//                .show()


            AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_launcher_background)
                .setTitle("제목들가는곳")
                .setItems(arrayOf("여자", "남자")) { dialog/*_ 언더바로 가능*/, which ->
                    //which는 인덱스를 반환해줍니다
                    when (which) {
                        0 -> {
                            genderText.text = "여자"
                        }

                        1 -> {
                            genderText.text = "남자"

                        }
                    }
                    genderText.visibility = View.VISIBLE
                    dialog.dismiss()
                }
                .setNegativeButton("끄기") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        //이름 부분
        var nameEdit: EditText = findViewById(R.id.name_edit)
        var nameText: TextView = findViewById(R.id.name_text)

        nameEdit.addTextChangedListener {
            Toast.makeText(this, "클릭~~", Toast.LENGTH_SHORT).show()

            var text = nameEdit.text.toString() // 사용자가 입력한 값
            nameText.text = text

            //visibility 바꾸끼
            nameText.visibility = View.VISIBLE
            nameText.setTextColor(Color.BLACK) //글씨색 바꾸기
            //nameText.setBackgroundColor(Color.GREEN) // 배경ㅅ개

            val color1 = ContextCompat.getColor(this, R.color.myColor1)
            val color2 = ContextCompat.getColor(this, R.color.myColor2)


            val color3 = ContextCompat.getColor(this, R.color.myColor3)

            nameText.setTextColor(color2)
            //nameText.setBackgroundColor(color2)

            nameEdit.setOnKeyListener{v, keyCode, event ->

                //사용자가 키를 클릭하면
                Log.d(TAG, "onCreate: $event")

                if (event.action == KeyEvent.ACTION_DOWN) {
                    if (event.keyCode == KeyEvent.KEYCODE_ENTER){
                        //event 객체 안의 액숀 값이 저거랑 같으면 동작한다
                        nameText.text = nameEdit.toString()
                        nameText.visibility = View.VISIBLE
                    }

                }
                false
            }



        }






        //생일 선택 버튼
        birthBtn.setOnClickListener {
            //오늘날짜 구하기
            val cal: Calendar = Calendar.getInstance()
            val yy = cal.get(Calendar.YEAR)
            val mm = cal.get(Calendar.MONTH)
            val dd = cal.get(Calendar.DAY_OF_MONTH)

            //로그창 << 맨위에 상수 만듬
            Log.d(TAG, "onCreate: $yy, $mm, $dd")
            //로그캣에서 로그볼 수 있음

//            val dialog123 =DatePickerDialog(this,{ view, year, month, dayOfMonth -> /*OK 버튼을 누를때 무언가 동작하는 곳*/},yy,mm,dd)
            val dialog123 = DatePickerDialog(this, { view, year, month, dayOfMonth ->

                /*OK 버튼을 누를때 무언가 동작하는 곳*/
                //사용자가 선택한 날짜 문자형식으로 저장
                val selected = "$year-${month}-$dayOfMonth"
                Log.d(TAG, "onCreate: $selected")
                
                //현재시간을 밀리초로 바꿔서 사용자 밀리초 값을 빼서 년도로 바꾼다
                val nowMillis = System.currentTimeMillis() //1970년에서 현재까지의 밀리초

                val sdf = SimpleDateFormat("yyyy-MM-dd") // 날짜 형식
                val date = sdf.parse(selected)
                val millis = date.time //밀리초를 구함

                val result = (nowMillis - millis) / 1000 / 60 / 60 / 24 / 365 //년도로 환산
                Log.d(TAG, "onCreate~~~~ $result")
                
                //Q. 나이 숨김영역에 result 로 몇살인지 구하기

                birthText.text = "$result 세"
                birthText.visibility = View.VISIBLE

            }, yy, mm, dd)

            dialog123.datePicker.maxDate = System.currentTimeMillis() //현재 날짜 다음 값을 막는다
            dialog123.show() //로그를 보여줘


        }






    }

    //xml에서 이벤트 연결하기
    fun handleClick(v: View) {
        //context를 상속받고, 텍스트 있고, 짧게상속 길게상속. 보여준다
        Toast.makeText(this, "제목을 클릭했씁니다", Toast.LENGTH_SHORT).show()
    }

}



