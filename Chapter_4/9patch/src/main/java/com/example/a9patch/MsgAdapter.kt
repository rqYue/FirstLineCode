package com.example.a9patch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MsgAdapter(val msgList : List<Msg>) : RecyclerView.Adapter<MsgViewHolder>(){

//    inner class LeftViewHolder(view : View) : RecyclerView.ViewHolder(view) {
//        val leftMsg : TextView = view.findViewById(R.id.leftMsg)
//    }
//
//    inner class RightViewHolder(view : View) : RecyclerView.ViewHolder(view) {
//        val rightMsg : TextView = view.findViewById(R.id.rightMsg)
//    }

    //设置两多种不同的布局，根据type进行选择具体生成哪种布局
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MsgViewHolder =
        if (viewType == Msg.TYPE_RECEIVED){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_left_item, parent, false)
            LeftViewHolder(view)
        } else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item, parent, false)
            RightViewHolder(view)
        }

    override fun getItemCount() = msgList.size

    override fun onBindViewHolder(holder: MsgViewHolder, position: Int) {
        val msg = msgList[position]
        when(holder){
            is LeftViewHolder -> holder.leftMsg.text = msg.content
            is RightViewHolder -> holder.rightMsg.text = msg.content

            // 使用密封类不需要为语法检查进行额外的判断
//            else -> throw IllegalArgumentException()
        }
    }

    // 由于有不同的布局，需要对 getItemViewType 方法进行重写
    override fun getItemViewType(position: Int): Int {
        val msg = msgList[position]
        return msg.type
    }
}