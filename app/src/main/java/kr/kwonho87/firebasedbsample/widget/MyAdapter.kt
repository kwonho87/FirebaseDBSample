package kr.kwonho87.firebasedbsample.widget

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kr.kwonho87.firebasedbsample.Data
import kr.kwonho87.firebasedbsample.R

class MyAdapter: RecyclerView.Adapter<MyViewHolder>() {

    private var mDataList = ArrayList<Data>()

    fun setData(list: ArrayList<Data>) {
        this.mDataList = list
    }

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_row, null)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvId.text = mDataList[position].id
        holder.tvName.text = mDataList[position].name
        holder.tvAge.text = mDataList[position].age
        holder.tvGender.text = mDataList[position].gender
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }
}