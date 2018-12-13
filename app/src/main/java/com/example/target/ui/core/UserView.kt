package com.example.target.ui.core

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import com.example.target.R
import kotlinx.android.synthetic.main.view_user.view.*

class UserView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        val attributes = context.obtainStyledAttributes(
            attrs,
            R.styleable.UserView, 0, 0
        )
        val titleText = attributes.getString(R.styleable.UserView_userViewTitle)
        attributes.recycle()

        LayoutInflater.from(context).inflate(R.layout.view_user, this, true)
        titleTextView.text = titleText
    }

    fun setSubtitle(subtitle: String) {
        subtitleTextView.text = subtitle
    }
}