package com.example.firstapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.coding404.myfirstapp.QuizRepo
import com.example.firstapp.databinding.ActivityMainBinding
import com.example.firstapp.databinding.ActivityQuizBinding

const val TAG = "log여"

class QuizActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityQuizBinding

    private var quizTv: TextView? = null
    private var hintBtn: TextView? = null
    private var hintImg: ImageView? = null

    private var progBar: ProgressBar? = null
    private var progTv: TextView? = null
    private var one: TextView? = null
    private var two: TextView? = null
    private var three: TextView? = null
    private var four: TextView? = null
    private var nextTtn: TextView? = null

    //퀴즈데이터 연결
    private var quizList: ArrayList<QuizDTO> = QuizRepo.getQuiz()
    private var index = 0

    //유저가 선택한 값
    private var userAnswer = 0

    //옵션리스트
    private var optionList = ArrayList<TextView>()

    //정답카운트
    private var collectCnt = 0

    //유저이름
    private var name : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //툴비 설정
        setSupportActionBar(binding.quizToolbar) //툴바 활성화 하기
        supportActionBar?.title = "MyFirstToolBar" //타이틀에 넣을 값
        supportActionBar?.setDisplayHomeAsUpEnabled(true)// 뒤로가기 버튼 활성화 하기

        //방금 툴바에서 만든 뒤로가기 기능 넣기
        binding.quizToolbar.setNavigationOnClickListener { //이거 누르면 
            onBackPressedDispatcher.onBackPressed() //뒤로가기 된다
        }

        ////////////////////////////////////////////////////////////////////
        //화면에서 사용할 위젯 전역변수에 저장
        quizTv = binding.quizTv
        hintBtn = binding.hintBtn
        hintImg = binding.hintImg
        progBar = binding.progBar
        progTv = binding.progTv
        one = binding.one
        two = binding.two
        three = binding.three
        four = binding.four
        nextTtn = binding.nextBtn

        //화면에 처리할 퀴즈 데이터를 가져오기
        dataBinding()

        //이벤트 연결
//        val listener = View.OnClickListener {v ->
//            when(v.id){
//                R.id.one -> Log.d(TAG, "onCreate: 첫번째")
//                R.id.two -> Log.d(TAG, "onCreate: 두번째")
//                R.id.three -> Log.d(TAG, "onCreate:세번째 ")
//                R.id.four -> Log.d(TAG, "onCreate:네번째 ")
//            }
//        }
//
//        one?.setOnClickListener(listener)
//        two?.setOnClickListener(listener)
//        three?.setOnClickListener(listener)
//        four?.setOnClickListener(listener)

        //액티비티가 온클릭리스너를 상속받고, 오버라이딩
        one?.setOnClickListener(this)
        two?.setOnClickListener(this) //나 자신이 view.onclickListener 타입 = this
        three?.setOnClickListener(this)
        four?.setOnClickListener(this)

        //옵션리스트에 one, two, three, four객체 저장
        optionList.add(one!!)
        optionList.add(two!!)
        optionList.add(three!!)
        optionList.add(four!!)

        //첫번째 엑티비티에서 넘어온값을 전역변수로 저장
        name = intent.getStringExtra("name")


    }


    //툴바 메뉴 활성화
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(
            R.menu.toolbar_menu, menu
        ) // menuInflater는 view로 전환하는 기능, xml로 만들 메뉴 활성화
        return super.onCreateOptionsMenu(menu)
    }

    //툴바 메뉴 이벤트
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) { //item은 툴바메뉴 xml 파일의 item 이다
            R.id.menu1 -> {
                Toast.makeText(this, "메뉴1 실행", Toast.LENGTH_SHORT).show()
            }

            R.id.menu2 -> {
                Toast.makeText(this, "메뉴2 실행", Toast.LENGTH_SHORT).show()
            }

            R.id.menu3 -> {
                Toast.makeText(this, "메뉴3 실행", Toast.LENGTH_SHORT).show()
            }

            R.id.menu4 -> {
                Toast.makeText(this, "메뉴4 실행", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun dataBinding() {

        var quizDTO: QuizDTO = quizList[index] //quizList.get(0)

        quizTv?.text = quizDTO.quiz
        one?.text = quizDTO.one
        two?.text = quizDTO.two
        three?.text = quizDTO.three
        four?.text = quizDTO.four
        //프로그래스 바의 값
        progBar?.progress = index + 1
        progTv?.text = "${quizList.size} 중 ${index + 1}"
        //힌트이미지
        hintImg?.setImageResource(quizDTO.img)

        //인덱스 증가
        index++
    }

    //view.OnclickLister를 상속받을 때 구현하는 추상메서드
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.one -> { //binding.one.id랑 같음
                userAnswer = 1
                resetStyle()
                changeStyle(v)
            }

            R.id.two -> {
                userAnswer = 2
                resetStyle()
                changeStyle(v)
            }

            R.id.three -> {
                userAnswer = 3
                resetStyle()
                changeStyle(v)
            }

            R.id.four -> {
                userAnswer = 4
                resetStyle()
                changeStyle(v)
            }
            //힌트 버튼을 누르면, 숨겨놧던 화면을 보여질 수 있도록 추가.
            R.id.hint_btn -> {
                hintImg?.visibility = View.VISIBLE
            }
            //다음버튼
            R.id.next_btn -> {
                //사용자가 버튼을 클릭했는지 유효성 검사
                if (userAnswer == 0) { //선택을 안함
                    Toast.makeText(this, "정답을 선택해 주세요", Toast.LENGTH_SHORT).show()
                } else {
                    //정답체크
                    if (quizList[index - 1].answer == userAnswer) {
                        collectCnt++
                    }

                    //마지막 일때 vs 마지막이 아닌경우
                    if (index != quizList.size) { //마지막이 아닌경우
                        dataBinding() //다음 데이터 적용
                        resetStyle() //옵션 초기화
                        resetHint() //힌트 초기화
                        userAnswer = 0 //유저의 정답 초기화
                    } else { //마지막인 경우
                        val intent = Intent(this, ResultActivity::class.java)
                        startActivity(intent)
                        finish() //현재 엑티비티는 종료
                    }
                }
            }
        }
    }

    private fun changeStyle(v: View?) {
        val textView = v as TextView
        textView.setBackgroundResource(R.drawable.rectangle_click)
        textView.setTextColor(ContextCompat.getColor(this, R.color.color1))
    }

    private fun resetStyle() {
        //기존에 있던 스타일 초기화
        for (textView in optionList) {
            textView.setBackgroundResource(R.drawable.rectangle)
            textView.setTextColor(Color.parseColor("#000000"))
        }


    }

    private fun resetHint() {
        //힌트 초기화
        hintImg?.visibility = View.GONE
    }
}