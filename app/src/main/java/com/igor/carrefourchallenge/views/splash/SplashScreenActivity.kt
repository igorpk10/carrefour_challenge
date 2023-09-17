package com.igor.carrefourchallenge.views.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.igor.carrefourchallenge.databinding.ActivitySplashScreenBinding
import com.igor.carrefourchallenge.views.main.UserActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    companion object {
        private const val ANIMATION_DELAY = 1000L
    }

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            delay(ANIMATION_DELAY)
            navigateToMain()
        }
    }

    private fun navigateToMain(){
        startActivity(Intent(this, UserActivity::class.java))
        finish()
    }

}