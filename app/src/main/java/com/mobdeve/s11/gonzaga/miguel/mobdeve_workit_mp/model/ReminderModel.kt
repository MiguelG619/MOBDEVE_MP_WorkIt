package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model

import java.io.Serializable
import java.util.ArrayList

class ReminderModel : Serializable {
    var title: String? = null
    var time: Long = 0
    var repeat: ArrayList<Int>? = null
    var is_on = false
}