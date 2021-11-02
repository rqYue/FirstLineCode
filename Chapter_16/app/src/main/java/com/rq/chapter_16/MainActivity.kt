package com.rq.chapter_16

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rq.permissionx.PermissionX
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeCallbtn.setOnClickListener {
            // 可以申请多组权限
            PermissionX.request(this,
            Manifest.permission.CALL_PHONE, Manifest.permission.INTERNET) { allGranted, deniedList ->
                if (allGranted) {
                    call()
                } else {
                    Toast.makeText(this, "you denied $deniedList", Toast.LENGTH_SHORT).show();
                }

            }
        }

//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) !=
//                PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
//        } else {
//            call()
//        }
    }

//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//        when (requestCode) {
//            1 -> {
//                if (grantResults.isNotEmpty() &&
//                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    call()
//                } else {
//                    Toast.makeText(this, "You denied the Permission", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }
//
    private fun call() {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:123456")
        startActivity(intent)
    }
}