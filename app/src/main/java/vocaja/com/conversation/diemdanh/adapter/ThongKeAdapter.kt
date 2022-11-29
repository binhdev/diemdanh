package vocaja.com.conversation.diemdanh.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vocaja.com.conversation.diemdanh.R
import vocaja.com.conversation.diemdanh.data.entity.ThongKe

class ThongKeAdapter(
    private val thongKeList: List<ThongKe>,
    private val context: Context
) : RecyclerView.Adapter<ThongKeAdapter.ThongKeViewHolder>() {

    class ThongKeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvOrder: TextView = itemView.findViewById(R.id.tv_order)
        val tvThongKeName: TextView = itemView.findViewById(R.id.learner_name)
        val tvStatus: TextView = itemView.findViewById(R.id.tv_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThongKeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ThongKeViewHolder(inflater.inflate(R.layout.thongke_item, parent, false))
    }

    override fun onBindViewHolder(holder: ThongKeViewHolder, position: Int) {
        holder.tvOrder.text = (position + 1).toString()
        holder.tvThongKeName.text = thongKeList[position].name

        if(thongKeList[position].status) {
            holder.tvStatus.text = context.getString(R.string.label_co)
            holder.tvStatus.setTextColor(Color.GREEN)
        }
        else {
            holder.tvStatus.text = context.getString(R.string.label_vang)
            holder.tvStatus.setTextColor(Color.RED)
        }
    }

    override fun getItemCount(): Int {
        return thongKeList.size
    }
}