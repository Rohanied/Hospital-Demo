package com.ruthvikbr.hospital_demo.fragments

import android.os.Bundle
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
import com.ruthvikbr.hospital_demo.databinding.FragmentSignUpBinding


class SignUp : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        // Initialize Firebase Auth
        auth = Firebase.auth
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.LoginTextView.setOnClickListener {
            it.setOnClickListener(Navigation
                .createNavigateOnClickListener(R.id.action_signIn_to_signUp))
        }

        binding.signUpButton.setOnClickListener {
            val email: String = binding.emailSignUpEditText.text.toString()
            val password: String = binding.passwordSignUpEditText.text.toString()
            val confirmPassword: String = binding.confirmPasswordEditText.text.toString()
            if (confirmPassword == password) {
                if (password.length >= 6) {
                    signUp(email, password)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Password Length has to be 6",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } else {
                Toast.makeText(requireContext(), "Passwords don't match", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

    private fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(requireActivity()){task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        updateUI(user)
                    }
                } else {
                    updateUI(null)
                    Toast.makeText(requireContext(), "Sign Up Failed", Toast.LENGTH_SHORT)
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