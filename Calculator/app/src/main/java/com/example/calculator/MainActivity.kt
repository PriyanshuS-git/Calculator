package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.ArithmeticException

class MainActivity : AppCompatActivity() {
// we created here lastNumeric and lastDot to check if last value of input is numeric then we can apply decimalpoint function
//but if its a dot then we cannot apply decimalpoint function again ,as we know more than one point is not possible.

    private var tvInput: TextView? = null
    var lastNumeric : Boolean = false
    var lastDot : Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)


    }

    ///creating a method name fun on Digit and tvInput.append meaning is that already exit char on textview we are going to add
    // something.We are adding ? because as tvInput is null so if it is not nullable then it will be executed or not be executed
    //here view works as a button and we are accessing text property of button from (view as Button).text

    // if we dont create onDigit method then we have to write 5 line of code for each button and it became lengthy and messy then we have to wrtie below written code.
    // private var btnOne : Button? = null
    // btnOne = findViewById(R.id.btnOne)
    //        btnOne?.setOnClickListener {
    //            tvInput?.append("1")
    //        }

    fun onDigit(view: View){
          tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false

        }
    //here we just create onClear method in which we are assigning a empty string if onClear function call then it will give
    // empty string


    fun onClear(view: View){
        tvInput?.text =""

    }
//if both inside if condition is true then it will execute and we append . and lastNumeric become false & lastDot become true

    fun onDecimalPoint(view: View){
       if(lastNumeric && !lastDot){
           tvInput?.append(".")
           lastNumeric = false
           lastDot = true
       }

    }
//So what I'm going to check is if the string that is given to this is operator added method that we are
//creating here.If it starts with a certain letter, so starts with and you see, there's this method that exists for
//strings, which checks if a certain character is given at the beginning of that string that is passed.
//So value is a string.So if the string would be something like minus five, for example, now, then this statement where
//it starts with a minus would be true.And then whatever code comes after those brackets would be executed.
// below  tvInput?.text?.let {
//            if(lastNumeric && isOperatorAdded(tvInput.text.toString()))
//        }
// if this tvInput?.text? is exist or not
// charSequence is the lambda we are getting from  the expression let which is automatically created
// when we are using let expression and let is used for nullabels

    fun onOperator(view: View){
        tvInput?.text?.let {
            if(lastNumeric && !isOperatorAdded(it.toString())){
                 tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false


            }
        }


    }
// here we using try cathc block to catch the exception like 9/0 or that expression that dont work
// on mathematical level or arithmetic level.
//The printStackTrace() method in Java is a tool used to handle exceptions and errors. It is a method of Java's throwable
// class which prints the throwable along with other details like the line number and class name where the exception occurred.
// printStackTrace() is very useful in diagnosing exceptions.
// splitValue split value like "99-1" to 99  1
// we are using substring so that from old value of tvValue we it starts from index 1 like example "-99" but it will give 99

    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""

            try{
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("-")){

                    val splitValue = tvValue.split("-")

                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())

                }else if(tvValue.contains("+")){

                    val splitValue = tvValue.split("+")

                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())

                }else if(tvValue.contains("/")){

                    val splitValue = tvValue.split("/")

                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())

                }else if(tvValue.contains("*")){

                    val splitValue = tvValue.split("*")

                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }



            }catch (e:ArithmeticException){
                   e.printStackTrace()
            }
        }

    }
// here we are eleminating last two digit like example 99 -1 = 98.0 but we are removing .0 and getting only 98

    private fun removeZeroAfterDot(result : String) : String{
        var value = result
        if(result.contains(".0"))
            value = result.substring(0, result.length-2)

        return value
    }


    private fun isOperatorAdded(value : String) : Boolean{
        return  if(value.startsWith("-")){
            false
        }else
        {
                     value.contains("/")
                    || value.contains("*")
                    ||  value.contains("+")
                             ||value.contains("-")
        }
    }



}