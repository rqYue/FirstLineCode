package com.rq.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import com.rq.room.database.AppDatabase
import com.rq.room.database.entity.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 使用删除原有数据的方式进行数据库强制升级
//        Room.databaseBuilder(this.applicationContext, AppDatabase::class.java, "app_database")
//            .fallbackToDestructiveMigration()
//            .build()

        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("a first", "a last", 22)
        val user2 = User("b first", "b last", 33)

        addDataBtn.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }

        updateDataBtn.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }

        deleteDataBtn.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("b last")
            }
        }

        queryDataBtn.setOnClickListener {
            thread {
                for (user in userDao.loadAllUsers()) {
                    Log.d("MainActivity", user.toString())
                }
            }
        }

    }
}
