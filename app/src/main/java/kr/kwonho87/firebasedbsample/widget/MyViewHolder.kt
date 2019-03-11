package kr.kwonho87.firebasedbsample.widget

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_main.view.*

class MyViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
    var tvId = itemView.tvId!!
    var tvName = itemView.tvName!!
    var tvAge = itemView.tvAge!!
    var tvGender = itemView.tvGender!!
}