package com.rq.permissionx

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

// 设设置别名，简化代码
typealias PermissionCallback = (Boolean, List<String>) -> Unit

class InvisibleFragment : Fragment() {

    // 函数类型变量，接收 Boolean 和 List<String> 这两种类型参数，没有返回值
    private var callback: PermissionCallback? = null

    // 接收一个与 callback 相同类型的函数类型参数
    // 使用 vararg 接收可变长度的 permissions 参数列表
    fun requestNow(cb: PermissionCallback, vararg  permission: String) {
        callback = cb
        // 进行权限申请
        requestPermissions(permission, 1)
    }

    // 权限申请的回调处理
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode == 1) {
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            // 判断是否所有权限都被允许
            val allGranted = deniedList.isEmpty()
            callback?.let { it(allGranted, deniedList) }
        }
    }

}