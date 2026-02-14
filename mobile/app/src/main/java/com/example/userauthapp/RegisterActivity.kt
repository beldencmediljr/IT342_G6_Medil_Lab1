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

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        findViewById<Button>(R.id.btnRegister).setOnClickListener {
            val newUser = User(
                findViewById<EditText>(R.id.etRegUsername).text.toString(),
                findViewById<EditText>(R.id.etRegEmail).text.toString(),
                findViewById<EditText>(R.id.etRegPassword).text.toString()
            )

            RetrofitClient.instance.register(newUser).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        Toast.makeText(applicationContext, "Registration Successful!", Toast.LENGTH_LONG).show()
                        finish() // Goes back to Login screen
                    } else {
                        Toast.makeText(applicationContext, "Registration Failed", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}