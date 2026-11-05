package ro.pub.cs.systems.eim.practicaltest01var08

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
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


class PracticalTest01Var08PlayActivity : AppCompatActivity() {
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText

    private lateinit var answer: String
    private lateinit var pBroadcastReceiver: PBroadcastReceiver

    private var started = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var08_play)
        editText1 = findViewById(R.id.e3)
        editText2 = findViewById(R.id.e4)
        answer = ""


        pBroadcastReceiver = PBroadcastReceiver()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val riddle = intent.getStringExtra(Constants.VAL1).toString()
        answer = intent.getStringExtra(Constants.VAL2).toString()
        if(!started){
            startService()
            started = true
        }

        editText1.setText(riddle)


        findViewById<Button>(R.id.b2).setOnClickListener(Listener())

    }
    @SuppressLint("MissingInflatedId", "UnspecifiedRegisterReceiverFlag")
    public override fun onResume() {
        val intentFilter = IntentFilter()

        intentFilter.addAction(Constants.ACTION)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(pBroadcastReceiver,intentFilter, Context.RECEIVER_NOT_EXPORTED)
        } else {
            @Suppress("DEPRECATION")
            registerReceiver(pBroadcastReceiver,intentFilter)
        }

        super.onResume()
        Log.d(Constants.TAG, "onResume() method was invoked")
    }
    public override fun onPause() {
        unregisterReceiver(pBroadcastReceiver)
        super.onPause()
        Log.d(Constants.TAG, "onPause() method was invoked")
    }
    public override fun onDestroy() {

        val intent = Intent(
            this,
            PracticalTest01Var08Service::class.java
        )

        // D.1: cere oprirea serviciului la distrugerea activitatii (inclusiv la recreare).
        stopService(intent)
        started = false

        super.onDestroy()
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
    private fun startService() {

        val intent = Intent(
            this,
            PracticalTest01Var08Service::class.java
        )
        Log.d(Constants.TAG, "sunt in activity si am trimis answer: $answer")
        intent.putExtra(Constants.SERVICERESULT, answer)

        startService(intent)
    }
}