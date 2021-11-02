package com.rq.practise

import android.content.Intent
import android.media.midi.MidiOutputPort
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val prefs = getPreferences(MODE_PRIVATE)
        val isRemember  = prefs.getBoolean("remember_password", false);

        // 如果上一次设置了记住密码，则对账号和密码进行自动填充
        if(isRemember) {
            val account =  prefs.getString("account", "")
            val password = prefs.getString("password", "")
            accountEdit.setText(account)
            passwordEdit.setText(password)
            rememberPassword.isChecked = true
        }

        login.setOnClickListener {

            val account =  accountEdit.text.toString()
            val password = passwordEdit.text.toString()

            if(account == "account" && password == "123456"){

                val editor = prefs.edit()
                // 检查复选框是否被选中
                if(rememberPassword.isChecked){
                    editor.putBoolean("remember_password", true)
                    editor.putString("account", account)
                    editor.putString("password", password)
                }
                else{
                    //对SharedPreferences 保存的设置进行清除
                    editor.clear()
                }
                editor.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "account or password is wrong", Toast.LENGTH_LONG).show()
            }
        }
    }
}