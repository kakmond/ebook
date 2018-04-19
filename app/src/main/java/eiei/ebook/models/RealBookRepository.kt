package eiei.ebook.models

import android.os.AsyncTask
import org.json.JSONArray
import java.io.IOException
import java.net.URL

class RealBookRepository : BookRepository() {

    override fun getBooks(): ArrayList<Book> {
        return bookList
    }

    override fun loadAllBooks() {
        val loaderTask = BookLoaderTask()
        loaderTask.execute()
    }

    fun updateBookJSON(json: String) {
        allbooks.clear()
        val arr = JSONArray(json)
        for (i in 0..(arr.length() - 1)) {
            val obj = arr.getJSONObject(i)
            val id: Int = obj.getInt("id")
            val title: String = obj.getString("title")
            val price: Double = obj.getDouble("price")
            val publicationYear: Int = obj.getInt("pub_year")
            val imageURL: String = obj.getString("img_url")
            allbooks.add(Book(id, title, price, publicationYear, imageURL))
        }
        bookList = allbooks
        setChanged()
        notifyObservers()
    }

    inner class BookLoaderTask : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg p0: String?): String? {
            val url = URL("https://theory.cpe.ku.ac.th/~jittat/courses/sw-spec/ebooks/books.json")
            try {
                val json = url.readText()
                return json
            } catch (ioe: IOException) {
                return ""
            }
        }

        override fun onPostExecute(result: String?) {
            if (result != null)
                updateBookJSON(result)
        }
    }
}