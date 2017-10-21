package ui.sample.data.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Shashank on 20/10/2017.
 */
class Comment(val author: User, val date: String, val text: String) : Parcelable {
    constructor(source: Parcel) : this(
            source.readParcelable<User>(User::class.java.classLoader),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeParcelable(author, 0)
        writeString(date)
        writeString(text)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Comment> = object : Parcelable.Creator<Comment> {
            override fun createFromParcel(source: Parcel): Comment = Comment(source)
            override fun newArray(size: Int): Array<Comment?> = arrayOfNulls(size)
        }
    }
}