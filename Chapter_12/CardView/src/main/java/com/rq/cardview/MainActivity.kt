package com.rq.cardview

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Integer.max
import java.lang.RuntimeException
import java.time.Duration
import kotlin.concurrent.thread
import kotlin.jvm.internal.Intrinsics

class MainActivity : AppCompatActivity() {

    val fruits = mutableListOf(Fruit("Apple", R.drawable.apple), Fruit("Banana",
        R.drawable.banana), Fruit("Orange", R.drawable.orange), Fruit("Watermelon",
        R.drawable.watermelon), Fruit("Pear", R.drawable.pear), Fruit("Grape",
        R.drawable.grape), Fruit("Pineapple", R.drawable.pineapple), Fruit("Strawberry",
        R.drawable.strawberry), Fruit("Cherry", R.drawable.cherry), Fruit("Mango",
        R.drawable.mango))

    val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        // 设置导航按钮
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        // 设置默认选中
        navView.setCheckedItem(R.id.navCall)
        // 设置选项被点击后的行为
        navView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            true
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Data deleted", Snackbar.LENGTH_LONG)
                .setAction("Undo") {
                    Toast.makeText(this, "FAB clicked", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

        initFruits()
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        val adapter = FruitAdapter(this, fruitList)
        recyclerView.adapter = adapter

        // 设置下拉进度条的颜色
        swipeRefresh.setColorSchemeResources(R.color.design_default_color_primary)
        // 下拉刷新的监听器
        swipeRefresh.setOnRefreshListener {
            refreshFruits(adapter)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
            R.id.backup -> Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show()
            R.id.delete -> Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show()
            R.id.settings -> Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    private fun initFruits() {
        fruitList.clear()
        repeat(50) {
            val index = (0 until fruits.size).random()
            fruitList.add(fruits[index])
        }
    }

    private fun refreshFruits(adapter: FruitAdapter) {
        thread {
            Thread.sleep(2000)
            // 切换到主线程，对界面进行更新
            runOnUiThread {
                initFruits()
                adapter.notifyDataSetChanged()
                swipeRefresh.isRefreshing = false
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun test(){
        val a = 10
        val b = 15
        val c = 5

        val largest = max(a, b, c)
    }

    fun max(vararg nums: Int): Int {
        var maxNum = Int.MIN_VALUE
        for(num in nums) {
            maxNum = kotlin.math.max(maxNum, num)
        }
        return maxNum
    }

    fun <T: Comparable<T>> max(vararg nums: T): T {
        if (nums.isEmpty()) throw RuntimeException("Params can not be empty.")
        var maxNum = nums[0]
        for (num in nums) {
            if (num > maxNum) {
                maxNum = num
            }
        }
        return maxNum
    }

    fun toast(context: Context) {
        "This is Toast".showToast(context, Toast.LENGTH_LONG)
        R.string.app_name.showToast(context, Toast.LENGTH_LONG)
    }

    fun String.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, this, duration).show()
    }

    fun Int.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, this, duration).show()
    }


    fun snackbar(view: View) {
        Snackbar.make(view, "This is Snackbar", Snackbar.LENGTH_SHORT)
            .setAction("Action") {
                // 处理具体逻辑
            }
            .show()

        view.showSnackbar("This is Snackbar")

        view.showSnackbar("This is Snackbar","Action") {
            // 处理具体逻辑
        }

    }

    fun View.showSnackbar(text: String, actionText: String? = null,
                          duration: Int = Snackbar.LENGTH_SHORT, block: (() -> Unit)? = null) {
        val snackbar = Snackbar.make(this, text, duration)
        if (actionText != null && block != null) {
            snackbar.setAction(actionText) {
                block()
            }
        }
        snackbar.show()
    }

    fun View.showSnackbar(resId: Int, actionResId: Int? = null,
                          duration: Int = Snackbar.LENGTH_SHORT, block: (() -> Unit)? = null) {
            val snackbar = Snackbar.make(this, resId, duration)
            if (actionResId != null && block != null) {
                snackbar.setAction(actionResId) {
                    block()
                }
            }
            snackbar.show()
    }



}