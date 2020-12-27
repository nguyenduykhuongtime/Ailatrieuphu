package com.khuong.ailatrieuphu.fragment

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.khuong.ailatrieuphu.R
import com.khuong.ailatrieuphu.databinding.ActivityLawplayingBinding

class PlayFragment(
    var num: Int,
    val intRet: Int,
    val int50: Int,
    val intKhan: Int,
    val intCall: Int
) : Fragment() {

    private lateinit var binding: ActivityLawplayingBinding
    private val music = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityLawplayingBinding.inflate(inflater, container, false)
        binding.btnContinue.setVisibility(View.GONE)
        musicLevel(num)
        level(num).setBackgroundResource(R.color.purple_700)
        level(num).setTextColor(Color.parseColor("#000000"))
        if (num > 1) {
            level(num - 1).setBackgroundResource(R.color.trongsuot)
        }
        if (num == 6){
            Handler().postDelayed({
            (activity as com.khuong.ailatrieuphu.FragmentActivity).addLayoutPlayFragment(
                num,
                intRet,
                int50,
                intKhan,
                intCall
            )
        }, 10000)
        }else if(num == 11){
            Handler().postDelayed({
            (activity as com.khuong.ailatrieuphu.FragmentActivity).addLayoutPlayFragment(
                num,
                intRet,
                int50,
                intKhan,
                intCall
            )
        }, 7000)
        }
        else{
            Handler().postDelayed({
            (activity as com.khuong.ailatrieuphu.FragmentActivity).addLayoutPlayFragment(
                num,
                intRet,
                int50,
                intKhan,
                intCall
            )
        }, 2000)
        }
        return binding.root
    }

    private fun addMusic(vararg values: Int?) {
        music.clear()
        for (i in 0 until values.size) {
            music.add(values[i]!!)
        }
    }

    private fun callMusicFrag() {
        (activity as com.khuong.ailatrieuphu.FragmentActivity).media =
            MediaPlayer.create(context, music.random())
        (activity as com.khuong.ailatrieuphu.FragmentActivity).media?.start()
    }

    private fun musicLevel(num: Int) {
        (activity as com.khuong.ailatrieuphu.FragmentActivity).media?.release()
        if (num == 1) {
            addMusic(R.raw.ques1, R.raw.ques1_b)
            callMusicFrag()
        }
        if (num == 2) {
            addMusic(R.raw.ques2, R.raw.ques2_b)
            callMusicFrag()
        }
        if (num == 3) {
            addMusic(R.raw.ques3, R.raw.ques3_b)
            callMusicFrag()
        }
        if (num == 4) {
            addMusic(R.raw.ques4, R.raw.ques4_b)
            callMusicFrag()
        }
        if (num == 5) {
            addMusic(R.raw.ques5, R.raw.ques5_b)
            callMusicFrag()
        }
        if (num == 6) {
            addMusic(R.raw.chuc_mung_vuot_moc_01_0, R.raw.chuc_mung_vuot_moc_01_1)
            callMusicFrag()
            Handler().postDelayed({
                addMusic(R.raw.ques6)
                callMusicFrag()
            }, 8000)

        }
        if (num == 7) {
            addMusic(R.raw.ques7, R.raw.ques7_b)
            callMusicFrag()
        }
        if (num == 8) {
            addMusic(R.raw.ques8, R.raw.ques8_b)
            callMusicFrag()
        }
        if (num == 9) {
            addMusic(R.raw.ques9, R.raw.ques9_b)
            callMusicFrag()
        }
        if (num == 10) {
            addMusic(R.raw.ques10)
            callMusicFrag()
        }
        if (num == 11) {
            addMusic(R.raw.chuc_mung_vuot_moc_02_0)
            callMusicFrag()
            Handler().postDelayed({
                addMusic(R.raw.ques11)
                callMusicFrag()
            }, 5000)
        }
        if (num == 12) {
            addMusic(R.raw.ques12)
            callMusicFrag()
        }
        if (num == 13) {
            addMusic(R.raw.ques13)
            callMusicFrag()
        }
        if (num == 14) {
            addMusic(R.raw.ques14)
            callMusicFrag()
        }
        if (num == 15) {
            addMusic(R.raw.ques15)
            callMusicFrag()
        }
    }

    private fun level(num: Int): TextView {
        var s = binding.textLevel1

        if (num == 2) {
            s = binding.textLevel2
        }
        if (num == 3) {
            s = binding.textLevel3
        }
        if (num == 4) {
            s = binding.textLevel4
        }
        if (num == 5) {
            s = binding.textLevel5
        }
        if (num == 6) {
            s = binding.textLevel6
        }
        if (num == 7) {
            s = binding.textLevel7
        }
        if (num == 8) {
            s = binding.textLevel8
        }
        if (num == 9) {
            s = binding.textLevel9
        }
        if (num == 10) {
            s = binding.textLevel10
        }
        if (num == 11) {
            s = binding.textLevel11
        }
        if (num == 12) {
            s = binding.textLevel12
        }
        if (num == 13) {
            s = binding.textLevel13
        }
        if (num == 14) {
            s = binding.textLevel14
        }
        if (num == 15) {
            s = binding.textLevel15
        }
        return s

    }

}