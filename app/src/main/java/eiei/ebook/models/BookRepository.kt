package eiei.ebook.models

import android.util.Log
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Mond on 30/3/2561.
 */
abstract class BookRepository : Observable() {

    var bookList = ArrayList<Book>() // List of books for display on the screen (result).
    var allbooks = ArrayList<Book>() // List of all books.

    abstract fun loadAllBooks()
    abstract fun getBooks(): ArrayList<Book>

    fun searchYear(years: Int) {
        bookList = ArrayList(allbooks.filter { book -> book.publicationYear == years })
        setChanged()
        notifyObservers()
    }

    fun searchTitle(title: String) {
        bookList = ArrayList(allbooks.filter { book -> book.title.toLowerCase().contains(title.toLowerCase()) })
        setChanged()
        notifyObservers()
    }

    fun search(title: String, years: Int) {
        var book_temp: ArrayList<Book>
        book_temp = ArrayList(allbooks.filter { book -> book.title.toLowerCase().contains(title.toLowerCase()) })
        book_temp = ArrayList(book_temp.filter { book -> book.publicationYear == years })
        this.bookList = book_temp
        setChanged()
        notifyObservers()
    }

    fun sortTitle_des() {
        Collections.sort(bookList) { bookList, bookList2 ->
            bookList2.title.toLowerCase().compareTo(bookList.title.toLowerCase())
        }
        setChanged()
        notifyObservers()
    }

    fun sortTitle_asc() {
        Collections.sort(bookList) { bookList, bookList2 ->
            bookList.title.toLowerCase().compareTo(bookList2.title.toLowerCase())
        }
        setChanged()
        notifyObservers()
    }

    fun sortYear_des() {
        Collections.sort(bookList) { bookList, bookList2 ->
            bookList2.publicationYear - bookList.publicationYear
        }
        setChanged()
        notifyObservers()
    }

    fun sortYear_asc() {
        Collections.sort(bookList) { bookList, bookList2 ->
            bookList.publicationYear - bookList2.publicationYear
        }
        setChanged()
        notifyObservers()
    }
}