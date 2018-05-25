package eiei.ebook.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Mond on 30/3/2561.
 */

class Book(val id: Int,
           val title: String,
           val price: Double = 0.0,
           val publicationYear: Int = 0,
           val imageURL: String = "") : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readInt(),
            parcel.readString()) {
    }

    override fun toString(): String {
        return "${title} (${price})"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeDouble(price)
        parcel.writeInt(publicationYear)
        parcel.writeString(imageURL)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }

}