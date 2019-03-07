package com.hrand.android.timesupmobile.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.activities.WordsListActivity
import com.hrand.android.timesupmobile.adapters.ThemeBaseAdapter
import com.hrand.android.timesupmobile.daos.ThemeDao
import com.hrand.android.timesupmobile.models.Theme
import kotlinx.android.synthetic.main.layout_add_word_dialog_2.*
import kotlinx.android.synthetic.main.theme_list_view_with_checkbox_item.*

class FullScreenDialog : DialogFragment(), AdapterView.OnItemSelectedListener {

    lateinit var themes: List<Theme>
    val DIFF_SPINNER_ID = 1

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

            // Set an item click listener for ListView
            list_view_with_checkbox.onItemClickListener = AdapterView.OnItemClickListener{
                parent, view, position, id ->
                val selectedItem = parent.getItemAtPosition(position) as Theme

                // Display the selected item text on TextView
                Toast.makeText(this.context, "Theme clicked: ${selectedItem.value}", Toast.LENGTH_SHORT).show()
                //list_view_item_checkbox.isChecked = list_view_item_checkbox.isChecked==false



            }

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

            //difficulty_spinner.setOnItemClickListener { adapterView, view, i, l -> Toast.makeText(this.context, "Difficulty choosen: ${i}", Toast.LENGTH_SHORT).show() }
        }

        with(difficulty_spinner){
            difficulty_spinner.id = DIFF_SPINNER_ID
            difficulty_spinner.setSelection(0)
            onItemSelectedListener = this@FullScreenDialog
        }

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        when(p0?.id){
            DIFF_SPINNER_ID -> {
                Toast.makeText(this.context, "Difficulty choosen: ${difficulty_spinner.adapter.getItem(p2)}", Toast.LENGTH_SHORT).show()
                difficulty_spinner.setSelection(p2)
            }
            else -> Toast.makeText(this.context, "Autre chose a été touché", Toast.LENGTH_SHORT).show()
        }


    }

}