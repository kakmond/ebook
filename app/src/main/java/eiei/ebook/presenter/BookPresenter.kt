package eiei.ebook.presenter

import android.R
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import eiei.ebook.MainActivity
import eiei.ebook.R.id.orderby_spinner
import eiei.ebook.R.id.sort_spinner
import eiei.ebook.models.BookRepository
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * Created by Mond on 30/3/2561.
 */
class BookPresenter(val view: BookView,
                    val repository: BookRepository) : Observer {


    override fun update(p0: Observable?, p1: Any?) {
        if (p0 == repository)
            view.setBookList(repository.getBooks())
    }

    fun search(title: String, years: String) {
        if (years.isEmpty()){
            repository.searchTitle(title)
        }
        else
            try {
                val temp_year = Integer.parseInt(years)
                if (title.isEmpty())
                    repository.searchYear(temp_year)
                else
                    repository.search(title, temp_year)
            } catch (e: NumberFormatException) {
                //ERROR!
            }
    }

    fun sortTitle_des() {
        repository.sortTitle_des()
    }

    fun sortTitle_asc() {
        repository.sortTitle_asc()
    }

    fun sortYear_des() {
        repository.sortYear_des()
    }

    fun sortYear_asc() {
        repository.sortYear_asc()
    }

    fun start() {
        repository.addObserver(this)
        repository.loadAllBooks()
    }
}