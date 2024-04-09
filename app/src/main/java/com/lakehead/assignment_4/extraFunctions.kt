package com.lakehead.assignment_4

import android.os.SystemClock

fun idCreator(): Int {
    val currentUnixTime = System.currentTimeMillis() / 100

    return currentUnixTime.toInt()
}