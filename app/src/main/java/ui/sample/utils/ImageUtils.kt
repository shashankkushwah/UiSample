package ui.sample.utils

import ui.sample.R

/**
 * Created by Shashank on 20/10/2017.
 */
class ImageUtils {
    companion object {
        fun getImageResId(imageId: Int): Int {
            return when (imageId) {
                1 -> R.drawable.image_1
                2 -> R.drawable.image_2
                3 -> R.drawable.image_3
                4 -> R.drawable.image_4
                5 -> R.drawable.image_5
                6 -> R.drawable.image_6
                7 -> R.drawable.image_7
                8 -> R.drawable.image_8
                9 -> R.drawable.image_9
                10 -> R.drawable.image_10
                11 -> R.drawable.image_11
                12 -> R.drawable.image_12
                13 -> R.drawable.image_13
                else -> R.drawable.image_0
            }
        }
    }
}