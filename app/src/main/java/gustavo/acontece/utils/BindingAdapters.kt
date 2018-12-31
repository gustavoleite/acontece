package gustavo.acontece.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import gustavo.acontece.R


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, imageUrl: String) {
        Picasso
            .get()
            .load(imageUrl)
            .placeholder(R.drawable.image_placeholder)
            .into(view)
    }
}