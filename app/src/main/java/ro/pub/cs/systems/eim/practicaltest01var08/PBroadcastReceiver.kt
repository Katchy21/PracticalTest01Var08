package ro.pub.cs.systems.eim.practicaltest01var08

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

// D.2: receiver inregistrat dinamic de MainActivity; afiseaza datele primite in Logcat.
class PBroadcastReceiver() : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val action = intent.action
        val result = intent.getStringExtra(Constants.ANSWER)


        // Pe ecran afisam valoarea actiunii; in Logcat jurnalizam toate datele primite.
        val message = when (action) {
            Constants.ACTION-> "am primit rezultat: $result"
            else -> return
        }


        Log.d(
            Constants.TAG,
            "Broadcast received: $action, si mesajul: $message"
        )
        Toast.makeText(context, "$message"     , Toast.LENGTH_LONG).show()


    }

}
