package com.telyu.nourimate.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.telyu.nourimate.databinding.ActivityPasswordPopupBinding
import com.telyu.nourimate.databinding.ActivityVerificationCode2Binding

class PasswordPopupActivity : AppCompatActivity() {

    private val VERIFY_REQUEST_CODE = 123 // Angka sembarang untuk requestCode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Gunakan ViewBinding untuk mengganti setContentView
        val binding = ActivityVerificationCode2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Buat variabel lokal untuk tombol "Verify"
        val buttonVerify = binding.buttonVerify

        buttonVerify.setOnClickListener {
            val verifyIntent = Intent(this, VerificationCode2Activity::class.java)
            startActivityForResult(verifyIntent, VERIFY_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == VERIFY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // Jika hasil OK dari halaman sebelumnya, tampilkan pop-up ganti password
            showChangePasswordDialog()
        }
    }

    private fun showChangePasswordDialog() {
        // Inflate layout menggunakan ViewBinding
        val passwordPopupBinding = ActivityPasswordPopupBinding.inflate(layoutInflater)

        // Membuat tampilan dialog
        val dialogBuilder = MaterialAlertDialogBuilder(this)
            .setView(passwordPopupBinding.root)
            .setTitle("Change Password")

        val alertDialog = dialogBuilder.create()

        passwordPopupBinding.buttonSave.setOnClickListener {
            val newPassword = passwordPopupBinding.editNewPassword.text.toString()
            val confirmPassword = passwordPopupBinding.editConfirmPassword.text.toString()
            alertDialog.dismiss()
        }

        // Tampilkan pop-up
        alertDialog.show()
    }
}
