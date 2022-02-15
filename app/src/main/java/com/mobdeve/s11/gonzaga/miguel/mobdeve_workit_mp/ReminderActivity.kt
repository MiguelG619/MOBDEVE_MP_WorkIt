package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityReminderBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.utils.SharedPrefUtility
import java.util.*

class ReminderActivity : AppCompatActivity() {

    lateinit var binding: ActivityReminderBinding
    lateinit var sharedPrefUtility: SharedPrefUtility
    lateinit var materialTimePicker : MaterialTimePicker
    lateinit var calendar: Calendar
    lateinit var pendingIntent: PendingIntent
    lateinit var alarmManager: AlarmManager
    var hour = 0
    var minute = 0
    lateinit var HOUR: String
    lateinit var MINUTE: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReminderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hides the action bar, initializes the sharedprefs, and initializes the navbar
        supportActionBar?.hide()
        Navbar(findViewById(R.id.bottom_navigation), this, R.id.nav_reminder)

        initPrefs()

        //Initializes the UI and Notification
        createNotificationChannel()
        setReminderText()

        binding.tvSelectTimeBtn.setOnClickListener {
            showTimePicker()
        }

        binding.tvSetAlarmBtn.setOnClickListener {
            setAlarm()
        }

        binding.tvCancelNotification.setOnClickListener {
            cancelAlarm()
        }


    }

    fun initPrefs() {
        sharedPrefUtility = SharedPrefUtility(this)
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    fun loadData() {
        HOUR = (this.application as GlobalVariables).id + "hourReminder"
        MINUTE = (this.application as GlobalVariables).id + "minuteReminder"
        hour = sharedPrefUtility.getIntegerPreferences(HOUR)
        minute = sharedPrefUtility.getIntegerPreferences(MINUTE)
    }

    fun saveData() {
        sharedPrefUtility.saveIntegerPreferences(HOUR, hour)
        sharedPrefUtility.saveIntegerPreferences(MINUTE, minute)
/*        var g = sharedPrefUtility.getIntegerPreferences(STREAK)
        Log.d("zzzzzzzzz", STREAK +"saveData: streak "+ streak + "dsdsdsds" + g)*/
    }

    // Creates the notifcation channel to be used when the notfication is triggered
    private fun createNotificationChannel() {

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "WorkItReminderChannel"
            val descriptionText = "Channel for Alarm Manager"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("WorkItId", name, importance)
            channel.description = descriptionText

            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(NotificationManager::class.java)

            /*getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager*/
            notificationManager.createNotificationChannel(channel)

        }
    }


    // Sets the time of the notification
    private fun setReminderText() {
        if (hour == 0) {
            binding.tvSelectedTime.text =
                String.format("%02d", 12) + ":" +
                        String.format("%02d", minute) + " AM"
        }
        else if (hour == 12) {
            binding.tvSelectedTime.text =
                String.format("%02d", hour ) + ":" +
                        String.format("%02d", minute) + " PM"

        }  else if (hour > 12) {
            binding.tvSelectedTime.text =
                String.format("%02d", hour - 12) + ":" +
                        String.format("%02d", minute) + " PM"
        }
        else {
            binding.tvSelectedTime.text =
                String.format("%02d", hour) + ":" +
                        String.format("%02d", minute) + " AM"
        }

    }

    private fun showTimePicker() {
        materialTimePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .build()
        materialTimePicker.show(supportFragmentManager, "WorkItId")

        materialTimePicker.addOnPositiveButtonClickListener {
            minute = materialTimePicker.minute
            hour = materialTimePicker.hour

            setReminderText()
            // Initializes the calendar
            calendar = Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY] = hour
            calendar[Calendar.MINUTE] = minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0
        }
    }

    private fun setAlarm() {
        //Saves the alarm in the phone
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )

        saveData()
        Toast.makeText(this, "Notification time set Successfully!", Toast.LENGTH_SHORT).show()

    }

    private fun cancelAlarm() {
        // Cancels the notification alarm
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.cancel(pendingIntent)

        //reminder = null
        hour = 0
        minute = 0
        setReminderText()
        Toast.makeText(this, "Notifcation Cancelled!", Toast.LENGTH_SHORT).show()
    }
}