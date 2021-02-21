package com.example.islamicapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islamicapp.R
import com.example.islamicapp.model.ModelData
import kotlinx.android.synthetic.main.list_data.view.*

class DataAdapter(private val modelData: List<ModelData>) : RecyclerView.Adapter<DataAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_data, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(listViewHolder: ListViewHolder, i: Int) {
        val dataModel = modelData[i]
        listViewHolder.txtId.text = dataModel.id
        listViewHolder.txtName.text = dataModel.name
        listViewHolder.txtArab.text = dataModel.arab
        listViewHolder.txtTask.text = dataModel.task

        val isExpandable : Boolean = modelData[i].expandable
        listViewHolder.expandableLayout.visibility =
            if(isExpandable) {
                listViewHolder.ind.setImageResource(R.drawable.ic_down)
                View.VISIBLE
            }else {
                listViewHolder.ind.setImageResource(R.drawable.ic_side)
                View.GONE
            }
        listViewHolder.linearLayout.setOnClickListener {
            val dataModel = modelData[i]
            dataModel.expandable = !dataModel.expandable
            notifyItemChanged(i)
        }
    }

    override fun getItemCount(): Int {
        return modelData.size
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtId: TextView = itemView.txtId
        var txtName: TextView = itemView.txtName
        var txtArab: TextView = itemView.txtArab
        var txtTask: TextView = itemView.txtTask
        var linearLayout: LinearLayout = itemView.linearlayout
        var expandableLayout: RelativeLayout = itemView.expandablelayout
        var ind: ImageView = itemView.indc

    }

}