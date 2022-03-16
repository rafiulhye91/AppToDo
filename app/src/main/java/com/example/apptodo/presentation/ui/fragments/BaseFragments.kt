package com.example.apptodo.presentation.ui.fragments

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

open class BaseFragments(private val layout: Int) : Fragment(layout) {
    internal fun finishTheFragment() {
        hideSoftKeyBoard(context, view)
        val manager = requireActivity().supportFragmentManager
        manager.popBackStack()
    }

    private fun hideSoftKeyBoard(context: Context?, view: View?) {
        try {
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (view != null) {
                imm?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}