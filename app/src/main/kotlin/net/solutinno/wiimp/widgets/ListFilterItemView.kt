package net.solutinno.wiimp.widgets

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.util.AttributeSet
import net.solutinno.wiimp.R
import java.util.*

class ListFilterItemView : FilterItemView {

    private val entries: Array<CharSequence>

    private val isMultiSelect: Boolean

    private val selectedValues: ArrayList<Int> = arrayListOf()

    var onChange: (ListFilterItemView, IntArray) -> Unit = { s, v -> }

    constructor(ctx: Context, attrs: AttributeSet? = null) : super(ctx, attrs) {
        val array = ctx.theme.obtainStyledAttributes(attrs, R.styleable.ListFilterItemView, 0, 0)
        entries = array.getTextArray(R.styleable.ListFilterItemView_entries)?:arrayOf()
        isMultiSelect = array.getBoolean(R.styleable.ListFilterItemView_multi_select, false)
        array.recycle()
        init()
    }

    private fun init() {
        isSaveEnabled = true
        setOnClickListener { if (isMultiSelect) multiDialog?.show() else listDialog?.show() }
    }

    private val listDialog : AlertDialog.Builder? get() = AlertDialog.Builder(context).apply {
        if (entries.isEmpty()) return null
        setItems(entries, DialogInterface.OnClickListener { dialogInterface, i ->
            selectedValues.clear()
            subtitle = entries[i]
            selectedValues.add(i)
            onChange(this@ListFilterItemView, selectedValues.toIntArray())
        })
    }

    private val multiDialog: AlertDialog.Builder? get() = AlertDialog.Builder(context).apply {
        if (entries.isEmpty()) return null
        setMultiChoiceItems(entries, null, DialogInterface.OnMultiChoiceClickListener { di, i, b ->
            if (b) selectedValues.add(i)
            else selectedValues.remove(i)
            subtitle = selectedValues.map { entries[it] }.joinToString()
            onChange(this@ListFilterItemView, selectedValues.toIntArray())
        })
    }
}