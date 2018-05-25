package eiei.ebook

import eiei.ebook.models.Book

interface ClickHandler {
    fun onClick(position: Book)
}