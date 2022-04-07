package com.mohitbhavsar.familyspotter

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mohitbhavsar.familyspotter.databinding.ActivityLoginBinding
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {


    //    view binding
    private lateinit var binding: ActivityLoginBinding


    // Creating firebaseAuth object
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        ============
//        initial remove top bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
        supportActionBar?.hide()

//      hide status bar
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        ============

        // initialising Firebase auth object
        auth = FirebaseAuth.getInstance()


        binding.tvRegister.setOnClickListener {
            val intent = Intent(this , RegisterActivity::class.java)
//                    intent.putExtra("mobileNo",mobileno)
            startActivity(intent)
            finish()
        }

        binding.btnLogin.setOnClickListener {
            login()

        }






    }


    private fun login() {
        val email = binding.etEmail.text.toString()
        val pass = binding.etPassword.text.toString()


        // check pass
        if (email.isBlank() || pass.isBlank()) {
            SingleToast.show(this, "All Fields are Mandatory!", Toast.LENGTH_LONG)
            return
        }


        // calling signInWithEmailAndPassword(email, pass)
        // function using Firebase auth object
        // On successful response Display a Toast
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else
                Toast.makeText(this, "Enter Correct Email or Password!", Toast.LENGTH_SHORT).show()
        }
    }


}