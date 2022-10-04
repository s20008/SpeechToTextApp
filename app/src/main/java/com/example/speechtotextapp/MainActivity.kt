package com.example.speechtotextapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Toast
import com.example.speechtotextapp.databinding.ActivityMainBinding
import java.lang.Exception
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val REQUEST_CODE_SPEECH_INPUT = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //button click to show SpeechToText dialog
        binding.voiceBtn.setOnClickListener{
           speak();
        }

    }

    private fun speak() {
        //intent to show SpeechToText dialog
        val mIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        mIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi speak something")

        try {
            //if there is no error show SpeechToText dialog
            startActivityForResult(mIntent, REQUEST_CODE_SPEECH_INPUT)
        }
        catch (e: Exception){
            //if there is any error get error message and show toast
            Toast.makeText(this, e.message,Toast.LENGTH_SHORT).show()
        }

        fun onActivityResult(requestCode: Int, resultCode: Int, data:Intent?){
            super.onActivityResult(requestCode, resultCode, data)
            when(requestCode){
                REQUEST_CODE_SPEECH_INPUT -> {
                    if(resultCode == Activity.RESULT_OK && null != data){

                        //get text from result
                        val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                        //set the text to textview
                        binding.textTv.text = result!![0]
                    }
                }
            }
        }


    }
}