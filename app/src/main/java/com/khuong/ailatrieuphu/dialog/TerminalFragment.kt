package com.khuong.ailatrieuphu.dialog

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.khuong.ailatrieuphu.R
import com.khuong.ailatrieuphu.databinding.DialogSaveUserBinding
import com.khuong.ailatrieuphu.db.DataBaseManager
import com.khuong.ailatrieuphu.model.HighScore
import java.util.*
import kotlin.contracts.Returns

class TerminalFragment(val level: Int) : DialogFragment(), View.OnClickListener {

    private lateinit var binding: DialogSaveUserBinding
    private lateinit var database: DataBaseManager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DialogSaveUserBinding.inflate(inflater, container, false)
        binding.btnSave.setOnClickListener(this)
        binding.btnCancel.setOnClickListener(this)
        binding.textMoney.setText(money(level))
        database = DataBaseManager(activity!!)

        return binding.root
    }

    private fun namePlayer():String{
        var s:String = binding.editUsername.text.toString()
        if(binding.editUsername.text.toString().equals("")){
            s = "Vô Danh"
        }
        return s

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnSave -> {
                database.insertHighScore(namePlayer(),level,money(level),getTime())
                (activity as com.khuong.ailatrieuphu.FragmentActivity).addStartFragment()
                (activity as com.khuong.ailatrieuphu.FragmentActivity).media?.stop()
                dismiss()
            }
            R.id.btnCancel -> {
                (activity as com.khuong.ailatrieuphu.FragmentActivity).addStartFragment()
                (activity as com.khuong.ailatrieuphu.FragmentActivity).media?.stop()
                dismiss()
            }
        }
    }

    private fun getTime(): String {
        val c: Calendar = Calendar.getInstance()
        val time: String =
            (c.get(Calendar.DATE)).toString() + "/" +
                    (c.get(Calendar.MONTH)).toString() + "/" +
                    (c.get(Calendar.YEAR)).toString() + "-" +
                    (c.get(Calendar.HOUR)).toString() +":" +
                    (c.get(Calendar.MINUTE)).toString() +":" +
                    (c.get(Calendar.SECOND)).toString()
        return time
    }

    private fun money(i: Int): String {
        var s = ""
        if (i <= 5) {
            s = "0 VND"
        }
        if (i < 10 && i > 5) {
            s = "2,000,000 VND"
        }
        if (i < 15 && i > 10) {
            s = "22,000,000 VND"
        }
        if (i == 15) {
            s = "150,000,000 VND"
        }
        return s
    }

}