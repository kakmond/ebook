package eiei.ebook.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 * Created by Mond on 30/3/2561.
 */

class User(val name: String) : Parcelable {

    private var balance: Double = 10.0

    constructor(parcel: Parcel) : this(
            parcel.readString()) {
        balance = parcel.readDouble()
    }

    fun getBalance(): Double {
        return balance
    }

    fun addMoney(money: Double) {
        this.balance += money
    }

    fun checkout(books: ArrayList<Book>) {
        for (book in books)
            this.balance -= book.price
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