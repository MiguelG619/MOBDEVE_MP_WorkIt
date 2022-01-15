package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.FragmentStartExerciseBinding



class StartExerciseFragment : Fragment() {

     lateinit var binding: FragmentStartExerciseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStartExerciseBinding.inflate(inflater, container, false)
        val root = binding.root
        return root
    }

}