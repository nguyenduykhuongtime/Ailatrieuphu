package com.khuong.ailatrieuphu

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.khuong.ailatrieuphu.dialog.CallFragment
import com.khuong.ailatrieuphu.dialog.HighScoreFragment
import com.khuong.ailatrieuphu.dialog.ReadyFragment
import com.khuong.ailatrieuphu.dialog.TerminalFragment
import com.khuong.ailatrieuphu.fragment.*


class FragmentActivity : AppCompatActivity() {
    var media: MediaPlayer?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        sttBar()
        addStartFragment()

    }
    private fun sttBar(){
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
        val win: Window = activity.window
        val winParams: WindowManager.LayoutParams = win.getAttributes()
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.setAttributes(winParams)
    }

//    private fun addQuestionFragment() {
//        val manager = supportFragmentManager
//        val tran = manager.beginTransaction()
//        val fr = QuestionFragment()
//        tran.add(R.id.content, fr)
//        tran
//            .setCustomAnimations(
//                R.anim.open_open,
//                R.anim.open_close,
//                R.anim.close_open,
//                R.anim.close_close
//            )
//            .replace(R.id.content, fr)
//            .addToBackStack(null)
//            .commit()
//    }
    override fun onBackPressed() {
        Toast.makeText(
            DemoDialogActivity@ this,
            "Không thể quay lại được", Toast.LENGTH_LONG
        ).show()
    }
     fun addStartFragment() {
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = StartFragment()
        tran.add(R.id.content, fr)
        tran
            .setCustomAnimations(
                R.anim.open_open,
                R.anim.open_close,
                R.anim.close_open,
                R.anim.close_close
            )
            .replace(R.id.content, fr)
            .addToBackStack(null)
            .commit()
    }

     fun addLawStartFragment() {
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = LawPlayingFragment()
        tran.add(R.id.content, fr)
         tran
             .setCustomAnimations(
                 R.anim.open_open,
                 R.anim.open_close,
                 R.anim.close_open,
                 R.anim.close_close
             )
             .replace(R.id.content, fr)
             .addToBackStack(null)
             .commit()
    }

    fun loadMusica(context: Context?,i:Int){
        media?.stop()
        media?.release()
        media = MediaPlayer.create(context,i)
        media?.start()
    }

    fun addLawFragment() {
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = LawFragment()

        tran.add(R.id.content, fr)
        tran
            .setCustomAnimations(
                R.anim.open_open,
                R.anim.open_close,
                R.anim.close_open,
                R.anim.close_close
            )
            .replace(R.id.content, fr)
            .addToBackStack(null)
            .commit()
    }
    fun addPlayFragment(level: Int,intRet:Int,int50:Int,intKhan:Int,intCall:Int) {
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = PlayFragment(level,intRet,int50,intKhan,intCall)
        tran.add(R.id.content, fr)
        tran
            .setCustomAnimations(
                R.anim.open_open,
                R.anim.open_close,
                R.anim.close_open,
                R.anim.close_close
            )
            .replace(R.id.content, fr)
            .addToBackStack(null)
            .commit()
    }
    fun addLayoutPlayFragment(level: Int,intRet:Int,int50:Int,intKhan:Int,intCall:Int) {
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = LayoutPlayFragment(level,intRet,int50,intKhan,intCall)

        tran.add(R.id.content, fr)
        tran
            .setCustomAnimations(
                R.anim.open_open,
                R.anim.open_close,
                R.anim.close_open,
                R.anim.close_close
            )
            .replace(R.id.content, fr)
            .addToBackStack(null)
            .commit()
    }
    fun addDialogReady() {
        val dialog = ReadyFragment()
        dialog.show(supportFragmentManager, "dialog")
    }
    fun addDialogTerminal(level: Int) {
        val dialog = TerminalFragment(level)
        dialog.show(supportFragmentManager, "dialog")
    }
    fun addDialogHighScore() {
        val dialog = HighScoreFragment()
        dialog.show(supportFragmentManager, "dialog")
    }
    fun addDialogCall(i:Int) {
        val dialog = CallFragment(i)
        dialog.show(supportFragmentManager, "dialog")
    }

    fun addDialogHelp(i:Int) {
        val dialog = HelpFragment(i)
        dialog.show(supportFragmentManager, "dialog")
    }



    override fun onPause() {
        super.onPause()
        media?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        media?.stop()
    }

    override fun onResume() {
        super.onResume()
        media?.start()
    }

}