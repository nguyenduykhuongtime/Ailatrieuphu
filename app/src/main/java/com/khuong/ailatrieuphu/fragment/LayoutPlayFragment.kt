package com.khuong.ailatrieuphu.fragment

import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.khuong.ailatrieuphu.R
import com.khuong.ailatrieuphu.databinding.LayoutPlayBinding
import com.khuong.ailatrieuphu.db.DataBaseManager
import com.khuong.ailatrieuphu.model.Question
import java.util.concurrent.Executors


class LayoutPlayFragment(
    var level: Int,
    var intRet: Int,
    var int50: Int,
    var intKhan: Int,
    var intCall: Int
) : Fragment() {

    private lateinit var binding: LayoutPlayBinding
    private lateinit var database: DataBaseManager
    private lateinit var q: Question
    private lateinit var v: TextView
    private val music = mutableListOf<Int>()
    private val tv = mutableListOf<TextView>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        musicPlay(R.raw.background_music)
        (activity as com.khuong.ailatrieuphu.FragmentActivity).media?.isLooping = true
        database = DataBaseManager(activity!!)
        binding = LayoutPlayBinding.inflate(inflater, container, false)
        q = database.getQuestion(level)
        binding.data = q
        binding.textMoney.setText(money(level))
        val asy = MyAsynctask()
        asy.executeOnExecutor(Executors.newFixedThreadPool(1), 30, 0)
        binding.textTitleQuestion.setText("Câu " + q.level)
        binding.textAnsA.setOnClickListener {
            disableButton()
            addMusic(R.raw.ans_a, R.raw.ans_a2)
            musicPlay(music.random())
            asy.cancel(true)
            v = binding.textAnsA
            v.setBackgroundResource(R.drawable.bg_choose1)
            loadAns(1)
        }
        binding.textAnsB.setOnClickListener {
            disableButton()
            asy.cancel(true)
            addMusic(R.raw.ans_b, R.raw.ans_b2)
            musicPlay(music.random())
            v = binding.textAnsB
            v.setBackgroundResource(R.drawable.bg_choose1)
            loadAns(2)
        }
        binding.textAnsC.setOnClickListener {
            disableButton()
            asy.cancel(true)
            addMusic(R.raw.ans_c, R.raw.ans_c2)
            musicPlay(music.random())
            v = binding.textAnsC
            v.setBackgroundResource(R.drawable.bg_choose1)
            loadAns(3)
        }
        binding.textAnsD.setOnClickListener {
            disableButton()
            asy.cancel(true)
            addMusic(R.raw.ans_d, R.raw.ans_d2)
            musicPlay(music.random())
            v = binding.textAnsD
            v.setBackgroundResource(R.drawable.bg_choose1)
            loadAns(4)
        }
        binding.btnTerminate.setOnClickListener {
            disableButton()
            asy.cancel(true)
            binding.btnTerminate.setImageResource(R.drawable.bg_faile_terminate)
            (activity as com.khuong.ailatrieuphu.FragmentActivity).addDialogTerminal(level)
        }
        binding.btnCall.setOnClickListener {
            addMusic(R.raw.call, R.raw.help_call)
            musicPlay(music.random())
            (activity as com.khuong.ailatrieuphu.FragmentActivity).addDialogCall(q.truecase)
            binding.btnCall.setImageResource(R.drawable.bg_faile_call)
            intCall = 1
        }
        binding.btnHelp.setOnClickListener {

            binding.btnHelp.setImageResource(R.drawable.bg_faile_help)
            intKhan = 1
            (activity as com.khuong.ailatrieuphu.FragmentActivity).loadMusica(
                context,
                R.raw.khan_gia
            )
            Handler().postDelayed({
                (activity as com.khuong.ailatrieuphu.FragmentActivity).addDialogHelp(q.truecase)
            }, 5000)

        }
        binding.btnPercent50.setOnClickListener {
            addMusic(R.raw.sound5050, R.raw.sound5050_2)
            musicPlay(music.random())
            binding.btnPercent50.setImageResource(R.drawable.bg_faile_percent50)
            binding.btnPercent50.isEnabled = false
            Handler().postDelayed({
                rercent50()
            }, 3000)
            int50 = 1
        }
        binding.btnRestart.setOnClickListener {
            (activity as com.khuong.ailatrieuphu.FragmentActivity).loadMusica(
                context,
                R.raw.touch_sound
            )
            q = database.getQuestion(level)
            binding.data = q
            binding.btnRestart.setImageResource(R.drawable.bg_faile_restart)
            intRet = 1
        }
        return binding.root
    }

    private fun setBackgroundHelp() {
        if (intRet == 1) {
            binding.btnRestart.setImageResource(R.drawable.bg_faile_restart)
            binding.btnRestart.isEnabled = false
        }
        if (int50 == 1) {
            binding.btnPercent50.setImageResource(R.drawable.bg_faile_percent50)
            binding.btnPercent50.isEnabled = false
        }
        if (intKhan == 1) {
            binding.btnHelp.setImageResource(R.drawable.bg_faile_help)
            binding.btnHelp.isEnabled = false
        }
        if (intCall == 1) {
            binding.btnCall.setImageResource(R.drawable.bg_faile_call)
            binding.btnCall.isEnabled = false
        }
    }

    private fun rercent50() {
        tv.clear()
        tv.add(binding.textAnsA)
        tv.add(binding.textAnsB)
        tv.add(binding.textAnsC)
        tv.add(binding.textAnsD)
        val inttt = mutableListOf<Int>()
        inttt.add(0)
        inttt.add(1)
        if (q.truecase == 1) {
            tv.removeAt(0)
            for (i in 0..1) {
                val rando = inttt.random()
                val text: TextView = tv.get(rando)
                tv.removeAt(rando)
                text.setText("")
                text.isEnabled = false
            }
        }
        if (q.truecase == 2) {
            tv.removeAt(1)
            for (i in 0..1) {
                val rando = inttt.random()
                val text: TextView = tv.get(rando)
                tv.removeAt(rando)
                text.setText("")
                text.isEnabled = false
            }
        }
        if (q.truecase == 3) {

            tv.removeAt(2)
            for (i in 0..1) {
                val rando = inttt.random()
                val text: TextView = tv.get(rando)
                tv.removeAt(rando)
                text.setText("")
                text.isEnabled = false
            }
        }
        if (q.truecase == 4) {
            tv.removeAt(3)
            for (i in 0..1) {
                val rando = inttt.random()
                val text: TextView = tv.get(rando)
                tv.removeAt(rando)
                text.setText("")
                text.isEnabled = false
            }
        }
    }

    private fun addMusic(vararg values: Int?) {
        music.clear()
        for (i in 0 until values.size) {
            music.add(values[i]!!)
        }
    }

    private fun musicPlay(i: Int) {
        (activity as com.khuong.ailatrieuphu.FragmentActivity).loadMusica(context, i)
    }

    inner class MyAsynctask : AsyncTask<Int, String, Void>() {
        override fun doInBackground(vararg values: Int?): Void? {

            for (i in values[0]!! downTo values[1]!!) {
                publishProgress(i.toString(), (i + 1).toString())
                SystemClock.sleep(1000)
                Log.d(
                    "LayoutPlayFragment",
                    "---------------------------:   " + i + "   ---  " + level
                )
            }
            return null
        }

        override fun onProgressUpdate(vararg a: String?) {
            binding.textTime.setText(a[0])
            setBackgroundHelp()
            if (a[0]!!.toInt() == 0) {
                (activity as com.khuong.ailatrieuphu.FragmentActivity).addDialogTerminal(level)
            }
        }
    }

    private fun setBackgroundTrue(tv: TextView) {
        tv.setBackgroundResource(R.drawable.bg_true)
        val frameAnimation: AnimationDrawable = tv.background as AnimationDrawable
        frameAnimation.start()
    }

    private fun disableButton() {
        binding.textAnsA.isEnabled = false
        binding.textAnsB.isEnabled = false
        binding.textAnsC.isEnabled = false
        binding.textAnsD.isEnabled = false
        binding.btnTerminate.isEnabled = false
        binding.btnCall.isEnabled = false
        binding.btnHelp.isEnabled = false
        binding.btnPercent50.isEnabled = false
        binding.btnRestart.isEnabled = false
    }

    private fun money(level: Int): String {
        var s: String = ""
        if (level == 1) {
            s = "200,000"
        }
        if (level == 2) {
            s = "400,000"
        }
        if (level == 3) {
            s = "600,000"
        }
        if (level == 4) {
            s = "1,000,000"
        }
        if (level == 5) {
            s = "2,000,000"
        }
        if (level == 6) {
            s = "3,000,000"
        }
        if (level == 7) {
            s = "6,000,000"
        }
        if (level == 8) {
            s = "10,000,000"
        }
        if (level == 9) {
            s = "14,000,000"
        }
        if (level == 10) {
            s = "22,000,000"
        }
        if (level == 11) {
            s = "30,000,000"
        }
        if (level == 12) {
            s = "40,000,000"
        }
        if (level == 13) {
            s = "60,000,000"
        }
        if (level == 14) {
            s = "85,000,000"
        }
        if (level == 15) {
            s = "150,000,000"
        }
        return s
    }

    private fun loadAns(caseTrue: Int) {
        Handler().postDelayed({
            addMusic(R.raw.ans_now1, R.raw.ans_now2, R.raw.ans_now3)
            musicPlay(music.random())
        }, 4000)
        Handler().postDelayed({
            voidAns(caseTrue)
            if (q.truecase != caseTrue) {
                v.setBackgroundResource(R.drawable.bg_faile)
                val frameAnimation: AnimationDrawable = v.background as AnimationDrawable
                frameAnimation.start()
            }
            if (q.truecase == 1) {
                setBackgroundTrue(binding.textAnsA)
            }
            if (q.truecase == 2) {
                setBackgroundTrue(binding.textAnsB)
            }
            if (q.truecase == 3) {
                setBackgroundTrue(binding.textAnsC)
            }
            if (q.truecase == 4) {
                setBackgroundTrue(binding.textAnsD)
            }
        }, 7000)
        Handler().postDelayed({
            if (q.truecase == caseTrue) {
                if (level == 15) {
                    (activity as com.khuong.ailatrieuphu.FragmentActivity).media?.stop()
                    (activity as com.khuong.ailatrieuphu.FragmentActivity).media?.release()
                    (activity as com.khuong.ailatrieuphu.FragmentActivity).media =
                        MediaPlayer.create(
                            context,
                            R.raw.best_player
                        )
                    (activity as com.khuong.ailatrieuphu.FragmentActivity).media?.start()
                    (activity as com.khuong.ailatrieuphu.FragmentActivity).addDialogTerminal(
                        level
                    )
                } else {
                    (activity as com.khuong.ailatrieuphu.FragmentActivity).addPlayFragment(
                        level + 1, intRet, int50, intKhan, intCall
                    )
                }
            } else {
                (activity as com.khuong.ailatrieuphu.FragmentActivity).media?.stop()
                (activity as com.khuong.ailatrieuphu.FragmentActivity).media?.release()
                (activity as com.khuong.ailatrieuphu.FragmentActivity).media =
                    MediaPlayer.create(
                        context,
                        R.raw.out_of_time
                    )
                (activity as com.khuong.ailatrieuphu.FragmentActivity).media?.start()
                (activity as com.khuong.ailatrieuphu.FragmentActivity).addDialogTerminal(
                    level
                )
            }
        }, 11000)
    }

    private fun voidAns(i: Int) {
        if (q.truecase == 1) {
            if (q.truecase == i) {
                addMusic(R.raw.true_a, R.raw.true_a2)
                musicPlay(music.random())
            } else {
                addMusic(R.raw.lose_a, R.raw.lose_a2)
                musicPlay(music.random())
            }
        }
        if (q.truecase == 2) {
            if (q.truecase == i) {
                addMusic(R.raw.true_b, R.raw.true_b2, R.raw.true_b3)
                musicPlay(music.random())
            } else {
                addMusic(R.raw.lose_b, R.raw.lose_b2)
                musicPlay(music.random())
            }
        }
        if (q.truecase == 3) {
            if (q.truecase == i) {
                addMusic(R.raw.true_c, R.raw.true_c2, R.raw.true_c3)
                musicPlay(music.random())
            } else {
                addMusic(R.raw.lose_c, R.raw.lose_c2)
                musicPlay(music.random())
            }
        }
        if (q.truecase == 4) {
            if (q.truecase == i) {
                addMusic(R.raw.true_d2, R.raw.true_d3)
                musicPlay(music.random())
            } else {
                addMusic(R.raw.lose_d, R.raw.lose_d2)
                musicPlay(music.random())
            }
        }
    }
}
