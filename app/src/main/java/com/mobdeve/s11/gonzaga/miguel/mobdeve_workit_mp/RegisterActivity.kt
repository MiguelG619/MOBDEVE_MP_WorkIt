package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityLogInBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mcvContinue.setOnClickListener{
            if (isUserInputValid() === false) {

            }
        }
    }

    fun isUserInputValid(): Boolean {
        var valid: Boolean = true
        if (binding.edtFname.text.toString().trim().isEmpty()) {
            binding.edtFname.error = "Please enter your first name."
            valid = false
        }

        if (binding.edtLname.text.toString().trim().isEmpty()) {
            binding.edtLname.error = "Please enter your last name."
            valid = false
        }

        if (binding.edtDob.text.toString().trim().isEmpty()) {
            binding.edtDob.error = "Please enter your date of birth."
            valid = false
        }

        if (binding.edtSex.text.toString().trim().isEmpty()) {
            binding.edtSex.error = "Please enter your sex."
            valid = false
        }

        // Check if edit text is empty and valid --> valid not yet coded
        if (binding.edtEmail.text.toString().trim().isEmpty()) {
            binding.edtEmail.error = "Please enter your email."
            valid = false
        }

        if (binding.edtPwd.text.toString().trim().isEmpty()) {
            binding.edtPwd.error = "Please enter your password."
            valid = false
        }

        if (binding.edtCpwd.text.toString().trim().isEmpty() || binding.edtCpwd.text.equals(binding.edtPwd.text)) {
            binding.edtCpwd.error = "Please enter your password."
            valid = false
        }

        if (valid === false && !binding.cbTerms.isChecked)
            valid = false


        return valid
    }
}