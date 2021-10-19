package vboyko.gb.libs.lesson1

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView

@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView: MvpView {
    fun init()
    fun updateList()
}

@StateStrategyType(AddToEndSingleStrategy::class)
interface CurrentUserView: MvpView

