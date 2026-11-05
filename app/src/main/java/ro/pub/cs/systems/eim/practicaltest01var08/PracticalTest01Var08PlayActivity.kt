package ro.pub.cs.systems.eim.practicaltest01var08

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class PracticalTest01Var08PlayActivity : AppCompatActivity() {
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText

    private lateinit var answer: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var08_play)
        editText1 = findViewById(R.id.e3)
        editText2 = findViewById(R.id.e4)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val riddle = intent.getStringExtra(Constants.VAL1).toString()
        answer = intent.getStringExtra(Constants.VAL2).toString()

        editText1.setText(riddle)


        findViewById<Button>(R.id.b2).setOnClickListener(Listener())

    }

    inner class Listener : View.OnClickListener {

        override fun onClick(view: View) {

            when (view.id) {
                R.id.b2 -> {
                    val reply = Intent().putExtra(Constants.RESULT_VALUE, editText2.text.toString())
                    setResult(RESULT_OK, reply)
                    finish()
                }
                R.id.b3 -> {
                    val reply = Intent().putExtra(Constants.RESULT_VALUE, "No/Fail")
                    setResult(RESULT_CANCELED, reply)
                    finish()
                }

                else -> return
            }


        }
    }
}