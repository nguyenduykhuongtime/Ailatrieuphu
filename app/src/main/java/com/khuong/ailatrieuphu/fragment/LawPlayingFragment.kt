package com.khuong.ailatrieuphu.fragment

import android.media.MediaPlayer
import android.os.AsyncTask
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.khuong.ailatrieuphu.R
import com.khuong.ailatrieuphu.databinding.ActivityLawplayingBinding
import org.jsoup.Jsoup
import java.util.concurrent.Executors

class LawPlayingFragment:Fragment() {
    private lateinit var binding: ActivityLawplayingBinding
    private val music = mutableListOf<Int>()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityLawplayingBinding.inflate(inflater,container,false)
        val asy = MyAsynctask()
        asy.executeOnExecutor(Executors.newFixedThreadPool(1), 8, 0)
        music.add(R.raw.luatchoi_b)
        music.add(R.raw.luatchoi_c)
        (activity as com.khuong.ailatrieuphu.FragmentActivity).media?.release()
        (activity as com.khuong.ailatrieuphu.FragmentActivity).media = MediaPlayer.create(context,music.random())
        (activity as com.khuong.ailatrieuphu.FragmentActivity).media?.start()
        binding.btnContinue.setOnClickListener{
            (activity as com.khuong.ailatrieuphu.FragmentActivity).media?.stop()
            (activity as com.khuong.ailatrieuphu.FragmentActivity).media?.release()
            (activity as com.khuong.ailatrieuphu.FragmentActivity).media = MediaPlayer.create(context,R.raw.touch_sound)
            (activity as com.khuong.ailatrieuphu.FragmentActivity).media?.start()
            asy.cancel(true)
            (activity as com.khuong.ailatrieuphu.FragmentActivity).addDialogReady()
        }

        return binding.root
    }

    inner class MyAsynctask : AsyncTask<Int, String, Void>(){
            override fun doInBackground(vararg values: Int?):Void? {
                for (i in values[0]!! downTo values[1]!!) {
                    publishProgress(i.toString(), (i + 1).toString())
                    SystemClock.sleep(1000)
                }
                return null
            }

            override fun onProgressUpdate(vararg a: String?) {

                if (a[0]!!.toInt() == 5){
                    binding.textLevel5.setBackgroundResource(R.color.purple_700)
                }
                if (a[0]!!.toInt() == 3){
                    binding.textLevel10.setBackgroundResource(R.color.purple_700)
                    binding.textLevel5.setBackgroundResource(R.color.trongsuot)
                }
                if (a[0]!!.toInt() == 1){
                    binding.textLevel15.setBackgroundResource(R.color.purple_700)

                    binding.textLevel10.setBackgroundResource(R.color.trongsuot)
                }
                if (a[0]!!.toInt() == 0){
                    (activity as com.khuong.ailatrieuphu.FragmentActivity).addDialogReady()

                }
            }
        }


}