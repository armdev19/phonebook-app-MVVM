package com.infernal93.phonebookappmvvm.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.infernal93.phonebookappmvvm.R

class LoginActivity : AppCompatActivity() {
    private val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
