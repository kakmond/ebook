package eiei.ebook.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Mond on 30/3/2561.
 */

class User(val name: String) : Parcelable {

    private var balance: Double = 0.0

    constructor(parcel: Parcel) : this(parcel.readString()) {
        balance = parcel.readDouble()
    }

    override fun toString(): String {
        return "${name})"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeDouble(balance)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}