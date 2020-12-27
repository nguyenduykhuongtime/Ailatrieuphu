package com.khuong.ailatrieuphu.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.khuong.ailatrieuphu.adapter.HighScoreAdapter
import com.khuong.ailatrieuphu.databinding.HighscoreFragmentBinding
import com.khuong.ailatrieuphu.db.DataBaseManager
import com.khuong.ailatrieuphu.model.HighScore

class HighScoreFragment:DialogFragment(),HighScoreAdapter.IHighScore, View.OnClickListener {
    private val highScore = mutableListOf<HighScore>()
    private lateinit var database: DataBaseManager
    private lateinit var binding: HighscoreFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        database = DataBaseManager(activity!!)
        binding = HighscoreFragmentBinding.inflate(
            inflater, container, false
        )
        initData()
        if(highScore.size == 0){
            binding.textNull.setVisibility(View.VISIBLE)
        }
        binding.rc.layoutManager = LinearLayoutManager(activity!!)
        binding.rc.adapter = HighScoreAdapter(this)
        binding.btnPlay.setOnClickListener(this)
        return binding.root
    }
    private fun initData() {
        highScore.addAll(
                database.getScore()
                )
    }

    override fun getCount() = highScore.size
    override fun getData(position: Int): HighScore {
        return highScore[position]
    }

    override fun onClick(view: View?) {
        dismiss()
    }
}