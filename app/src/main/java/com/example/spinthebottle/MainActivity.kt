package com.example.spinthebottle

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity
import com.example.spinthebottle.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object{
        private var lastDirection = 0
        private var spinning = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.ivBottle.setOnClickListener{
            spinTheBottle()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.appbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.selectWaterBottle -> {
                binding.ivBottle.setImageResource(R.drawable.water_bottle)
                true
            }
            R.id.selectGreenBottle -> {
                binding.ivBottle.setImageResource(R.drawable.green_bottle)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun spinTheBottle()
    {
        if(!spinning) {
            val newDirection = Random.nextInt(3600)
            val pivotX = (binding.ivBottle.width / 2)
            val pivotY = (binding.ivBottle.height / 2)

            val rotateAnim =
                RotateAnimation(lastDirection.toFloat(), newDirection.toFloat(), pivotX.toFloat(), pivotY.toFloat())

            rotateAnim.duration = 2500  // in milliseconds
            rotateAnim.fillAfter = true

            rotateAnim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    spinning = false
                }

                override fun onAnimationStart(animation: Animation?) {
                   spinning = true
                }
            })

            lastDirection = newDirection

            binding.ivBottle.startAnimation(rotateAnim)


        }
    }
}
