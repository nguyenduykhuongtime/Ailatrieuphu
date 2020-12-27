package com.khuong.ailatrieuphu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khuong.ailatrieuphu.databinding.ItemHighscoreBinding
import com.khuong.ailatrieuphu.model.HighScore

class HighScoreAdapter : RecyclerView.Adapter<HighScoreAdapter.HighScoreHolder> {
        private val inter: IHighScore

        constructor(inter: IHighScore) {
            this.inter = inter
        }
    interface IHighScore {
        fun getCount(): Int
        fun getData(position: Int): HighScore
    }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighScoreHolder {
            return HighScoreHolder(ItemHighscoreBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }

        override fun getItemCount() = inter.getCount()

        override fun onBindViewHolder(holder: HighScoreHolder, position: Int) {
            holder.binding.data = inter.getData(position)
        }



        class HighScoreHolder(val binding: ItemHighscoreBinding) : RecyclerView.ViewHolder(binding.root)
    }