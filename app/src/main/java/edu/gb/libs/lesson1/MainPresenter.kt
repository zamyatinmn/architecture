package edu.gb.libs.lesson1

class MainPresenter(private val view: MainView) {

    private val model = CountersModel()

    fun onBtnOneClicked() {
        val nextValue = model.next(Counter.FIRST.ordinal)
        view.setBtnOneText(nextValue.toString())
    }

    fun onBtnTwoClicked() {
        val nextValue = model.next(Counter.SECOND.ordinal)
        view.setBtnTwoText(nextValue.toString())
    }

    fun onBtnThreeClicked() {
        val nextValue = model.next(Counter.THIRD.ordinal)
        view.setBtnThreeText(nextValue.toString())
    }
}