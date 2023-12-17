package com.project.foodrecipes.activities

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.foodrecipes.R
import com.project.foodrecipes.db.DBManager
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash_login.*
import kotlinx.android.synthetic.main.toolbar_main.*
import java.util.*

@Suppress("DEPRECATION")
class Activity_Splash_Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_login)
        val dbManager = DBManager(this)
        dbManager.open()
        btn_login.setOnClickListener {
            var userName = et_username.text
            var password = et_password.text
            var isFound = dbManager.fetchLogin(userName.toString(), password.toString())
            if (isFound) {
                dbManager.close()
                val intent = Intent(this@Activity_Splash_Login, MainActivity::class.java)
                intent.putExtra("username", userName.toString())
                startActivity(intent)
            } else {
                Toast.makeText(this, "CannotLoginUserNotFound", Toast.LENGTH_LONG).show()
            }
        }

        tv_login_register.setOnClickListener {
            val intent = Intent(this@Activity_Splash_Login, RegisterActivity::class.java)

            startActivity(intent)
        }
    }
}