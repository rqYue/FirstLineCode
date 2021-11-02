package com.rq.chapter_13

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

// 包含姓名与年龄，此处只将姓名对外部暴露
data class User(val firstName: String, var lastName: String, var age: Int)

class UserViewModel() :ViewModel() {
    // 设置为 私有的，整个 User 不对外暴露
    private val userLiveData = MutableLiveData<User>()
    // 将 userLiveData 转化为 userName作为包裹 String类型的 LiveData，暴露给外部作为可观察的LivaData
    // 当 userLiveData 的数据发生变化时，map() 方法会监听到变化并执行转换函数中的逻辑，然后将转换后的数据通知给 userName的观察者
    val userName: LiveData<String> = Transformations.map(userLiveData) { user ->
        "${user.firstName} ${user.lastName}"
    }
}