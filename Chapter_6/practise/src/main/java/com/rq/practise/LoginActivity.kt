package com.rq.practise

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login.setOnClickListener {
            val account =  accountEdit.text.toString()
            val password = passwordEdit.text.toString()

            if(account == "account" && password == "123456"){
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