package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.FragmentRestBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.FragmentStartExerciseBinding
import java.util.*
import java.util.concurrent.TimeUnit


class RestFragment : Fragment() {

     lateinit var binding: FragmentRestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRestBinding.inflate(inflater, container, false)
        val root = binding.root


        // Initialize timer duration
        // Get timer duration from workout exercise
        // 20seconds
        // Convert minute seconds to seconds
        var duration: Long = TimeUnit.SECONDS.toMillis(5)

        // Initialize Timer Countdown
        object : CountDownTimer(duration, 1000) {
            override fun onTick(l: Long) {
                // When tick
                // Convert Milisecond to minute and second
                var sDuration: String = String.format(
                    Locale.ENGLISH, "%02d"
                    , l / 1000)

                // Set converted string on text view
                binding.tvRestTime.text = sDuration
            }

            override fun onFinish() {
                // When finish
                getFragmentManager()?.popBackStack()
            }

        }.start()


        return root
    }

}