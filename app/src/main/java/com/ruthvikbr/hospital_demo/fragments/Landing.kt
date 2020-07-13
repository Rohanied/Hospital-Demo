package com.ruthvikbr.hospital_demo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ruthvikbr.hospital_demo.R


class Landing : Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = Firebase.auth
        if(!(auth.currentUser == null)){
            Navigation.createNavigateOnClickListener(R.id.action_landing_to_home2)
        }
        return inflater.inflate(R.layout.fragment_landing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animFadeIn = AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.image_fade_in)

        val imageView: ImageView = requireActivity().findViewById(R.id.imageView)
        imageView.startAnimation(animFadeIn)

        val button:Button = requireActivity().findViewById(R.id.get_started_button)
        button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_landing_to_signIn))
    }


}