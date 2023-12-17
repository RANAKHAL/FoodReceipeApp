package com.project.foodrecipes.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.foodrecipes.R
import com.project.foodrecipes.db.DBManager
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_splash_login.*
import kotlinx.android.synthetic.main.toolbar_main.*
import java.util.*

@Suppress("DEPRECATION")
class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val dbManager = DBManager(this)
        dbManager.open()
        btn_register.setOnClickListener {
            val userName = et_register_user_name.text
            val email = et_register_email.text
            val phone = et_register_phone.text
            val password = et_register_password.text
            if (userName.isEmpty()) {
                Toast.makeText(this, "UserName Mandatory", Toast.LENGTH_LONG).show()
            } else if (email.isEmpty()) {
                Toast.makeText(this, "Email Mandatory", Toast.LENGTH_LONG).show()
            } else if (phone.isEmpty()) {
                Toast.makeText(this, "Phone Mandatory", Toast.LENGTH_LONG).show()
            } else if (password.isEmpty()) {
                Toast.makeText(this, "Password Mandatory", Toast.LENGTH_LONG).show()
            } else {
                var isUserExists = dbManager.isUserExists(userName.toString());
                if (isUserExists) {
                    Toast.makeText(this, "UserName already exists, Please Choose another one!!", Toast.LENGTH_LONG).show()
                } else {
                    dbManager.insertUser(
                        userName.toString(),
                        email.toString(),
                        Integer.parseInt(phone.toString()),
                        password.toString()
                    )

                    val intent = Intent(this@RegisterActivity, Activity_Splash_Login::class.java)
                    startActivity(intent)
                }
                dbManager.close()
            }
        }
    }
}