package com.example.datepicker

import android.app.DatePickerDialog
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import com.example.datepicker.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var date:String
    private lateinit var editText:EditText
    private lateinit var insert:Button
    private var Allcheck:Boolean=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //create the array
val helper=MyDBhelper(applicationContext)
        val rs=helper.readableDatabase

        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            editText=findViewById(R.id.editTextTextPersonName)
            editText.setText("")
            setDate()
        }

        insert=findViewById(R.id.button2)
        insert.setOnClickListener {
            editText=findViewById(R.id.editTextTextPersonName)
            if(editText.text.toString().isEmpty())
            {
                println("NO")
                Allcheck=false
            }else
            {
                Allcheck=true
            }
            if(Allcheck)
            {
                var value=ContentValues()
                value.put("DATE",editText.text.toString())
                rs.insert("USERS",null,value)
            }else
            {
                Toast.makeText(applicationContext,"must fill all of the requirement field",Toast.LENGTH_LONG).show()
            }

        }
        //Toast.makeText(applicationContext,editText.text.toString(),Toast.LENGTH_LONG).show()
        setToolbar()

    }

    private fun setToolbar()
    {
        supportActionBar?.title="CJSFlow"
        supportActionBar?.subtitle="How to use date in android"
    }

    private fun setDate()
    {
        val datePicker=Calendar.getInstance() //get time form the calender
        val date= DatePickerDialog.OnDateSetListener{
            view:DatePicker?,year:Int,month:Int,dayOfMonth:Int ->
            datePicker[Calendar.YEAR]=year
            datePicker[Calendar.MONTH]=month
            datePicker[Calendar.DAY_OF_MONTH]=dayOfMonth
            //crete the format for the date
            val dateformat="dd-MMMM-yyyy"
            val simpleDateFormat=SimpleDateFormat(dateformat,Locale.getDefault())
            date=simpleDateFormat.format(datePicker.time)
            //Toast.makeText(this,date,Toast.LENGTH_LONG).show()
            editText.setText(date)
            //binding.textView.text=simpleDateFormat.format(datePicker.time)
        }
       DatePickerDialog(
           this@MainActivity,date,
           datePicker[Calendar.YEAR],
           datePicker[Calendar.MONTH],
           datePicker[Calendar.DAY_OF_MONTH]
       ).show()
    }



}