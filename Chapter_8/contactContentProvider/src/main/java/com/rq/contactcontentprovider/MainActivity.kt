package com.rq.contactcontentprovider

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val contactList = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, contactList)
        contactView.adapter = adapter

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            // 如果未获取权限，则进行权限请求
            //requestCode 为唯一值即可，此处设为 1
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_CONTACTS), 1)
        } else {
            // 如果已经获取权限，直接进行操作
            readContacts()
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
                    readContacts()
                }
                else {
                    Toast.makeText(this, "you denied the permission", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun readContacts(){
        contentResolver.query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null, null, null, null)?.apply {
            while (moveToNext()){
                // 获取联系人姓名
                val displayName = getString(getColumnIndex( ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME ))
                // 获取联系人手机号码
                val number = getString( getColumnIndex( ContactsContract.CommonDataKinds.Phone.NUMBER))

                contactList.add("$displayName: $number")
            }
            adapter.notifyDataSetChanged()
            close()
        }
    }
}