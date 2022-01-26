package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityMyWorkoutBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityReminderBinding
import java.util.*

class ReminderActivity : AppCompatActivity() {

    lateinit var binding : ActivityReminderBinding
    lateinit var materialTimePicker : MaterialTimePicker
    lateinit var calendar: Calendar
    lateinit var alarmManager: AlarmManager
    lateinit var pendingIntent: PendingIntent
    var hour = 0
    var minute = 0
    //var reminder = (this.application as GlobalVariables).reminder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReminderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Navbar(findViewById(R.id.bottom_navigation), this, R.id.nav_home)

        supportActionBar!!.hide()

        //if (reminder != null) binding.tvSelectedTime.text = reminder.toString()
        hour = (this.application as GlobalVariables).reminderHour
        minute = (this.application as GlobalVariables).reminderMinute

        setReminderText()

               createNotificationChannel()

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

    private fun setReminderText() {
        if (hour == 0 && minute == 0) {
            binding.tvSelectedTime.text =
                String.format("%02d", hour) + " : " +
                        String.format("%02d", minute)
        }
        else if (hour > 12) {
            binding.tvSelectedTime.text =
                String.format("%02d", hour - 12) + " : " +
                        String.format("%02d", minute) + "PM"
        } else {
            binding.tvSelectedTime.text =
                String.format("%02d", hour) + " : " +
                        String.format("%02d", minute) + "AM"
        }

    }

    private fun cancelAlarm() {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.cancel(pendingIntent)

        //reminder = null
        Toast.makeText(this, "Notifcation Cancelled!", Toast.LENGTH_SHORT).show()
    }

    private fun setAlarm() {

        val intent = Intent(this, AlarmReceiver::class.java)

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )

        (this.application as GlobalVariables).reminderHour = hour
        (this.application as GlobalVariables).reminderMinute = minute
        //reminder = calendar
        Toast.makeText(this, "Notification time set Successfully!", Toast.LENGTH_SHORT).show()

    }

    private fun showTimePicker() {
        Toast.makeText(this, "fdss!", Toast.LENGTH_SHORT).show()

        materialTimePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .build()

        materialTimePicker.show(supportFragmentManager, "WorkItId")

        materialTimePicker.addOnPositiveButtonClickListener {
            minute = materialTimePicker.minute
            hour = materialTimePicker.hour

            setReminderText()

            calendar = Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY] = hour
            calendar[Calendar.MINUTE] = minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0

        }


        /*picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Alarm Time")
            .build()

        */
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "WorkItReminderChannel"
            val description = "Channel for Alarm Manager"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("WorkItId", name, importance)
            channel.description = description
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)

        }

    }
}