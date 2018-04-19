package eiei.ebook.presenter

import eiei.ebook.models.User

interface UserView {

    fun setUserList(books: ArrayList<User>)
}