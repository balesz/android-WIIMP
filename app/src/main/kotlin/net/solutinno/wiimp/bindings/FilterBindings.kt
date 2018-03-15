package net.solutinno.wiimp.bindings

import android.view.View
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


class FilterBinder (view: View) {

    class FilterItemBinding<T> : ReadWriteProperty<FilterBinder, T> {

        override fun getValue(thisRef: FilterBinder, property: KProperty<*>): T {
            throw UnsupportedOperationException()
        }

        override fun setValue(thisRef: FilterBinder, property: KProperty<*>, value: T) {
            throw UnsupportedOperationException()
        }
    }
}