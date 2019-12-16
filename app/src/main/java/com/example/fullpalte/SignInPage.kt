package com.example.fullpalte

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class SignInPage : AppCompatActivity() {
    lateinit var mGoogleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_page)

        var imageView = findViewById<View>(R.id.image) as ImageView
        var name = findViewById<View>(R.id.name) as TextView
        var email = findViewById<View>(R.id.email) as TextView
        var id = findViewById<View>(R.id.id1) as TextView
        var Signout = findViewById<View>(R.id.sign_out_button) as Button

        Signout.setOnClickListener { view ->
            when (view.id) {
                // ...
                R.id.sign_out_button -> signOut()
            }// ...
        }


        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            val personName = acct.displayName
            //            String personGivenName = acct.getGivenName();
            //            String personFamilyName = acct.getFamilyName();
            val personEmail = acct.email
            val personId = acct.id
            val personPhoto = acct.photoUrl

            name.text = personName
            email.text = personEmail
            id.text = personId
            Glide.with(this).load(personPhoto.toString()).into(imageView)

        }
    }

    private fun signOut() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this) {
                Toast.makeText(this@SignInPage, "Signed Out Successfully!.", Toast.LENGTH_LONG)
                    .show()
                finish()
                // ...
            }
    }
}
