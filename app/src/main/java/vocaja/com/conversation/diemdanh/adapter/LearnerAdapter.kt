package vocaja.com.conversation.diemdanh.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vocaja.com.conversation.diemdanh.R
import vocaja.com.conversation.diemdanh.data.model.Learner

class LearnerAdapter(
    private val learnerList: List<Learner>,
    private val context: Context
) : RecyclerView.Adapter<LearnerAdapter.LearnerViewHolder>() {

    class LearnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvOrder: TextView = itemView.findViewById(R.id.tv_order)
        val tvLearnerName: TextView = itemView.findViewById(R.id.learner_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearnerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LearnerViewHolder(inflater.inflate(R.layout.learner_item, parent, false))
    }

    override fun onBindViewHolder(holder: LearnerViewHolder, position: Int) {
        holder.tvOrder.text = (position + 1).toString()
        holder.tvLearnerName.text = learnerList[position].name
    }

    override fun getItemCount(): Int {
        return learnerList.size
    }
}