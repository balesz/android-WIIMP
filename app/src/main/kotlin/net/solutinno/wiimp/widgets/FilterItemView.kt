package net.solutinno.wiimp.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.filter_item.view.*
import net.solutinno.wiimp.R

abstract class FilterItemView<T> : FrameLayout {

    var title: CharSequence?
        get() = text1?.text
        set(value) { text1?.text = value }

    var subtitle: CharSequence?
        get() = text2?.text
        set(value) { text2?.text = value }

    protected val originalSubtitle: CharSequence?

    abstract var value: T

    var onChanged : (View, T) -> Unit = {vw, v -> }

    constructor(ctx: Context, attrs: AttributeSet? = null) : super(ctx, attrs) {
        addView(inflate(context, R.layout.filter_item, null).apply {
            layoutParams = generateDefaultLayoutParams()
        })
        val array = ctx.theme.obtainStyledAttributes(attrs, R.styleable.FilterItemView, 0, 0)
        title = array.getString(R.styleable.FilterItemView_title)
        originalSubtitle= array.getString(R.styleable.FilterItemView_subtitle)
        subtitle = originalSubtitle
        array.recycle()
    }
}
