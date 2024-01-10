package com.galal.yajhz.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.galal.yajhz.API.ApiService
import com.galal.yajhz.Data.LoginRequest
import com.galal.yajhz.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Login : AppCompatActivity() {
    private val binding:ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var email : String
    private lateinit var password:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bind()
    }

    private fun bind(){

        binding.btLogin.setOnClickListener {
            email = binding.edEmail.text.toString().trim()
            password = binding.edPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Some field empty", Toast.LENGTH_SHORT).show()
            }else {

                val apiService = Retrofit.Builder()
                    .baseUrl("https://yogahez.mountasher.online/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)

                val loginRequest = LoginRequest(email, password)

                lifecycleScope.launch {
                    try {
                        val response = apiService.loginUser(loginRequest)
                        val token = response.token
                    } catch (e: Exception) {

                    }
                }
                Toast.makeText(this,"Login successful",Toast.LENGTH_SHORT).show()
                Intent(this,MainActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }

        binding.textSignUp.setOnClickListener {
            Intent(this, Sign_Up::class.java).also {
                startActivity(it)
            finish()}
        }
    }




}

