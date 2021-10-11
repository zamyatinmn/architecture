package vboyko.gb.libs.lesson1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import vboyko.gb.libs.lesson1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    private val counters = mutableListOf(0, 0, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        mainBinding.btnCounter1.setOnClickListener {
            mainBinding.btnCounter1.text = (++counters[0]).toString()
        }
        mainBinding.btnCounter2.setOnClickListener {
            mainBinding.btnCounter2.text = (++counters[1]).toString()
        }
        mainBinding.btnCounter3.setOnClickListener {
            mainBinding.btnCounter3.text = (++counters[2]).toString()
        }
        initViews()
    }

    private fun initViews() {
        mainBinding.btnCounter1.text = counters[0].toString()
        mainBinding.btnCounter2.text = counters[1].toString()
        mainBinding.btnCounter3.text = counters[2].toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray("counters", counters.toIntArray())
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putIntArray("counters", counters.toIntArray())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val countersArray = savedInstanceState.getIntArray("counters")
        countersArray?.toList()?.let {
            counters.clear()
            counters.addAll(it)
        }
        initViews()
    }

}