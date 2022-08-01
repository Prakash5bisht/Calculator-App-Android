package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit  var result: EditText
    private lateinit  var newNumber: EditText
    private lateinit  var displayOperation: TextView

    private var operand1: Double? = null
    private var operand2: Double = 0.0
    private var pendingOperation: String = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.result)
        newNumber = findViewById(R.id.newNumber)
        displayOperation = findViewById(R.id.operation)


        val button0 = findViewById<Button>(R.id.button0)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        val buttonDot = findViewById<Button>(R.id.buttonDot)


        val buttonEqual = findViewById<Button>(R.id.buttonEqual)
        val buttonPlus = findViewById<Button>(R.id.buttonPlus)
        val buttonMinus = findViewById<Button>(R.id.buttonMinus)
        val buttonDivide = findViewById<Button>(R.id.buttonDivide)
        val buttonMultiply = findViewById<Button>(R.id.buttonMultiply)


        val listener = View.OnClickListener { view ->
            val button = view as Button
            newNumber.append(button.text)
        }

        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDot.setOnClickListener(listener)
        
        
        val opListener = View.OnClickListener { view ->  
            val op = (view as Button).text.toString()


            try {
                val value = newNumber.text.toString().toDouble()
                performOperation(value, op)
            } catch (e: NumberFormatException) {
                newNumber.setText("")
            }


            pendingOperation = op
            displayOperation.text = pendingOperation
        }

        buttonPlus.setOnClickListener(opListener)
        buttonMinus.setOnClickListener(opListener)
        buttonMultiply.setOnClickListener(opListener)
        buttonDivide.setOnClickListener(opListener)
        buttonEqual.setOnClickListener(opListener)

    }

    private fun performOperation(value : Double, op : String){

        if(operand1 == null){

            operand1 = value

        }else{

            operand2 = value

            if(pendingOperation == "="){
                pendingOperation = op
            }else{

                when (pendingOperation){
                    "="-> operand1 = operand2
                    "/"-> operand1 = if(operand2 == 0.0){
                        Double.NaN
                    }else{
                        operand1!!/operand2
                    }
                    "+"-> operand1 = operand1!! + operand2
                    "-"-> operand1 = operand1!! - operand2
                    "*"-> operand1 = operand1!! * operand2
                }
            }

        }

        result.setText(operand1.toString())
        newNumber.setText("")

    }

}