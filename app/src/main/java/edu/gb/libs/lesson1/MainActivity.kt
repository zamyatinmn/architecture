package edu.gb.libs.lesson1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.gb.libs.lesson1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var ui: ActivityMainBinding

    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)
        setOnClickBehaviour()
    }

    private fun setOnClickBehaviour() {
        ui.btnCounter1.setOnClickListener {
            presenter.onBtnOneClicked()
        }
        ui.btnCounter2.setOnClickListener {
            presenter.onBtnTwoClicked()
        }
        ui.btnCounter3.setOnClickListener {
            presenter.onBtnThreeClicked()
        }
    }

    override fun setBtnOneText(text: String) {
        ui.btnCounter1.text = text
    }

    override fun setBtnTwoText(text: String) {
        ui.btnCounter2.text = text
    }

    override fun setBtnThreeText(text: String) {
        ui.btnCounter3.text = text
    }
}