package com.hrand.android.timesupmobile.activities

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Html
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hrand.android.timesupmobile.R
import kotlinx.android.synthetic.main.activity_rules.*
import org.apache.commons.io.IOUtils
import java.io.IOException


class RulesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rules)

        btn_back.setOnClickListener {
            this.finish()
        }


        try {
            val res = resources.openRawResource(R.raw.rules1)
            val s = IOUtils.toString(res, "UTF-8")
            res.close()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tv_rules.text = Html.fromHtml(s, Html.FROM_HTML_MODE_COMPACT)
            } else{
                tv_rules.text = Html.fromHtml(s)
            }
        } catch (io: IOException){
            Log.e("TimsupMobile", "Pb fermeture Stream !")
        }

    }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, RulesActivity::class.java)
            return intent
        }
    }

}