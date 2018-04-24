package eiei.ebook.presenter

import eiei.ebook.models.UserRepository
import java.util.*

class UserPresenter(val view: UserView,
                    val repository: UserRepository) : Observer {

    override fun update(p0: Observable?, p1: Any?) {
    }



}
