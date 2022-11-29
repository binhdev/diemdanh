package vocaja.com.conversation.diemdanh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import vocaja.com.conversation.diemdanh.adapter.LearnerAdapter
import vocaja.com.conversation.diemdanh.adapter.ThongKeAdapter
import vocaja.com.conversation.diemdanh.data.AppDatabase
import vocaja.com.conversation.diemdanh.data.entity.ThongKe
import vocaja.com.conversation.diemdanh.util.Constants

class StatisticActivity : AppCompatActivity() {

    lateinit var db : AppDatabase
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistic)

        init()
    }

    private fun init(){
        val sessionId = intent.getIntExtra(Constants.SESSION_ID, 0)
        recyclerView = findViewById(R.id.recyclerview)
        var layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, Constants.DATABASE)
            .allowMainThreadQueries()
            .createFromAsset(Constants.DATABASE)
            .build()

        var learnerList = db.learnerDao().all()

        var thongKeList = mutableListOf<ThongKe>()

        learnerList.forEach {
            if(db.learnerSessionDao().find(it.Id, sessionId) != null){
                val thongKe = ThongKe(it.Id, it.name, true)
                thongKeList.add(thongKe)
            }else{
                val thongKe = ThongKe(it.Id, it.name, false)
                thongKeList.add(thongKe)
            }
        }
        val adapter = ThongKeAdapter(thongKeList, this);
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}