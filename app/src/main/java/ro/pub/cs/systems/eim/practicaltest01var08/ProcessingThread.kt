package ro.pub.cs.systems.eim.practicaltest01var08

import android.content.Context
import android.content.Intent
import android.os.Process
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProcessingThread(
    private val context: Context,
    private val answer: String
) : Thread() {

    override fun run() {
        Log.d(Constants.TAG, "Thread.run() was invoked, PID: ${Process.myPid()} TID: ${Process.myTid()}")
        while (!isInterrupted) {
            try {
                sleep(Constants.INTERVAL)
            } catch (e: InterruptedException) {
                return
            }
            sendMessage(Constants.ACTION)
        }
    }

    private fun sendMessage(action: String) {
        val intent = Intent()

        val length = answer.length
        val index = (0..length).random()

        var message = ""

        var some = answer.toCharArray()
        for (i in 0..length-1) {
            Log.d(Constants.TAG, "sunt in processingthread si message: $message si index: $index si answer: $answer si length: $length")
            if(index == i)
            {
                message = message + some[i]
            } else {
                message = "$message*"
            }
        }

        intent.action = action
        intent.putExtra(Constants.ANSWER, message)

        // Limiteaza livrarea broadcast-ului la aplicatia noastra.
        intent.setPackage(context.packageName)

        Log.d(
            Constants.TAG,
            "Broadcast: $action, " +
                    "Message: $message"

        )
        context.sendBroadcast(intent)
    }
}
