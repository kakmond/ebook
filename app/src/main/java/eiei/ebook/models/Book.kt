package eiei.ebook.models

/**
 * Created by Mond on 30/3/2561.
 */

class Book(val id: Int,
           val title: String,
           val price: Double = 0.0,
           val publicationYear: Int = 0,
           val imageURL: String = "") {

    override fun toString(): String {
        return "${title} (${price})"
    }

}