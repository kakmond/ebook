package eiei.ebook.presenter

import eiei.ebook.models.Book

/**
 * Created by Mond on 30/3/2561.
 */
interface BookView {
    fun setBookList(books: ArrayList<Book>)
}