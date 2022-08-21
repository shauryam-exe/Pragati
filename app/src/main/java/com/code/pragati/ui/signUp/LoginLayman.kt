package com.code.pragati.ui.signUp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.code.pragati.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class LoginLayman : AppCompatActivity() {

    private lateinit var number: EditText
    private lateinit var name: EditText
    private lateinit var getOTP: Button
    private lateinit var google: CardView
    private lateinit var linkedIn: CardView
    private lateinit var social: CardView
    private lateinit var back: ImageView

    private lateinit var auth: FirebaseAuth
    private val tag = "check"

    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_leyman)

        number = findViewById(R.id.etNumberLogin)
        name = findViewById(R.id.etNameLoginLeyMan)
        getOTP = findViewById(R.id.btnGetOTP)
        google = findViewById(R.id.cardLoginWithGoogle)
        linkedIn = findViewById(R.id.cardLoginWithLinkedIn)
        social = findViewById(R.id.cardLoginWithSocial)
        back = findViewById(R.id.ivBackLoginLayman)

        back.setOnClickListener {
            onBackPressed()
        }

        auth = FirebaseAuth.getInstance()

        getOTP.setOnClickListener {
            val phoneNumber = number.text.toString().trim()
            val nameText = name.text.toString().trim()
            if (phoneNumber.isNotEmpty() && nameText.isNotEmpty()) {
                if (phoneNumber.length == 10) {

                    //ProgressBar enable. Button Disable

                    val callbacks =
                        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                                // This callback will be invoked in two situations:
                                // 1 - Instant verification. In some cases the phone number can be instantly
                                //     verified without needing to send or enter a verification code.
                                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                                //     detect the incoming verification SMS and perform verification without
                                //     user action.
                                Log.d(tag, "onVerificationCompleted:$credential")

                            }

                            override fun onVerificationFailed(e: FirebaseException) {
                                // This callback is invoked in an invalid request for verification is made,
                                // for instance if the the phone number format is not valid.
                                Log.w(tag, "onVerificationFailed", e)

                                //Update e.message later
                                Toast.makeText(this@LoginLayman, e.message, Toast.LENGTH_SHORT)
                                    .show()
                            }

                            override fun onCodeSent(
                                verificationId: String,
                                token: PhoneAuthProvider.ForceResendingToken
                            ) {
                                // The SMS verification code has been sent to the provided phone number, we
                                // now need to ask the user to enter the code and then construct a credential
                                // by combining the code with a verification ID.
                                Log.d(tag, "onCodeSent:$verificationId")

                                val intent = Intent(this@LoginLayman, OTPVerification::class.java)
                                intent.putExtra("otp", verificationId)
                                intent.putExtra("username", nameText)
                                startActivity(intent)
                            }
                        }

                    val options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber("+91$phoneNumber") // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                        .build()
                    PhoneAuthProvider.verifyPhoneNumber(options)

                } else {
                    Toast.makeText(this, "Please enter correct mobile number", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(this, "Fields can't be empty", Toast.LENGTH_SHORT).show()
            }
        }

        //Google implementation
        FirebaseApp.initializeApp(this)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        google.setOnClickListener {
            loginWithGoogle()
        }
    }

    private fun loginWithGoogle() {
        val signInIntent = mGoogleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            handleResult(task)
        } else {
            Toast.makeText(this, it.resultCode.toString(), Toast.LENGTH_SHORT).show()
        }
    }

//    override fun onStart() {
//        super.onStart()
//        if (GoogleSignIn.getLastSignedInAccount(this) != null) {
//            startActivity(Intent(this, ChatInside::class.java))
//            finish()
//        }
//    }

    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        if (completedTask.isSuccessful) {
            val account: GoogleSignInAccount? = completedTask.result
            if (account != null) {
                updateUI(account)
            }
        } else{
            Toast.makeText(this, completedTask.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Sign in successful with\nUsername : ${account.displayName}\nEmail : ${account.email}", Toast.LENGTH_SHORT).show()
//                TODO("Handle UI")
//                val intent = Intent(this, ChatInside::class.java)
//                startActivity(intent)
//                finish()
            } else {
                Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}