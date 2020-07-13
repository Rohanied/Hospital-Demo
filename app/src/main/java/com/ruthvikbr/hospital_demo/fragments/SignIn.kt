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
import com.google.firebase.auth.FirebaseUser
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
                Toast.makeText(requireContext(), "Enter Password", Toast.LENGTH_SHORT).show()
            }
            if (password.length < 6) {
                Toast.makeText(requireContext(), "Password length has to be 6", Toast.LENGTH_SHORT)
                    .show()
            } else if ((password.isNotEmpty() && (email.isNotEmpty()) && (password.length >= 6))) {
                login(email, password)
            }

        }

        binding.cancelButton.setOnClickListener {
            binding.emailEditText.setText("")
            binding.passwordEditText.setText("")
        }

        binding.signUpTextView.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_signIn_to_signUp))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        updateUI(user)
                    }

                } else {
                    Log.v("Login","${task.exception}")
                    updateUI(null)
                    Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            Navigation.createNavigateOnClickListener(R.id.action_signIn_to_home2)
        }
    }

}

