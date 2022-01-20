package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model

import java.io.Serializable

class RepeatModel(var day: String, selected: Boolean) : Serializable {

    var isSelected = false

    init {
        isSelected = selected
    }
}