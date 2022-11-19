package com.tarikkeskin.simplifiedinstagramstoryplayer.common.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter

/**
 * A Binding Adapter that is called whenever the value of the attribute `app:customImageSet`
 * changes. Depending on the value it determines the drawable png of the Imageview.
 */
@BindingAdapter("app:customImageSet")
fun customImageSet(view: ImageView, image: String) {
    view.setImageResource(view.context.resources.getIdentifier(image,
        "drawable",
        view.context.packageName))
}