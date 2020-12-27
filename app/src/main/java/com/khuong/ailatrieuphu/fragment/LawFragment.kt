package com.khuong.ailatrieuphu.fragment

import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.khuong.ailatrieuphu.R
import com.khuong.ailatrieuphu.databinding.LayoutLawBinding
import java.util.concurrent.Executors

class LawFragment : Fragment(), Animation.AnimationListener {

    private lateinit var binding: LayoutLawBinding
    private val music = mutableListOf<Int>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ani = AnimationUtils.loadAnimation(context, R.anim.rotate_waite)
        ani.setAnimationListener(this)

        binding = LayoutLawBinding.inflate(inflater, container, false)
        binding.circle.startAnimation(ani)
        val asy = MyAsynctask()
        asy.executeOnExecutor(Executors.newFixedThreadPool(1), 6, 0)
        return binding.root
    }
    inner class MyAsynctask : AsyncTask<Int, Int, Void>(){
        override fun doInBackground(vararg values: Int?):Void? {
            for (i in values[0]!! downTo values[1]!!) {

                publishProgress(i, (i + 1))
                SystemClock.sleep(1000)
                Log.d("LawFragment","=------------------- "+i)
            }
            return null
        }

        override fun onProgressUpdate(vararg a: Int?) {

            Log.d("LawFragment","=-------------------  aaaaaaaaaaaaa == "+a[0])
            if(a[0]==6){
                music.add(R.raw.gofind_b)
                music.add(R.raw.gofind)
                (activity as com.khuong.ailatrieuphu.FragmentActivity).loadMusica(context,music.random())
            }
            if(a[0]==0) {
                (activity as com.khuong.ailatrieuphu.FragmentActivity).addPlayFragment(
                    1,
                    0,
                    0,
                    0,
                    0
                )
            }

        }
    }

    override fun onAnimationStart(p0: Animation?) {}
    override fun onAnimationEnd(p0: Animation?) {}
    override fun onAnimationRepeat(p0: Animation?) {}


}