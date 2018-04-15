package eiei.ebook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import eiei.ebook.models.Book
import eiei.ebook.models.RealBookRepository
import eiei.ebook.presenter.BookPresenter
import eiei.ebook.presenter.BookView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BookView {

    lateinit var presenter: BookPresenter
    lateinit var real: RealBookRepository;
    // lateinit var mock: MockBookRepository;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.initialSpinner()
        // mock = MockBookRepository()
        real = RealBookRepository()
        presenter = BookPresenter(this, real)
        presenter.start()
    }

    fun initialSpinner() {
        val items_searchby = arrayOf("Title", "Years")
        val adapter_seachby = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items_searchby)
        search_spinner.adapter = adapter_seachby

        val items_sortby = arrayOf("Title", "Years")
        val adapter_sortby = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items_sortby)
        sort_spinner.adapter = adapter_sortby

        val items_orderby = arrayOf("Ascending", "Descending")
        val adapter_orderby = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items_orderby)
        orderby_spinner.adapter = adapter_orderby
    }

    fun searchButtonClicked(view: View) {
        /* Search the list */
        if (search_spinner.selectedItem.toString().equals("Title"))
            presenter.searchTitle(search_text.text.toString())
        if (search_spinner.selectedItem.toString().equals("Years"))
            presenter.searchYear(search_text.text.toString())
        /* Sort the list */
        if (sort_spinner.selectedItem.toString().equals("Title")
                && orderby_spinner.selectedItem.toString().equals("Ascending"))
            presenter.sortTitle_asc()
        if (sort_spinner.selectedItem.toString().equals("Title")
                && orderby_spinner.selectedItem.toString().equals("Descending"))
            presenter.sortTitle_des()
        if (sort_spinner.selectedItem.toString().equals("Years")
                && orderby_spinner.selectedItem.toString().equals("Ascending"))
            presenter.sortYear_asc()
        if (sort_spinner.selectedItem.toString().equals("Years")
                && orderby_spinner.selectedItem.toString().equals("Descending"))
            presenter.sortYear_des()
    }

    override fun setBookList(books: ArrayList<Book>) {
        var adapter = ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, books)
        book_list.setAdapter(adapter);
    }

}
