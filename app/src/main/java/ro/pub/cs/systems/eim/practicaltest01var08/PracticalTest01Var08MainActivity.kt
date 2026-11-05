package ro.pub.cs.systems.eim.practicaltest01var08

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01Var08MainActivity : AppCompatActivity() {
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var result: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var08_main)
        editText1 = findViewById(R.id.e1)
        editText2 = findViewById(R.id.e2)
        result = "something"

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == Constants.SECONDARY_REQUEST_CODE) {
            result = intent?.getStringExtra(Constants.RESULT_VALUE).toString()
            when (resultCode) {
                RESULT_OK -> {
                    Toast.makeText(
                        this,
                        "Ai ghicit bine",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                RESULT_CANCELED -> {
                    Toast.makeText(
                        this,
                        "No/Fail",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)

        savedInstanceState.putString(Constants.result,result)
    }

    // Restaurare dupa onStart, daca exista stare salvata; aici dubleaza restaurarea din onCreate.
    public override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.containsKey(Constants.result)) {
            result = (savedInstanceState.getString(Constants.result)).toString()
            Toast.makeText(this@PracticalTest01Var08MainActivity, "$result", Toast.LENGTH_SHORT).show()
            Log.d(Constants.TAG, "Rezultatul ultimului meci a fost $result")

        }

    }
}