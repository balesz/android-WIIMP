package net.solutinno.wiimp.widgets

import android.content.Context
import android.support.v7.app.AlertDialog
import android.util.AttributeSet
import com.appeaser.sublimepickerlibrary.datepicker.DatePickerFunctions
import com.appeaser.sublimepickerlibrary.datepicker.SublimeDatePicker
import net.solutinno.wiimp.R
import org.joda.time.DateTime

class DateFilterItemView : FilterItemView {

    var onChange: (DateFilterItemView, Long) -> Unit = { s, v -> }

    var value : Long = -1
        get() = field
        set(value) {
            field = value
            subtitle = if (field == -1L) originalSubtitle
            else DateTime(field).toLocalDate().toString()
        }

    constructor(ctx: Context, attrs: AttributeSet? = null) : super(ctx, attrs) {
        init()
    }

    private fun init() {
        setOnClickListener { dateDialog?.show() }
    }

    private val dateDialog : AlertDialog? get()
    = AlertDialog.Builder(context, R.style.AppTheme_AlertDialog).create().apply {
        val dialog = this
        setView(SublimeDatePicker(context).apply {
            val callback = DatePickerFunctions.OnDateChangedListener { sdp, y, m, d ->
                value = DateTime(y, m + 1, d, 12, 0).millis
                onChange(this@DateFilterItemView, value)
                dialog.hide()
            }
            val now = if (value > -1) DateTime(value) else DateTime.now()
            init(now.year, now.monthOfYear - 1, now.dayOfMonth, callback)
            syncState()
        })
    }
}
