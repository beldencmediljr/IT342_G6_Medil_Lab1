package com.example.userauthapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.userauthapp.api.RetrofitClient
import com.example.userauthapp.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvRegister = findViewById<TextView>(R.id.tvRegisterLink)

        btnLogin.setOnClickListener {
            val user = User(findViewById<EditText>(R.id.etUsername).text.toString(), null, findViewById<EditText>(R.id.etPassword).text.toString())

            RetrofitClient.instance.login(user).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        Toast.makeText(applicationContext, "Login Successful!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@MainActivity, DashboardActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }

        tvRegister.setOnClickListener { startActivity(Intent(this, RegisterActivity::class.java)) }
    }
}