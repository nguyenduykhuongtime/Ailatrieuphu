package com.khuong.ailatrieuphu.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.khuong.ailatrieuphu.R
import com.khuong.ailatrieuphu.databinding.DialogCallBinding


class CallFragment(val i: Int) : DialogFragment(), View.OnClickListener {
    private lateinit var binding: DialogCallBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogCallBinding.inflate(inflater, container, false)

        binding.bigronaldo.setOnClickListener(this)
        binding.convinh.setOnClickListener(this)
        binding.leymar.setOnClickListener(this)
        binding.messi.setOnClickListener(this)
        binding.ronaldo.setOnClickListener(this)
        binding.suarez.setOnClickListener(this)
        binding.btnOk.setOnClickListener(this)
        return binding.root
    }

    private fun trueCase(i: Int): String {
        var s: String = "A"
        if (i == 2) {
            s = "B"
        }
        if (i == 3) {
            s = "C"
        }
        if (i == 4) {
            s = "D"
        }
        return s
    }
    private fun disableButton(){
        binding.bigronaldo.isEnabled = false
        binding.convinh.isEnabled = false
        binding.leymar.isEnabled = false
        binding.messi.isEnabled = false
        binding.ronaldo.isEnabled = false
        binding.suarez.isEnabled = false
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.leymar -> {
                disableButton()
                binding.textCall.setText("Neymar tư vấn phương án "+trueCase(i))
                binding.textCall.setVisibility(View.VISIBLE)
            }
            R.id.convinh -> {
                disableButton()
                binding.textCall.setText("Công Vinh tư vấn phương án "+trueCase(i))
                binding.textCall.setVisibility(View.VISIBLE)
            }
            R.id.suarez -> {
                disableButton()
                binding.textCall.setText("Suarez tư vấn phương án "+trueCase(i))
                binding.textCall.setVisibility(View.VISIBLE)
            }
            R.id.bigronaldo -> {
                disableButton()
                binding.textCall.setText("Big Ronaldo tư vấn phương án "+trueCase(i))
                binding.textCall.setVisibility(View.VISIBLE)
            }
            R.id.messi -> {
                disableButton()
                binding.textCall.setText("Messi tư vấn phương án "+trueCase(i))
                binding.textCall.setVisibility(View.VISIBLE)
            }
            R.id.ronaldo -> {
                disableButton()
                binding.textCall.setText("Ronaldo tư vấn phương án "+trueCase(i))
                binding.textCall.setVisibility(View.VISIBLE)
            }
            R.id.btnOk -> {
                dismiss()

            }
        }
    }


}