package gustavo.acontece.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, imageUrl: String) {
        Picasso
            .get()
            .load(imageUrl)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("circleImageUrl")
    fun loadImageCircle(view: ImageView, imageUrl: String) {
        Picasso
            .get()
            .load(imageUrl)
            .fit()
            .centerCrop()
            .transform(CircleTransform())
            .into(view)
    }
}