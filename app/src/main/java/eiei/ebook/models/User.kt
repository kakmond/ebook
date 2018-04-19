package eiei.ebook.models

/**
 * Created by Mond on 30/3/2561.
 */

class User(val name: String) {

    private var balance: Double = 0.0

    override fun toString(): String {
        return "${name})"
    }

//    fun pay(price: Double) {
//        this.balance + -price
//    }

}