package com.hrand.android.timesupmobile.adapters

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hrand.android.timesupmobile.models.Theme
import android.widget.TextView
import android.widget.CheckBox
import com.hrand.android.timesupmobile.R
import com.hrand.android.timesupmobile.utils.inflate


class ThemeBaseAdapter(private val context: Context, private val themeList: List<Theme>) : BaseAdapter() {

    override fun getView(p0: Int, convertView: View?, viewGroup: ViewGroup): View? {
        val viewHolder: ListViewItemHolder
        val viewToReturn: View?

        if(convertView==null){
            viewToReturn = viewGroup.inflate(R.layout.theme_list_view_with_checkbox_item, false)
            viewHolder = ListViewItemHolder(viewToReturn)
            //if(viewGroup!=null) {
                //viewToReturn = viewGroup.inflate(R.layout.theme_list_view_with_checkbox_item, false)


                val listViewItemCheckbox = viewToReturn.findViewById<CheckBox>(R.id.list_view_item_checkbox)
                viewHolder.setItemCheckbox(listViewItemCheckbox)

                val listItemText = viewToReturn.findViewById<TextView>(R.id.list_view_item_text)
                viewHolder.setItemTextView(listItemText)

                viewToReturn.tag = viewHolder
            //}
        }
        else{
            viewToReturn = convertView
            viewHolder = convertView.tag as ListViewItemHolder
        }

        viewHolder.getItemCheckbox()?.setChecked(false)
        viewHolder.getItemTextView()?.setText(themeList.get(p0).value)

        return viewToReturn

    }

    override fun getItem(p0: Int): Any {
        return themeList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return themeList.size
    }

    class ListViewItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit private var itemCheckbox: CheckBox
        lateinit private var itemTextView: TextView

        fun getItemCheckbox(): CheckBox? {
            return itemCheckbox
        }

        fun setItemCheckbox(itemCheckbox: CheckBox) {
            this.itemCheckbox = itemCheckbox
        }

        fun getItemTextView(): TextView? {
            return itemTextView
        }

        fun setItemTextView(itemTextView: TextView) {
            this.itemTextView = itemTextView
        }
    }

    class ListViewItemDTO(private var checked:Boolean = false, private var itemText:String = "")

}