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
import com.example.islamicapp.model.ModelData2
import kotlinx.android.synthetic.main.list_data2.view.*

class Data2Adapter(private val modelData2: List<ModelData2>) : RecyclerView.Adapter<Data2Adapter.ListViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_data2, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(listViewHolder: ListViewHolder, i: Int) {
        val data2Model = modelData2[i]
        listViewHolder.txtId.text = data2Model.id
        listViewHolder.txtName.text = data2Model.name
        listViewHolder.txtArab.text = data2Model.arab
        listViewHolder.txtLatin.text = data2Model.latin
        listViewHolder.txtTerjemah.text = data2Model.terjemahan

        val isExpandable : Boolean = modelData2[i].expandable
        listViewHolder.expandableLayout.visibility =
            if(isExpandable) {
                listViewHolder.ind.setImageResource(R.drawable.ic_down)
                View.VISIBLE
            }else {
                listViewHolder.ind.setImageResource(R.drawable.ic_side)
                View.GONE
            }
        listViewHolder.linearLayout.setOnClickListener {
            val data2Model = modelData2[i]
            data2Model.expandable = !data2Model.expandable
            notifyItemChanged(i)
        }
    }

    override fun getItemCount(): Int {
        return modelData2.size
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtId: TextView = itemView.txtId
        var txtName: TextView = itemView.txtName
        var txtArab: TextView = itemView.txtArab
        var txtLatin: TextView = itemView.txtLatin
        var txtTerjemah: TextView = itemView.txtTerjemah
        var linearLayout: LinearLayout = itemView.linearlayout
        var expandableLayout: RelativeLayout = itemView.expandablelayout
        var ind: ImageView = itemView.indc
    }

}