package net.solutinno.wiimp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.filter.*
import net.solutinno.wiimp.widgets.DateFilterItemView
import net.solutinno.wiimp.widgets.ListFilterItemView

class FilterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.filter, container)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        group_by.onChange = listChange
        order_by.onChange = listChange

        date_from.onChange = dateChange
        date_to.onChange = dateChange

        length.onChange = listChange
        favorites.onChange = listChange

        sites.onChange = multiSelectChange
        tags.onChange = multiSelectChange
    }

    val listChange : (ListFilterItemView, IntArray) -> Unit = { sender, value ->

    }

    val multiSelectChange : (ListFilterItemView, IntArray) -> Unit = { sender, value ->

    }

    val dateChange : (DateFilterItemView, Long) -> Unit = { sender, value ->
        when (sender) {
            date_from -> if (value > date_to.value && date_to.value > -1) {
                date_from.value = date_to.value
                date_to.value = value
            }
            date_to -> if (value < date_from.value && date_from.value > -1) {
                date_to.value = date_from.value
                date_from.value = value
            }
        }
    }
}
