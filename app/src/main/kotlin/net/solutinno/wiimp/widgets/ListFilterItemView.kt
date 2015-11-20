package net.solutinno.wiimp.widgets

import android.content.Context
import android.support.v7.app.AlertDialog
import android.util.AttributeSet
import net.solutinno.wiimp.R

class ListFilterItemView : FilterItemView<IntArray> {

    private val entries: Array<CharSequence>

    private val isMultiSelect: Boolean

    override var value: IntArray = intArrayOf()
        get() = field
        set(value) {
            field = value
            subtitle = if (value.isEmpty()) entries.firstOrNull().toString()
            else value.map { entries[it] }.joinToString()
            onChanged(this, value)
        }

    constructor(ctx: Context, attrs: AttributeSet? = null) : super(ctx, attrs) {
        val array = ctx.theme.obtainStyledAttributes(attrs, R.styleable.ListFilterItemView, 0, 0)
        entries = array.getTextArray(R.styleable.ListFilterItemView_entries)?:arrayOf()
        isMultiSelect = array.getBoolean(R.styleable.ListFilterItemView_multi_select, false)
        array.recycle()
        init()
    }

    private fun init() {
        isSaveEnabled = true
        subtitle = if (subtitle.isNullOrBlank()) entries.firstOrNull().toString() else subtitle
        setOnClickListener { if (isMultiSelect) multiDialog?.show() else listDialog?.show() }
    }

    private val listDialog : AlertDialog.Builder? get() = AlertDialog.Builder(context).apply {
        if (entries.isEmpty()) return null
        setItems(entries, { dialogInterface, i ->
            value = intArrayOf(i)
        })
    }

    private val multiDialog: AlertDialog.Builder? get() = AlertDialog.Builder(context).apply {
        if (entries.isEmpty()) return null
        val selected = (0..entries.size-1).map { value.contains(it) }.toBooleanArray()
        setMultiChoiceItems(entries, selected, { di, i, b ->
            value = if (b) value.union(intArrayOf(i).asIterable()).toIntArray()
            else value.subtract(intArrayOf(i).asIterable()).toIntArray()
        })
    }
}
