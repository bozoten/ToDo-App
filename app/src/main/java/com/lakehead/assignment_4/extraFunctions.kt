package com.lakehead.assignment_4

import android.os.SystemClock
/**
 * Filename: extraFunctions.kt
 * Project: Assignment 4
 * Author's name: Shridhara Pavel Rahul Uma
 * Student Id: 1175516
 * Date: 09-04-2024
 * Description: A fully functional Todo app
 */
fun idCreator(): String {
    val currentUnixTime = System.currentTimeMillis() / 100

    return currentUnixTime.toString()
}