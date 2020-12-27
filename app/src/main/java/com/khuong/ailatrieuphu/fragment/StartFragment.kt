package com.khuong.ailatrieuphu

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.khuong.ailatrieuphu.databinding.BgBeginBinding

class StartFragment: Fragment(), Animation.AnimationListener {

    private lateinit var binding: BgBeginBinding
    private val music = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BgBeginBinding.inflate(inflater,container,false)
        music.add(R.raw.bgmusic)
        music.add(R.raw.bgmusic)
        (activity as com.khuong.ailatrieuphu.FragmentActivity).loadMusica(context,music.random())
        (activity as com.khuong.ailatrieuphu.FragmentActivity).media?.isLooping = true

        val ani =AnimationUtils.loadAnimation(context, R.anim.rotate_waite)
        ani.setAnimationListener(this)
        binding.circle.startAnimation(ani)

        binding.btnPlay.setOnClickListener{
            (activity as com.khuong.ailatrieuphu.FragmentActivity).loadMusica(context,R.raw.touch_sound)
            (activity as com.khuong.ailatrieuphu.FragmentActivity).addLawStartFragment()
        }
        binding.btnHightSore.setOnClickListener{
            (activity as com.khuong.ailatrieuphu.FragmentActivity).addDialogHighScore()
        }
        return binding.root

    }

    override fun onAnimationStart(ani: Animation?) {
        Log.d("MenuActivity", "onAnimationRepeat.......")
    }

    override fun onAnimationEnd(ani: Animation?) {
        Log.d("MenuActivity", "onAnimationEnd.......")
    }

    override fun onAnimationRepeat(ani: Animation?) {
        Log.d("MenuActivity", "onAnimationRepeat.......")
    }


}