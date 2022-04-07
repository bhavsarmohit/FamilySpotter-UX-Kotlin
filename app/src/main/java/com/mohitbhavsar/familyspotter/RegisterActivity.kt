package com.mohitbhavsar.familyspotter

import AESEncyption.encrypt
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.mohitbhavsar.familyspotter.databinding.ActivityRegisterBinding
import java.util.regex.Matcher
import java.util.regex.Pattern


class RegisterActivity : AppCompatActivity() {


    //    view binding
    private lateinit var binding: ActivityRegisterBinding

    private lateinit var auth: FirebaseAuth;

    val db=FirebaseFirestore.getInstance()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

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


        binding.tvLogin.setOnClickListener {
            val intent = Intent(this , LoginActivity::class.java)
//                    intent.putExtra("mobileNo",mobileno)
            startActivity(intent)
            finish()
        }

        binding.btnRegister.setOnClickListener {
            signUpUser()
        }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
//        if(currentUser) {
//            SingleToast.show(this, "User Logged:$currentUser", Toast.LENGTH_LONG)
//        }else{
//            SingleToast.show(this, "User Logged:$currentUser", Toast.LENGTH_LONG);
//        }

//        updateUI(currentUser)
    }


    private fun signUpUser() {
        val name = binding.etName.text.toString()
        val mobile = binding.etMobile.text.toString()
        val email = binding.etEmail.text.toString()
        val pass = binding.etPassword.text.toString()
//        val confirmPassword = etConfPass.text.toString()

        // check pass
        if (email.isBlank() || pass.isBlank() || name.isBlank() || mobile.isBlank()) {
            SingleToast.show(this, "All Fields are Mandatory!", Toast.LENGTH_LONG)

            return
        }

        if(mobile.length!=10){
            SingleToast.show(this, "Mobile number required 10 digits!", Toast.LENGTH_LONG)
            return
        }

        if(!isValidEmail(email)){
            SingleToast.show(this, "Please Enter Correct Email!", Toast.LENGTH_LONG)
            return
        }

        if(pass.length<8){
            SingleToast.show(this, "Minimum 8 Digits Password Mandatory!", Toast.LENGTH_LONG)
            return
        }

//        if (pass != confirmPassword) {
//            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
//                .show()
//            return
//        }

        // If all credential are correct
        // We call createUserWithEmailAndPassword
        // using auth object and pass the
        // email and pass in it.
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                SingleToast.show(this, "Successfully Signed Up", Toast.LENGTH_LONG)
//                finish()

                var currentuserUID = FirebaseAuth.getInstance().currentUser!!.uid
                SingleToast.show(this,currentuserUID, Toast.LENGTH_LONG)

//                var enPass = encrypt(pass)

                val userData = hashMapOf(
                    "UID" to currentuserUID,
                    "name" to name,
                    "mobile" to mobile,
                    "email" to email,
                    "pass" to pass
                )

                db.collection("UsersData").document(currentuserUID)
                    .set(userData)
                    .addOnSuccessListener { Log.d(TAG, "User Data Snapshot Written Successful!") }
                    .addOnFailureListener { e -> Log.w(TAG, "Error writing User Data Snapshot.", e) }

                val intent = Intent(this , MainActivity::class.java)
//              intent.putExtra("mobileNo",mobileno)
                startActivity(intent)
                finish()

            } else {
                SingleToast.show(this, "Singed Up Failed! try after some time.", Toast.LENGTH_LONG)

            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

//
//    fun isValidPassword(password: String?): Boolean {
//        val pattern: Pattern
//        val matcher: Matcher
//        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
//        pattern = Pattern.compile(PASSWORD_PATTERN)
//        matcher = pattern.matcher(password)
//        return matcher.matches()
//    }

}