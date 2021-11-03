package vboyko.gb.libs.lesson1

interface UserItemView: IItemView {
    fun setLogin(text: String)
    fun setAvatar(url: String)
}