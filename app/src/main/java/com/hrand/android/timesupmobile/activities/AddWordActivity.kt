package com.hrand.android.timesupmobile.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.adapters.ThemeBaseAdapter
import com.hrand.android.timesupmobile.daos.ThemeDao
import com.hrand.android.timesupmobile.daos.WordDao
import com.hrand.android.timesupmobile.models.Theme
import com.hrand.android.timesupmobile.models.Word
import kotlinx.android.synthetic.main.activity_add_word.*

class AddWordActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    lateinit var themes: List<Theme>
    lateinit var selectedThemes: MutableList<Theme>
    lateinit var wordToInsert: Word

    val DIFF_SPINNER_ID = 1
    val THEME_CHECKBOX_ID = 2
    var difficultyChoosen = 1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_word)

        toolbar.setNavigationOnClickListener { v ->
            Toast.makeText(this, "Click !!!", Toast.LENGTH_SHORT).show()
            this.finish()
        }

        btn_back.setOnClickListener { this.finish() }

        btn_validate_word.setOnClickListener {
            if(addWord()) {
                // Return to WordsListActivity if adding ok
                this.finish()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        themes = ThemeDao.getAll()
        selectedThemes = mutableListOf()
        list_view_with_checkbox.adapter = ThemeBaseAdapter(this, themes)

        with(list_view_with_checkbox){
            list_view_with_checkbox.id = THEME_CHECKBOX_ID
            onItemClickListener = this@AddWordActivity
        }

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
                this,
                R.array.difficulty_array,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            difficulty_spinner.adapter = adapter

        }

        with(difficulty_spinner){
            difficulty_spinner.id = DIFF_SPINNER_ID
            difficulty_spinner.setSelection(0)
            onItemSelectedListener = this@AddWordActivity
        }

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when(p0?.id){
            DIFF_SPINNER_ID -> {
                Toast.makeText(this, "Difficulty choosen: ${difficulty_spinner.adapter.getItem(p2)}", Toast.LENGTH_SHORT).show()
                difficulty_spinner.setSelection(p2)
                difficultyChoosen = (difficulty_spinner.adapter.getItem(p2) as String).toLong()
            }
            else -> Toast.makeText(this, "Autre chose a été touché", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id){
            THEME_CHECKBOX_ID -> {

                val themeChoosen = (list_view_with_checkbox.adapter.getItem(position) as Theme)

                // Display the selected item text on TextView
                Toast.makeText(this, "Theme clicked: ${themeChoosen.value}", Toast.LENGTH_SHORT).show()

                val currentCheckbox = view?.findViewById<CheckBox>(R.id.list_view_item_checkbox)
                if(currentCheckbox != null) {
                    currentCheckbox.isChecked = !currentCheckbox.isChecked
                    if(currentCheckbox.isChecked){
                        selectedThemes.add(themeChoosen)
                    }
                    else{
                        selectedThemes.remove(themeChoosen)
                    }
                }
            }
            else -> Toast.makeText(this, "Autre chose a été touché", Toast.LENGTH_SHORT).show()
        }
    }

    fun addWord(): Boolean{
        if(!input_word.text.isEmpty() && !input_word.text.equals("")){
            if(selectedThemes.size > 0) {
                wordToInsert = Word(value = input_word.text.toString(), difficulty = difficultyChoosen)
                for (t in selectedThemes) {
                    wordToInsert.themes.add(t)
                }
                WordDao.addWord(wordToInsert)
                Toast.makeText(this, "Mot ${input_word.text} ajouté", Toast.LENGTH_SHORT).show()
                return true
            }
            else{
                Toast.makeText(this, "Vous devez selectionner au moins 1 Thème...", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        else{
            Toast.makeText(this, "Un champ est mal renseigné", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, AddWordActivity::class.java)
            return intent
        }
    }

}