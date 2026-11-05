package ro.pub.cs.systems.eim.practicaltest01var08

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class PracticalTest01Var08Service : Service() {
    private var processingThread: ProcessingThread? = null

    override fun onCreate() {
        super.onCreate()
        Log.d(Constants.TAG, "onCreate() method was invoked")
    }

    // Nu folosim bindService: acest serviciu nu ofera o interfata Binder.
    override fun onBind(intent: Intent): IBinder? {
        Log.d(Constants.TAG, "onBind() method was invoked")
        return null
    }

    override fun onUnbind(intent: Intent): Boolean {
        Log.d(Constants.TAG, "onUnbind() method was invoked")
        return true
    }

    override fun onRebind(intent: Intent) {
        Log.d(Constants.TAG, "onRebind() method was invoked")
    }

    // La oprirea serviciului cerem si terminarea firului care trimite mesajele.
    override fun onDestroy() {
        processingThread?.interrupt()
        processingThread = null

        Log.d(Constants.TAG, "onDestroy() method was invoked")
        super.onDestroy()
    }

    // Apelat la fiecare startService; citim extras si inlocuim firul anterior cu unul nou.
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(Constants.TAG, "onStartCommand() method was invoked")
        if (intent == null) {
            return START_NOT_STICKY
        }

        val answer = intent.getStringExtra(Constants.SERVICERESULT).toString()
        Log.d(Constants.TAG, "sunt in service si am primit answer: $answer")


        processingThread?.interrupt()

        processingThread = ProcessingThread(
            this,
            answer
        )
        processingThread?.start()
        // Daca sistemul omoara serviciul, poate programa repornirea cu intentia retransmisa.
        return START_REDELIVER_INTENT
    }
}