package ui.sample.data.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Shashank on 20/10/2017.
 */
class Mock(val id: Int, val title: String, val description: String, val imageId: Int, val view: Int, val likes: Int,
           val author: User, val comments: List<Comment>) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readParcelable<User>(User::class.java.classLoader),
            source.createTypedArrayList(Comment.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(title)
        writeString(description)
        writeInt(imageId)
        writeInt(view)
        writeInt(likes)
        writeParcelable(author, 0)
        writeTypedList(comments)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Mock> = object : Parcelable.Creator<Mock> {
            override fun createFromParcel(source: Parcel): Mock = Mock(source)
            override fun newArray(size: Int): Array<Mock?> = arrayOfNulls(size)
        }
    }
}