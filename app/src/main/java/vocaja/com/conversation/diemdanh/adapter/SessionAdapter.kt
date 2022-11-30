package vocaja.com.conversation.diemdanh.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vocaja.com.conversation.diemdanh.MainActivity
import vocaja.com.conversation.diemdanh.R
import vocaja.com.conversation.diemdanh.StatisticActivity
import vocaja.com.conversation.diemdanh.data.model.Session
import vocaja.com.conversation.diemdanh.util.Constants

class SessionAdapter(
    private val sessionList: List<Session>,
    private val context: Context,
    private val action: Int
) : RecyclerView.Adapter<SessionAdapter.SessionViewHolder>() {
    class SessionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvSessionName: TextView = itemView.findViewById(R.id.session_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SessionViewHolder(inflater.inflate(R.layout.session_item, parent, false))
    }

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        holder.tvSessionName.text = sessionList[position].name
        holder.itemView.setOnClickListener {
            var intent : Intent? = null
            if(action == Constants.ACTION_DIEMDANH){
                intent = Intent(context, MainActivity::class.java).apply {
                    putExtra(Constants.SESSION_ID, sessionList[position].Id)
                }
            }
            if(action == Constants.ACTION_THONGKE){
                intent = Intent(context, StatisticActivity::class.java).apply {
                    putExtra(Constants.SESSION_ID, sessionList[position].Id)
                }
            }

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return sessionList.size
    }
}