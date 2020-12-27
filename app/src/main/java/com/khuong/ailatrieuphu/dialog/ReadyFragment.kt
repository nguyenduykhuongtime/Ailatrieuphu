package com.khuong.ailatrieuphu.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.khuong.ailatrieuphu.R
import com.khuong.ailatrieuphu.databinding.DialogReadyBinding

class ReadyFragment : DialogFragment(), View.OnClickListener {

    private lateinit var binding: DialogReadyBinding
    private val music = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogReadyBinding.inflate(inflater, container, false)
        binding.btnReady.setOnClickListener(this)
        binding.btnCancel.setOnClickListener(this)
        addMusic(R.raw.ready,R.raw.ready_b,R.raw.ready_c)
        (activity as com.khuong.ailatrieuphu.FragmentActivity).loadMusica(context,music.random())
        return binding.root
    }

    private fun addMusic(vararg values: Int?) {
        music.clear()
        for (i in 0 until values.size) {
            music.add(values[i]!!)
        }
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btnReady->{
                (activity as com.khuong.ailatrieuphu.FragmentActivity).loadMusica(context,R.raw.touch_sound)
                (activity as com.khuong.ailatrieuphu.FragmentActivity).addLawFragment()
                dismiss()
            }
            R.id.btnCancel->{
                (activity as com.khuong.ailatrieuphu.FragmentActivity).loadMusica(context,R.raw.touch_sound)
                dismiss()
            }

        }
    }
}