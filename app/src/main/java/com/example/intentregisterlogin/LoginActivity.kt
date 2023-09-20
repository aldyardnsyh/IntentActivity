package com.example.intentregisterlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.Toast
import com.example.intentregisterlogin.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val TAG = "LoginActivityLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val checkBox = binding.checkBox
        val tac = "<font color=#FF000000>By checking the box you agree to our</font> " +
                "<font color=#525BFF>Terms</font> " +
                "<font color=#FF000000>and</font> " +
                "<font color=#525BFF>Conditions.</font>"
        checkBox.text = Html.fromHtml(tac)

        val login = binding.txtLogin
        val loginText = "<font color=#FF000000>Already Have an Account?</font> " +
                "<font color=#525BFF>Log In</font> "
        login.text = Html.fromHtml(loginText)

        binding.btnToHome.setOnClickListener {
            openHomeActivity()
        }

    }

    private fun openHomeActivity() {

        val name = binding.editTxtName.text.toString()
        val email = binding.editTxtEmail.text.toString()
        val phone = binding.editTxtPhone.text.toString()
        val password = binding.editTxtPassword.text.toString()

        val intent = Intent(this, RegisterActivity::class.java)
            .apply {
                // * APPLY PUT EXTRA
                putExtra("NAME", name)
                putExtra("EMAIL", email)
                putExtra("PHONE", phone)
                putExtra("PASSWORD", password)
            }

        val conditions = listOf(name.isNotEmpty(), phone.isNotEmpty(), email.isNotEmpty(), password.isNotEmpty())

        if (conditions.all { it }) {
            if (binding.checkBox.isChecked) {
                startActivity(intent)
            } else {
                showToast("Anda Belum Centang Terms & Condition")
            }
        } else {
            showToast("Field Tidak Boleh Ada Yang Kosong")
        }

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart dipanggil")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume dipanggil")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause dipanggil")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop dipanggil")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy dipanggil")
    }
}