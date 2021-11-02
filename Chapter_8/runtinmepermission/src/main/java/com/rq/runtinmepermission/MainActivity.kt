package com.rq.runtinmepermission

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeCall.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    // 如果未获取权限，则进行权限请求
                    //requestCode 为唯一值即可，此处设为 1
                    ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.CALL_PHONE), 1)
            } else {
                // 如果已经获取权限，直接进行操作
                call()
            }

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // 授权结果返回，根据 requestCode 进行处理
        when (requestCode) {
            1 -> {
                if(grantResults.isNotEmpty() &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call()
                }
                else {
                    Toast.makeText(this, "you denied the permission", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } catch (e: SecurityException){
            e.printStackTrace()
        }
    }
}