package com.example.a9patch

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val msgList = ArrayList<Msg>()
    // 进行延迟初始化
    private lateinit var adapter : MsgAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMsg()
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        // 判断是否已经初始化，避免重复初始化
        if(! ::adapter.isInitialized) {
            adapter = MsgAdapter(msgList)
        }
        recyclerView.adapter = adapter
        send.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            send -> {
                val content = inputText.text.toString()
                if(content.isNotEmpty()){
                    val msg = Msg(content, Msg.TYPE_SENT)
                    msgList.add(msg)
                    // 有消息时，刷新 RecycleView 中的显示
                    adapter?.notifyItemInserted(msgList.size - 1)
                    // 将当前位置定位到最后一行
                    recyclerView.scrollToPosition(msgList.size - 1)
                    // 消息发送之后，将输入框清空
                    inputText.setText("")
                }
            }

        }
    }

    private fun initMsg() {
        val msg1 = Msg("aaaaaaaaa", Msg.TYPE_RECEIVED);
        val msg2 = Msg("bbbbbbbbbbbb", Msg.TYPE_SENT);
        val msg3 = Msg("ccccccc", Msg.TYPE_RECEIVED);
        msgList.add(msg1)
        msgList.add(msg2)
        msgList.add(msg3)
    }
}