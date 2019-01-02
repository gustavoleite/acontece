package gustavo.acontece.utils

import android.databinding.BindingAdapter
import android.databinding.ObservableField
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
    @BindingAdapter("observableImageUrl")
    fun loadImage(view: ImageView, imageUrl: ObservableField<String>) {
        if (!imageUrl.get().isNullOrBlank()) {
            Picasso
                .get()
                .load(imageUrl.get())
                .into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("circleImageUrl")
    fun loadImageCircle(view: ImageView, imageUrl: String) {
        Picasso
            .get()
            .load(imageUrl)
            .fit()
            .centerCrop()
            .transform(CircleTransformation())
            .into(view)
    }
}