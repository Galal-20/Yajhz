package com.galal.yajhz.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.galal.yajhz.API.ApiService
import com.galal.yajhz.Data.SignUpRequest
import com.galal.yajhz.databinding.ActivitySignUpBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Sign_Up : AppCompatActivity() {
    private val binding:ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    private lateinit var name:String
    private lateinit var email: String
    private lateinit var phone: String
    private lateinit var password: String
    private lateinit var confirmPassword:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bind()
    }

    private fun bind(){
        binding.btSignUp.setOnClickListener{
            name = binding.edName.text.toString().trim()
            email = binding.edEmail.text.toString().trim()
            phone = binding.edPhone.text.toString().trim()
            password = binding.edPassword.text.toString().trim()
            confirmPassword = binding.edConfirmPassword.text.toString().trim()

            if (name.isNotEmpty() || email.isNotEmpty() || phone.isNotEmpty() || password.isNotEmpty() || password.isNotEmpty() || confirmPassword.isNotEmpty())
            {
                val apiService = Retrofit.Builder()
                    .baseUrl("https://yogahez.mountasher.online/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)

                val signUpRequest = SignUpRequest(name, email, phone, password)
                lifecycleScope.launch {
                    try {
                        val response = apiService.signUpUser(signUpRequest)
                        val status = response.status
                        val message = response.message
                    } catch (e: Exception) {
                    }
                }

                Toast.makeText(this,"Sign Up successful",Toast.LENGTH_SHORT).show()
                Intent(this,MainActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }else {
                Toast.makeText(this,"Some of fields are empty",Toast.LENGTH_SHORT).show()
            }
        }
        binding.textLogin.setOnClickListener {
            Intent(this, Login::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}