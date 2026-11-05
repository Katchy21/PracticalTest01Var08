package ro.pub.cs.systems.eim.practicaltest01var08

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01Var08MainActivity : AppCompatActivity() {
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var08_main)
        editText1 = findViewById(R.id.e1)
        editText2 = findViewById(R.id.e2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<Button>(R.id.b1).setOnClickListener(Listener())

    }

    inner class Listener : View.OnClickListener {

        override fun onClick(view: View) {

            when (view.id) {
                R.id.b1 -> {
                    openSecondaryActivity()
                    return
                }

                else -> return
            }


        }
    }

    private fun openSecondaryActivity() {

        val riddle = editText1.text.toString()
        val answer = editText2.text.toString()

        val intent = Intent(
            this,
            PracticalTest01Var08PlayActivity::class.java
        )

        intent.putExtra(Constants.VAL1, riddle)
        intent.putExtra(Constants.VAL2, answer)

        startActivityForResult(
            intent,
            Constants.SECONDARY_REQUEST_CODE
        )
    }
}