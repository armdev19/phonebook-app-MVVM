package com.infernal93.phonebookappmvvm.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by Armen Mkhitaryan on 03.01.2020.
 */

fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.longToast(message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}