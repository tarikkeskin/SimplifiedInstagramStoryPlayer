package com.tarikkeskin.simplifiedinstagramstoryplayer.presentation.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.tarikkeskin.simplifiedinstagramstoryplayer.MainActivity
import com.tarikkeskin.simplifiedinstagramstoryplayer.R
import com.tarikkeskin.simplifiedinstagramstoryplayer.databinding.ActivitySplashBinding
import com.tarikkeskin.simplifiedinstagramstoryplayer.utils.startActivityTop
import com.tarikkeskin.simplifiedinstagramstoryplayer.utils.startAnimation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        startAnimation(binding.logo,this,R.anim.splash_anim)

        lifecycleScope.launchWhenCreated {
            delay(2000)
            withContext(Dispatchers.Main) {
                startActivityTop(MainActivity::class.java)
            }
        }
    }
}