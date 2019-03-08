package kr.kwonho87.firebasedbsample.widget

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kr.kwonho87.firebasedbsample.R

class MyAdapter: RecyclerView.Adapter<MyViewHolder>() {

    private var mDataList = ArrayList<String>()

    fun setData(list: ArrayList<String>) {
        this.mDataList = list
    }

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_row, null)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvId.text = mDataList[position]
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }
}