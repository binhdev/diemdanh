package vocaja.com.conversation.diemdanh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import vocaja.com.conversation.diemdanh.adapter.LearnerAdapter
import vocaja.com.conversation.diemdanh.adapter.SessionAdapter
import vocaja.com.conversation.diemdanh.data.AppDatabase
import vocaja.com.conversation.diemdanh.util.Constants

class DanhSachActivity : AppCompatActivity() {

    lateinit var db : AppDatabase
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_sach)

        init()
    }

    private fun init(){
        recyclerView = findViewById(R.id.recyclerview)
        var layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, Constants.DATABASE)
            .allowMainThreadQueries()
            .createFromAsset(Constants.DATABASE)
            .build()
        val adapter = LearnerAdapter(db.learnerDao().all(), this);
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}