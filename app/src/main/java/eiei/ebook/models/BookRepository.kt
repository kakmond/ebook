package eiei.ebook.models

import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Mond on 30/3/2561.
 */
abstract class BookRepository : Observable() {
    abstract fun loadAllBooks()
    abstract fun getBooks(): ArrayList<Book>
}