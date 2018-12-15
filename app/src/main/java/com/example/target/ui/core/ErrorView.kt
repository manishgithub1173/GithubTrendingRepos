package com.example.target.ui.core

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.example.target.R
import kotlinx.android.synthetic.main.view_error.view.*

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), View.OnClickListener {

    interface ErrorListener {
        fun onRetry()
    }

    private lateinit var callback: ErrorListener

    init {
        LayoutInflater.from(context).inflate(R.layout.view_error, this, true)
        retry.setOnClickListener(this)
    }

    fun setErrorMessage(message: String) {
        errorTextView.text = message
    }

    fun setListener(listener: ErrorListener) {
        callback = listener
    }

    override fun onClick(v: View?) {
        callback.onRetry()
    }
}