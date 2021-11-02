package com.rq.permissionx

import androidx.fragment.app.FragmentActivity

// 设置为单例类
object PermissionX {

    private const val TAG = "InvisibleFragment"

    // 接收一个 FragmentActivity 、一个可变长度的 permissions 参数列表，以及callback 回调
    fun request(activity: FragmentActivity, vararg permissions: String, callback: PermissionCallback) {
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        // 判断是否已经包含指定 TAG的 Fragment，即判断 InvisibleFragment 是否存在
        val fragment = if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            // 创建 InvisibleFragment 并且立即执行添加操作，保证返回时Fragment 已经添加完成
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        // 使用* 表示将一个数组转变为可变长度参数传递过去
        fragment.requestNow(callback, *permissions)
    }

}