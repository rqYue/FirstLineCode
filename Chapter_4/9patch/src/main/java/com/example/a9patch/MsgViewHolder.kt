package com.example.a9patch

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//定义一个密封类，有两个子类
sealed class MsgViewHolder(view: View) : RecyclerView.ViewHolder(view)

class LeftViewHolder(view : View) : MsgViewHolder(view) {
    val leftMsg : TextView = view.findViewById(R.id.leftMsg)
}

class RightViewHolder(view : View) : MsgViewHolder(view) {
    val rightMsg : TextView = view.findViewById(R.id.rightMsg)
}
