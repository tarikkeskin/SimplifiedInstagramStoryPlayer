package com.tarikkeskin.simplifiedinstagramstoryplayer.utils

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity


fun <T : AppCompatActivity> AppCompatActivity.startActivityTop(kClass: Class<T>) {
    val intent = Intent(this, kClass).apply {
        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
    }

    startActivity(intent)
    finishAfterTransition()
}

fun <T : AppCompatActivity> AppCompatActivity.startActivity(kClass: Class<T>) {
    val intent = Intent(this, kClass)
    startActivity(intent)
}

fun AppCompatActivity.startAnimation(view: View, mContext: Context, id: Int) {
    view.startAnimation(AnimationUtils.loadAnimation(mContext, id))
}