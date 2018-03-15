package net.solutinno.wiimp.bindings

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


class TextViewBinding (private val view: TextView, onChange: ((CharSequence?) -> Unit)? = null)
: ReadWriteProperty<Any, CharSequence?> {

    val value : CharSequence? get() = view.text

    private val watcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (value != s)
                onChange?.invoke(value)
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
        override fun afterTextChanged(s: Editable?) { }
    }

    init { view.addTextChangedListener(watcher) }

    override fun getValue(thisRef: Any, property: KProperty<*>): CharSequence? {
        return view.text
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: CharSequence?) {
        view.text = value
    }
}

class VisibleBinding (val view: View, val onChange: ((Int) -> Unit)? = null)
: ReadWriteProperty<Any, Int> {

    override fun getValue(thisRef: Any, property: KProperty<*>): Int {
        return view.visibility
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
        view.visibility = value
        onChange?.invoke(view.visibility)
    }
}

class OnClickBinding (val view: View, val onClick: () -> Unit)
: ReadWriteProperty<Any, () -> Unit> {

    init { view.setOnClickListener { onClick() } }

    override fun getValue(thisRef: Any, property: KProperty<*>): () -> Unit = onClick

    override fun setValue(thisRef: Any, property: KProperty<*>, value: () -> Unit) {
        view.setOnClickListener { value?.invoke() }
    }
}

open class Binder {

    fun textBinding (view: TextView, onChange: ((CharSequence?) -> Unit)? = null)
            = TextViewBinding(view, onChange)

    fun visibleBinding (view: View, onChange: ((Int) -> Unit)? = null)
            = VisibleBinding(view, onChange)

    fun clickBinding (view: View, onClick: () -> Unit)
            = OnClickBinding(view, onClick)
}


private class TestBinder (val view: View) : Binder() {

    var textField: CharSequence? by textBinding(view as TextView) {

    }

    var textFieldVisible : Int by visibleBinding(view) {

    }

    val action : () -> Unit by clickBinding(view) {

    }
}
