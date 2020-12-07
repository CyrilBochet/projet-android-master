package com.bochet.cyril.projetandroidcloud2020.firebase.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseUser
import com.bochet.cyril.projetandroidcloud2020.R
import com.bochet.cyril.projetandroidcloud2020.firebase.viewmodel.FirebaseAuthViewModel
import kotlinx.android.synthetic.main.activity_firebase_login.*

class FirebaseLoginActivity : AppCompatActivity() {

    private lateinit var mViewModel: FirebaseAuthViewModel

    private var mObserverUser = Observer<FirebaseUser> {
        updateUser(it)
    }

    private var mObserverError = Observer<Int> {
        updateError(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_login)

        mViewModel = ViewModelProvider(this)[FirebaseAuthViewModel::class.java]
        firebaseButtonRegister.setOnClickListener { register() }
        firebaseButtonLogin.setOnClickListener { login() }
        firebaseButtonDisconnect.setOnClickListener { disconnect() }
    }

    override fun onStart() {
        super.onStart()
        mViewModel.mCurrentUser.observe(this, mObserverUser)
        mViewModel.mErrorProcess.observe(this, mObserverError)
    }

    override fun onStop() {
        mViewModel.mCurrentUser.removeObserver(mObserverUser)
        mViewModel.mErrorProcess.removeObserver(mObserverError)
        super.onStop()
    }

    private fun checkConformityFields(): Boolean {
        var isValid = true
        firebaseError.text = ""

        if (TextUtils.isEmpty(firebaseUserEmail.text.toString()) || TextUtils.isEmpty(firebaseUserPassword.text.toString())) {
            isValid = false
            firebaseError.text = "empty field"
        }

        return isValid
    }

    private fun login() {
        if (checkConformityFields()) {
            mViewModel.loginUser(firebaseUserEmail.text.toString(), firebaseUserPassword.text.toString())

        }
    }

    private fun register() {
        if (checkConformityFields()) {
            mViewModel.registerNewUser(firebaseUserEmail.text.toString(), firebaseUserPassword.text.toString())
        }
    }

    private fun disconnect() {
        mViewModel.disconnectUser()
        firebaseButtonLogin.isEnabled=true;
        firebaseButtonRegister.isEnabled=true;
        firebaseButtonDisconnect.isEnabled=false;
    }

    private fun updateUser(user : FirebaseUser) {

        user.let {
            firebaseLog.text = "Welcome back ${user.email} !"
        }
        firebaseButtonLogin.isEnabled=false;
        firebaseButtonRegister.isEnabled=false;
        firebaseButtonDisconnect.isEnabled=true;

    }

    @SuppressLint("SetTextI18n")
    private fun updateError(code : Int) {
        when(code) {
            5 -> {
                firebaseError.text = "Disconnected"
                firebaseLog.text = ""
            }
            9 -> firebaseError.text = "You're not connected."
            10 -> firebaseError.text = "Error when creating"
            11 -> firebaseError.text = "Error when login"
            12 -> firebaseError.text = "The password is too weak."
            13 -> firebaseError.text = "Error when creating"
            14 -> firebaseError.text = "An account with this email already exist."
        }
    }
}