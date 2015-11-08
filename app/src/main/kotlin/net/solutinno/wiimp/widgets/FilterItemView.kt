package net.solutinno.wiimp.widgets

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.util.AttributeSet
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.filter_item.view.button
import kotlinx.android.synthetic.filter_item.view.subtitle
import kotlinx.android.synthetic.filter_item.view.title
import net.solutinno.wiimp.R
import net.solutinno.wiimp.widgets.FilterItemView.Type.values
import java.util.*

class FilterItemView : FrameLayout {

    enum class Type (val value: Int) {
        SPINNER(0), DATE_RANGE(1), LIST(2), MULTI_SELECT_LIST(3);
        companion object {
            fun valueOf (value: Int) : Type? = values.firstOrNull { it.value == value }
        }
    }

    val titleText: String?
    val subtitleText: String?
    val type: Type?
    val entries: Array<CharSequence>?
    val selected: ArrayList<String> = arrayListOf()

    constructor(ctx: Context, attrs: AttributeSet? = null) : super(ctx, attrs) {
        val array = ctx.theme.obtainStyledAttributes(attrs, R.styleable.FilterItemView, 0, 0)
        titleText = array.getString(R.styleable.FilterItemView_title)
        subtitleText = array.getString(R.styleable.FilterItemView_subtitle)
        type = Type.valueOf(array.getInt(R.styleable.FilterItemView_type, -1))
        entries = array.getTextArray(R.styleable.FilterItemView_entries)
        array.recycle()
        setupView()
    }

    private fun setupView () {
        addView(inflate(context, R.layout.filter_item, null).apply {
            layoutParams = generateDefaultLayoutParams()
            title.setupTitle()
            subtitle.setupSubtitle()
            button.setupButton()
        })
    }

    private fun TextView.setupTitle() {
        text = titleText
    }

    private fun TextView.setupSubtitle() {
        text = subtitleText ?: entries?.first() ?: "Subtitle"
    }

    private fun Button.setupButton() {
        if (entries != null && arrayOf(Type.LIST, Type.SPINNER).containsRaw(type)) {
            setOnClickListener {
                AlertDialog.Builder(context)
                        .setItems(entries, DialogInterface.OnClickListener { dialogInterface, i ->
                            this@FilterItemView.subtitle.text = entries[i]
                        })
                        .show()
            }
        } else if (entries != null && type == Type.MULTI_SELECT_LIST) {
            setOnClickListener {
                AlertDialog.Builder(context)
                        .setMultiChoiceItems(entries, null, DialogInterface.OnMultiChoiceClickListener { dialogInterface, i, b ->
                            if (b) selected.add(entries[i].toString())
                            else selected.remove(entries[i].toString())
                            this@FilterItemView.subtitle.text = selected.joinToString()
                        })
                        .show()
            }
        } else {
            setOnClickListener {
                Toast.makeText(it.context, "$entries", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
