package com.khuong.ailatrieuphu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.khuong.ailatrieuphu.R
import com.khuong.ailatrieuphu.databinding.ToaHelpBinding

class HelpFragment(val i:Int) : DialogFragment() {
    private lateinit var binding: ToaHelpBinding
    private var a:Float=0f
    private var b:Float=0f
    private  var c:Float=0f
    private var d:Float=0f
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ToaHelpBinding.inflate(inflater, container, false)
        (activity as com.khuong.ailatrieuphu.FragmentActivity).loadMusica(context,R.raw.hoi_y_kien_chuyen_gia_01b)
        setBarChart()
        return binding.root
    }
        private fun ranHelp(){
            if (i == 1) {
                a = 5f
                b = 2f
                c = 3f
                d = 0f
            }
            if (i == 2) {
                a = 2f
                b = 5f
                c = 0f
                d = 3f
            }
            if (i == 3) {
                a = 2f
                b = 1f
                c = 7f
                d = 0f
            }
            if (i == 4) {
                a = 0f
                b = 1f
                c = 2f
                d = 7f
            }

        }


    private fun setBarChart() {
        val entries = ArrayList<BarEntry>()
       ranHelp()

        entries.add(BarEntry(a, 0))
        entries.add(BarEntry(b, 1))
        entries.add(BarEntry(c, 2))
        entries.add(BarEntry(d, 3))

        val barDataSet = BarDataSet(entries, "Số người dự đoán")

        val labels = ArrayList<String>()
        labels.add("A")
        labels.add("B")
        labels.add("C")
        labels.add("D")
        val data = BarData(labels, barDataSet)
        binding.barChart.data = data
        binding.barChart.setDescription("")
        barDataSet.color = resources.getColor(R.color.bg_hihi)
        binding.barChart.animateY(3000)
    }

}