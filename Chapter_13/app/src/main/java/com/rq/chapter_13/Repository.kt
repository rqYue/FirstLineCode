package com.rq.chapter_13

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.GenericArrayType

object Repository {

    fun getUser(userId: String): LiveData<User> {
        val liveData = MutableLiveData<User>()
        liveData.value = User(userId, userId, 0)
        return liveData
    }

    fun refresh(): LiveData<User> {
        val liveData = MutableLiveData<User>()
        liveData.value = User("", "", 0)
        return liveData
    }
}

class ViewModel(): ViewModel() {

    // 私有的LiveData userIdLiveData 用于接收数据变化并进行转换
    private val userIdLiveData = MutableLiveData<String>()

    // 新建LiveData user 用于外部观察数据
    //2、 userIdLiveData 中的数据发生变化时，switchMap() 方法会立即执行
    val user: LiveData<User> = Transformations.switchMap(userIdLiveData) { userId ->
        //3、 转换函数返回调用 getUser获取到的 LiveData
        Repository.getUser(userId)
    }

    //1、 外部调用 getUser 获取数据，并不直接获取返回值，而是使 userIdLiveData 数据发生变化
    fun getUser(userId: String) {
        userIdLiveData.value = userId
    }

//    //直接传入 userId进行获取将每次创建一个新的 LiveData，无法正常观察数据变化
//    fun getUser(userId: String): LiveData<User> {
//        return Repository.getUser(userId)
//    }
}

class MyViewModel(): ViewModel() {

    // 创建空 LiveData
    private val refreshLiveData = MutableLiveData<Any?>()

    val refreshResult = Transformations.switchMap(refreshLiveData) {
        Repository.refresh()
    }

    //实际上只是取出 refreshLiveData 的数据在设置进去，为了触发数据变化进行监听
    fun refresh() {
        refreshLiveData.value = refreshLiveData.value
    }
}
//class UserActivity: AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        getUserBtn.setOnClickListener {
//            viewModel.getUser(userId)
//        }
//
//        // 进行数据的监听，此处监听的是 LiveData user
//        viewModel.user.observe(this) { user ->
//            textView.text = user.toString()
//        }
//    }
//
//}