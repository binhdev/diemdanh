package vocaja.com.conversation.diemdanh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView
import vocaja.com.conversation.diemdanh.util.Constants

class HomeActivity : AppCompatActivity() {
    lateinit var cvDiemDanh: CardView
    lateinit var cvThongKe: CardView
    lateinit var cvDanhSach: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        init()
    }

    private fun init(){
        cvDiemDanh = findViewById(R.id.cv_diemdanh)
        cvThongKe = findViewById(R.id.cv_thongke)
        cvDanhSach = findViewById(R.id.cv_danhsach)

        cvDiemDanh.setOnClickListener(listener)
        cvThongKe.setOnClickListener(listener)
        cvDanhSach.setOnClickListener(listener)
    }

    private val listener = View.OnClickListener {
        var intent : Intent? = null
        when(it.id){
            R.id.cv_diemdanh -> intent = Intent(this, SessionActivity::class.java).apply { putExtra(Constants.ACTION, Constants.ACTION_DIEMDANH) }
            R.id.cv_thongke -> intent = Intent(this, SessionActivity::class.java).apply { putExtra(Constants.ACTION, Constants.ACTION_THONGKE) }
            R.id.cv_danhsach -> intent = Intent(this, DanhSachActivity::class.java)
            else -> {

            }
        }
        startActivity(intent)
    }
}