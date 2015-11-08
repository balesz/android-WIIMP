package net.solutinno.wiimp.widgets

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.util.AttributeSet
import android.widget.FrameLayout
import com.appeaser.sublimepickerlibrary.datepicker.DatePickerFunctions
import com.appeaser.sublimepickerlibrary.datepicker.SublimeDatePicker
import kotlinx.android.synthetic.filter_item.view.button1
import kotlinx.android.synthetic.filter_item.view.text1
import kotlinx.android.synthetic.filter_item.view.text2
import net.solutinno.wiimp.R
import org.joda.time.DateTime
import java.util.*

class FilterItemView : FrameLayout {

    companion object {
        val TYPE_LIST = 0
        val TYPE_DATE_RANGE = 1
        val TYPE_MULTI_SELECT_LIST = 2
    }

    val type: Int

    val entries: Array<CharSequence>?

    val selectedValues: ArrayList<CharSequence> = arrayListOf()

    var title: CharSequence?
        get() = text1?.text
        set(value) { text1?.text = value }

    var subtitle: CharSequence?
        get() = text2?.text
        set(value) { text2?.text = value }

    constructor(ctx: Context, attrs: AttributeSet? = null) : super(ctx, attrs) {
        addView(inflate(context, R.layout.filter_item, null).apply {
            layoutParams = generateDefaultLayoutParams()
        })
        val array = ctx.theme.obtainStyledAttributes(attrs, R.styleable.FilterItemView, 0, 0)
        title = array.getString(R.styleable.FilterItemView_title)
        subtitle = array.getString(R.styleable.FilterItemView_subtitle)
        type = array.getInt(R.styleable.FilterItemView_type, -1)
        entries = array.getTextArray(R.styleable.FilterItemView_entries)
        array.recycle()
        setupView()
    }

    private fun setupView () {
        if (entries != null && type == TYPE_LIST) {
            subtitle = entries.first()
        }
        button1.setOnClickListener {
            AlertDialog.Builder(context).apply {
                when (type) {
                    TYPE_LIST -> listClick()
                    TYPE_DATE_RANGE -> dateRangeClick()
                    TYPE_MULTI_SELECT_LIST -> multiSelectListClick()
                }
            }
        }
    }

    private fun AlertDialog.Builder.dateRangeClick() {
        setView(SublimeDatePicker(context).apply {
            init(2015, 11, 8, DatePickerFunctions.OnDateChangedListener { sdp, y, m, d ->
                subtitle = DateTime(y, m+1, d, 12, 0).toString("yyyy-MM-dd")
            })
        }, 72, 16, 0, 16)
        show()
    }

    private fun AlertDialog.Builder.listClick() {
        if (entries == null) return
        setItems(entries, DialogInterface.OnClickListener { dialogInterface, i ->
            subtitle = entries[i]
        })
        show()
    }

    private fun AlertDialog.Builder.multiSelectListClick() {
        if (entries == null) return
        setMultiChoiceItems(entries, null, DialogInterface.OnMultiChoiceClickListener { di, i, b ->
            if (b) selectedValues.add(entries[i])
            else selectedValues.remove(entries[i])
            subtitle = selectedValues.joinToString()
        })
        show()
    }
}
