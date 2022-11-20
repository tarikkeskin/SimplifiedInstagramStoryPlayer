package com.tarikkeskin.simplifiedinstagramstoryplayer.common.anim

import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2

class CubicTransition : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        view.apply {
            //Log.e("Debug, position -> ",position.toString())
            //Log.e("Debug, height -> ",height.toString())
            //Log.e("Debug, width -> ",width.toString())
            pivotX = if(position < 0){
                width.toFloat()
            }else{
                0f
            }
             pivotY =  height * 0.5f
             cameraDistance = (width * height)* 0.01f
             rotationY = 90f * position
        }
    }
}