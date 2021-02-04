package com.marcelo.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.marcelo.motivation.R
import com.marcelo.motivation.infra.MotivationConstants
import com.marcelo.motivation.infra.SecurityPreferences
import com.marcelo.motivation.repository.Mock
import com.marcelo.motivation.utils.ThemeUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences
    private var mPhraseFilter : Int = MotivationConstants.PHRASEFILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSecurityPreferences = SecurityPreferences(this)

        supportActionBar?.hide()

        val name = mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        textName.text = "${getString(R.string.ola)}, ${name}!"

        handleFilter(R.id.imageAll)
        handleNewPhrase()

        buttonNewPhrase.setOnClickListener(this)
        imageAll.setOnClickListener(this)
        imageHappy.setOnClickListener(this)
        imageMorning.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val id = v.id

        val listFilter = listOf(R.id.imageAll, R.id.imageHappy, R.id.imageMorning)

        if(id == R.id.buttonNewPhrase) {
            handleNewPhrase()
        } else if (id in listFilter) {
            handleFilter(id)
        }
    }

    private fun handleFilter(id: Int) {

        imageAll.setColorFilter(ThemeUtils.getThemeColor(this, R.attr.colorOnPrimary))
        imageHappy.setColorFilter(ThemeUtils.getThemeColor(this, R.attr.colorOnPrimary))
        imageMorning.setColorFilter(ThemeUtils.getThemeColor(this, R.attr.colorOnPrimary))

        when (id) {
            R.id.imageAll -> {
                imageAll.setColorFilter(ThemeUtils.getThemeColor(this, R.attr.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.ALL
            }
            R.id.imageHappy -> {
                imageHappy.setColorFilter(ThemeUtils.getThemeColor(this, R.attr.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.HAPPY
            }
            R.id.imageMorning -> {
                imageMorning.setColorFilter(ThemeUtils.getThemeColor(this, R.attr.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.MORNING
            }
        }

    }

    private fun handleNewPhrase() {
        val phrase = Mock().getPhrase(mPhraseFilter)
        textPhrase.text = phrase
    }
}