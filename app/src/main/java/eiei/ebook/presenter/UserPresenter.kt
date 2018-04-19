package eiei.ebook.presenter

import eiei.ebook.models.UserRepository
import java.util.*

class UserPresenter(val view: BookView,
                    val repository: UserRepository) : Observer {

    override fun update(p0: Observable?, p1: Any?) {
    }



}
