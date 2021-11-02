package com.example.addfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_top.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 设置一个 button 用于切换 fragment
        button.setOnClickListener{
            replaceFragment(ReplaceFragment())
        }

        // 该位置为一个空的 fragment，需要先将 bottomFragment 添加
        replaceFragment(BottomFragment())

        // activity 中获取 fragment 实例
//        val fragment = fragmentTop as Fragment
    }

    /*
        动态替换 fragment
        1. 创建待添加 Fragment 实例
        2. 在Activity 中使用 getSupportFragmentManager() 获取 FragmentManager
        3. 调用 beginTransaction() 方法开启一个事务
        4. 向容器内添加或者替换 Fragment，一般使用 replace 方法实现，需要传入容器的 id 以及新添加的 fragment 实例
        5. 使用 commit() 方法提交事务
     */

    private fun replaceFragment( fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentBottom, fragment)
        // 设置 返回栈 的效果，按下 Back 键，返回到上一个 fragment
        transaction.addToBackStack(null)
        transaction.commit()
    }
}