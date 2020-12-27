package com.khuong.ailatrieuphu.db


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Environment
import android.util.Log
import com.khuong.ailatrieuphu.model.HighScore
import com.khuong.ailatrieuphu.model.Question
import java.io.File
import java.io.FileOutputStream

class DataBaseManager {
    companion object {
        val DB_NAME = "latrieuphu"
    }

    private val context: Context

    //doi tuong quan ly co so du lieu
    private var database: SQLiteDatabase? = null

    constructor(context: Context) {
        this.context = context
        copyDatabase()
    }

    fun copyDatabase() {
        val input = context.assets.open(DB_NAME)
        val path =
            Environment.getDataDirectory().path + File.separator + "data" + File.separator + context.packageName + File.separator + "db"
        File(path).mkdir()
        val fullPath = path + File.separator + DB_NAME
        if (File(fullPath).exists()) {
            return
        }
        val out = FileOutputStream(fullPath)
        val b = ByteArray(1024)
        var le = input.read(b)
        while (le >= 0) {
            out.write(b, 0, le)
            le = input.read(b)
        }
        input.close()
        out.close()
    }

    private fun openDatabase() {
        if (database == null || !database!!.isOpen()) {
            val path =
                Environment.getDataDirectory().path + File.separator + "data" + File.separator + context.packageName + File.separator + "db"
            val fullPath = path + File.separator + DB_NAME
            database = SQLiteDatabase.openDatabase(
                fullPath, null,
                SQLiteDatabase.OPEN_READWRITE
            )
        }
    }

    private fun closeDatabase() {
        if (database != null && database!!.isOpen()) {
            database?.close()
            database = null
        }
    }

    fun insertHighScore(name: String, passLevel: Int, money: String, time: String) {
        openDatabase()
        //ContentValues de chua cac cap gia tri keu(column name) + value (value of column)
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("level_pass", passLevel)
        contentValues.put("money", money)
        contentValues.put("created_time", time)
        database?.insert("hight_score", null, contentValues)
        closeDatabase()
    }

    fun getScore(): MutableList<HighScore> {
        val highScore = mutableListOf<HighScore>()
        openDatabase()
        val sql = "SELECT * FROM hight_score"
        val cursor = database!!.rawQuery(sql, null)
        cursor.moveToFirst()
        val indexId = cursor.getColumnIndex("id")
        val indexName = cursor.getColumnIndex("name")
        val indexLevelPass = cursor.getColumnIndex("level_pass")
        val indexMoney = cursor.getColumnIndex("money")
        val indexTime = cursor.getColumnIndex("created_time")
        while (!cursor.isAfterLast) {
            val id = cursor.getInt(indexId)
            val name = cursor.getString(indexName)
            val level = cursor.getInt(indexLevelPass)
            val money = cursor.getString(indexMoney)
            val time = cursor.getString(indexTime)
            highScore.add(HighScore(id,name, level, money, time))
            cursor.moveToNext()
        }
        cursor.close()
        closeDatabase()

        return highScore
    }

    fun updateHighScore(id: Int, money: String, name: String) {
        openDatabase()
        //ContentValues de update
        val contentValue = ContentValues()
        contentValue.put("name", name)
        contentValue.put("money", money)
        database?.update(
            "hight_score", contentValue,
            "id = ?", arrayOf(id.toString())
        )
        //when you update database, you must call the method 'setTransactionSuccessfull'
        database?.setTransactionSuccessful()
        closeDatabase()
    }

    fun deleteHighScore(id: Int) {
        openDatabase()
        database?.delete(
            "hight_score", "id = ?",
            arrayOf(id.toString())
        )
        database?.setTransactionSuccessful()
        closeDatabase()
    }


    fun getQuestion(level: Int): Question {
        openDatabase()
        val sql = "select * from question where level = ? order by random() limit 1"
        val cursor = database!!.rawQuery(sql, arrayOf(level.toString()))
        cursor.moveToFirst()
        val indexId = cursor.getColumnIndex("_id")
        val indexQuestion = cursor.getColumnIndex("question")
        val indexLevel = cursor.getColumnIndex("level")
        val indexCauseA = cursor.getColumnIndex("casea")
        val indexCauseB = cursor.getColumnIndex("caseb")
        val indexCauseC = cursor.getColumnIndex("casec")
        val indexCauseD = cursor.getColumnIndex("cased")
        val indexTrueCase = cursor.getColumnIndex("truecase")
        val id = cursor.getInt(indexId)
        val question = cursor.getString(indexQuestion)
        val level = cursor.getInt(indexLevel)
        val casea = cursor.getString(indexCauseA)
        val caseb = cursor.getString(indexCauseB)
        val casec = cursor.getString(indexCauseC)
        val cased = cursor.getString(indexCauseD)
        val truecase = cursor.getInt(indexTrueCase)
        Log.d("DataBaseManager", "getQuestion level: " + level.toString())
        Log.d("DataBaseManager", "getQuestion question: " + question)
        Log.d("DataBaseManager", "getQuestion casea: " + casea)
        Log.d("DataBaseManager", "getQuestion caseb: " + caseb)
        Log.d("DataBaseManager", "getQuestion casec: " + casec)
        Log.d("DataBaseManager", "getQuestion cased: " + cased)
        Log.d("DataBaseManager", "getQuestion truecase: " + truecase.toString())
        Log.d("DataBaseManager", "getQuestion ...........................................")

        val q = Question(id, level, question, casea, caseb, casec, cased, truecase)

        cursor.close()
        closeDatabase()
        return q
    }

    fun getFifteen(): MutableList<Question> {
        val questions = mutableListOf<Question>()
        openDatabase()
        val sql =
            "select * from  (select * from question order by random()) group by level order by level asc"
        //bat dau query
        val cursor = database!!.rawQuery(sql, null)
        cursor.moveToFirst()
        val indexId = cursor.getColumnIndex("_id")
        val indexQuestion = cursor.getColumnIndex("question")
        val indexLevel = cursor.getColumnIndex("level")
        val indexCauseA = cursor.getColumnIndex("casea")
        val indexCauseB = cursor.getColumnIndex("caseb")
        val indexCauseC = cursor.getColumnIndex("casec")
        val indexCauseD = cursor.getColumnIndex("cased")
        val indexTrueCase = cursor.getColumnIndex("truecase")
        while (!cursor.isAfterLast) {
            val id = cursor.getInt(indexId)
            val question = cursor.getString(indexQuestion)
            val level = cursor.getInt(indexLevel)
            val casea = cursor.getString(indexCauseA)
            val caseb = cursor.getString(indexCauseB)
            val casec = cursor.getString(indexCauseC)
            val cased = cursor.getString(indexCauseD)
            val truecase = cursor.getInt(indexTrueCase)
            questions.add(
                Question(
                    id, level, question, casea, caseb,
                    casec, cased, truecase
                )
            )
            cursor.moveToNext()
        }
        cursor.close()
        closeDatabase()

        return questions
    }


}