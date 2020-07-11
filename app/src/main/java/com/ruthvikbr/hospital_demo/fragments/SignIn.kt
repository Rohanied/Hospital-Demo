package com.ruthvikbr.hospital_demo.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ruthvikbr.hospital_demo.R
import com.ruthvikbr.hospital_demo.databinding.FragmentSignInBinding


class SignIn : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        // Initialize Firebase Auth
        auth = Firebase.auth
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            val email: String = binding.emailEditText.text.toString()
            val password: String = binding.passwordEditText.text.toString()
            email.ifEmpty {
                Toast.makeText(requireContext(), "Enter Email", Toast.LENGTH_SHORT).show()
            }
            password.ifEmpty {
                Toast.makeText(
                    requireContext(),
                    "Enter Password",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.signUpTextView.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_signIn_to_signUp))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithEmail:success")
                    val user = auth.currentUser

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                }
            }
    }

}

