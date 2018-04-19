package eiei.ebook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
    lateinit var real: RealBookRepository
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

        title_search.addTextChangedListener(object : TextWatcher {

            var title: String = ""

            override fun afterTextChanged(p0: Editable?) {
                presenter.search(title, year_search.text.toString())
                sortHandle()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // do nothing.
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                title = title_search.text.toString()
            }
        })

        year_search.addTextChangedListener(object : TextWatcher {

            var years: String = ""

            override fun afterTextChanged(p0: Editable?) {
                presenter.search(title_search.text.toString(), years)
                sortHandle()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // do nothing.
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                years = year_search.text.toString()
            }
        })

        val items_sortby = arrayOf("Unsorted", "Title", "Years")
        val adapter_sortby = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items_sortby)
        sort_spinner.adapter = adapter_sortby

        sort_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0) {
                    presenter.search(title_search.text.toString(), year_search.text.toString())
                    orderby_spinner.isEnabled = false
                } else
                    sortHandle()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //do nothing
            }
        }

        val items_orderby = arrayOf("Ascending", "Descending")
        val adapter_orderby = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items_orderby)
        orderby_spinner.adapter = adapter_orderby

        orderby_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                sortHandle()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //do nothing
            }
        }

    }

    fun sortHandle() {
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
        book_list.adapter = adapter
    }

}
