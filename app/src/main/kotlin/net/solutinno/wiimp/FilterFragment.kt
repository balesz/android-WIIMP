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
        group_by.onChanged = listChange
        order_by.onChanged = listChange

        date_from.onChanged = dateChange
        date_to.onChanged = dateChange

        length.onChanged = listChange
        favorites.onChanged = listChange

        sites.onChanged = multiSelectChange
        tags.onChanged = multiSelectChange
    }

    val listChange : (View, IntArray) -> Unit = { sender, value ->

    }

    val multiSelectChange : (View, IntArray) -> Unit = { sender, value ->

    }

    val dateChange : (View, Long) -> Unit = { sender, value ->
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
