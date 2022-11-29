package vocaja.com.conversation.diemdanh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import vocaja.com.conversation.diemdanh.adapter.SessionAdapter
import vocaja.com.conversation.diemdanh.data.AppDatabase
import vocaja.com.conversation.diemdanh.util.Constants

class SessionActivity : AppCompatActivity() {

    lateinit var db : AppDatabase
    lateinit var recyclerView: RecyclerView
    var action = Constants.ACTION_THONGKE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session)

        initGUI()
        initData()
    }

    private fun initGUI(){
        recyclerView = findViewById(R.id.recyclerview)
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager

        action = intent.getIntExtra(Constants.ACTION, Constants.ACTION_THONGKE)
        when(action){
            Constants.ACTION_DIEMDANH -> setTitle(R.string.label_diemdanh)
            Constants.ACTION_THONGKE -> setTitle(R.string.label_thongke)
            else -> {

            }
        }
    }

    private fun initData(){
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, Constants.DATABASE)
            .allowMainThreadQueries()
            .createFromAsset(Constants.DATABASE)
            .build()
        val adapter = SessionAdapter(db.sessionDao().all(), this, action);
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}