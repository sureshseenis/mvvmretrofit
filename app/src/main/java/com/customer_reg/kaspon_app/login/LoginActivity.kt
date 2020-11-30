package com.customer_reg.kaspon_app.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.customer_reg.kaspon_app.R
import com.customer_reg.kaspon_app.databinding.ActivityLoginBinding
import com.customer_reg.kaspon_app.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*

class LoginActivity : AppCompatActivity() {

    lateinit var loginBinding: ActivityLoginBinding
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginBinding.loginViewModel = viewModel



        viewModel.loginResponse.observe(this, Observer {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        })
        
        loginBinding.root.btn_login.setOnClickListener {
            if (validation()) {
                var hashMap = HashMap<String, String>()
                hashMap.put("username", loginBinding.root.et_username.text.toString())
                hashMap.put("password", loginBinding.root.et_password.text.toString())
                viewModel.loginUser(hashMap)
            }
        }

        loginBinding.root.btn_register.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }

    }


    private fun validation(): Boolean {
        if (loginBinding.root.et_username.text?.toString().equals("")!!) {
            Toast.makeText(this, "Enter username", Toast.LENGTH_SHORT).show()
            return false

        } else if (loginBinding.root.et_password.text?.toString().equals("")!!) {
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }


    }
}