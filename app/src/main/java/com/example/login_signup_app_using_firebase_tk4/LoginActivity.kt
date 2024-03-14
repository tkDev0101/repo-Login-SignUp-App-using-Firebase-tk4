package com.example.login_signup_app_using_firebase_tk4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.login_signup_app_using_firebase_tk4.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {


    //Using Binding -> Gradle Script
    private lateinit var binding: ActivityLoginBinding

    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //intialize
        firebaseAuth = FirebaseAuth.getInstance()




        //Event Handler -> Binding when clicking on tv Link
        binding.tvSignUpLink.setOnClickListener{

            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }



        //Event Handler -> Binding when clicking on BTN
        binding.LoginBtn.setOnClickListener{

            val email = binding.edEmail.text.toString()
            val password = binding.edPassword.text.toString()


            //CHECKS
            if(email.isNotEmpty() && password.isNotEmpty()  ){

                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener{

                        if(it.isSuccessful){

                            Toast.makeText(this, "Successful Login ->> MainActivity", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)

                        }else{  //it.is NOT Successful

                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }

                    }//listener

            } else{ //1 of da Fields is EMPTY

                Toast.makeText(this, "Email or password is Empty", Toast.LENGTH_SHORT).show()
            }


        }//end_onClick_Listen




    }
}