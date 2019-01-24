package com.hrand.android.timesupmobile.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.activities.WordsListActivity
import com.hrand.android.timesupmobile.adapters.ThemeBaseAdapter
import com.hrand.android.timesupmobile.daos.ThemeDao
import com.hrand.android.timesupmobile.models.Theme
import kotlinx.android.synthetic.main.layout_add_word_dialog_2.*

class FullScreenDialog : DialogFragment() {

    lateinit var themes: List<Theme>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        var view = inflater.inflate(R.layout.layout_add_word_dialog_2, container, false)

        return view
    }

    override fun onStart() {
        super.onStart()
        val currentAct = this.activity
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window.setLayout(width, height)
        }
        toolbar.setNavigationOnClickListener { v ->
            Toast.makeText(this.context, "Click !!!", Toast.LENGTH_SHORT).show()

            if(currentAct is WordsListActivity){
                currentAct.closeAddDialog()
            }
        }

        if(currentAct!=null && context!=null) {
            val currentContext = currentAct.themedContext
            ThemeDao.init(currentContext)
            themes = ThemeDao.getAll()
            list_view_with_checkbox.adapter = ThemeBaseAdapter(currentContext, themes)
        }

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
                context,
                R.array.difficulty_array,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            difficulty_spinner.adapter = adapter
        }

    }
}