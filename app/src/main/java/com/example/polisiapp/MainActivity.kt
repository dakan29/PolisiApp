package com.example.polisiapp

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat

import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.makeCall
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.jar.Manifest



class MainActivity : AppCompatActivity() {
    //        call feature
    val CALL_REQUEST_CODE = 101
    private val PERMISSION_CODE = 1000
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        img_call.setOnClickListener{
            makeCall("0723308900")
        }

        btn_report.setOnClickListener{
            reportCase()
        }

        reports.setOnClickListener{
            startActivity(Intent(this, ReportActivity::class.java))
        }
    }


    @SuppressLint("NewApi")
    fun reportCase(){
        val title = case_title.text.trim().toString()
        val description = case_description.text.trim().toString()
        val id = System.currentTimeMillis()

        val reportProgress = showProgress("Saving data","Please wait...Data being sent to the nearest police station")

//        validate data
        if (title.isEmpty() && description.isEmpty()){
            showMessage("Missing data","Fill in all fields")
        }else{
            val ref = FirebaseDatabase.getInstance().reference.child("cases/$id")
            val currentDateTime = LocalDateTime.now()
            val report_time = currentDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
            val report_instance = ReportSchema(title,report_time,description)
            reportProgress.show()
            ref.setValue(report_instance).addOnCompleteListener{task ->
                reportProgress.dismiss()
                if(task.isSuccessful){
                    clearText()
                    showMessage("Saving successful", "The nearest police station has received the report")
                }else{
                    showMessage("Saving unsuccessful", "Data not sent, check you internet connection")
                }
            }
        }
    }

    fun showMessage(title:String, message:String){
        val dialogBox = AlertDialog.Builder(this)
        dialogBox.setTitle(title)
        dialogBox.setMessage(message)
        dialogBox.setPositiveButton("Ok", {dialog, which -> dialog.dismiss() })
        dialogBox.create().show()
    }

    fun showProgress(title:String, message:String):ProgressDialog{
        val progress = ProgressDialog(this)
        progress.setTitle(title)
        progress.setMessage(message)
        return progress
    }

    fun clearText(){
        case_title.setText(null)
        case_description.setText(null)
    }



}