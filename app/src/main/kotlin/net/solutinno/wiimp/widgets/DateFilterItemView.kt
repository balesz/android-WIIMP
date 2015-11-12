package net.solutinno.wiimp.widgets

import android.content.Context
import android.support.v7.app.AlertDialog
import android.util.AttributeSet
import com.appeaser.sublimepickerlibrary.datepicker.DatePickerFunctions
import com.appeaser.sublimepickerlibrary.datepicker.SublimeDatePicker
import net.solutinno.wiimp.R
import org.joda.time.DateTime

class DateFilterItemView : FilterItemView {

    constructor(ctx: Context, attrs: AttributeSet? = null) : super(ctx, attrs)

    override fun setupView() {
        setOnClickListener { dateDialog?.show() }
    }

    private val dateDialog : AlertDialog? get()
    = AlertDialog.Builder(context, R.style.AppTheme_AlertDialog).create().apply {
        val dialog = this
        setView(SublimeDatePicker(context).apply {
            val callback = DatePickerFunctions.OnDateChangedListener { sdp, y, m, d ->
                subtitle = DateTime(y, m + 1, d, 12, 0).toLocalDate().toString()
                dialog.hide()
            }
            val date = DateTime.now()
            init(date.year, date.monthOfYear, date.dayOfMonth, callback)
        })
    }
}
