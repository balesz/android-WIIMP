package net.solutinno.wiimp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.filter.*
import net.solutinno.wiimp.model.Filter

class FilterFragment : Fragment() {

    val filter = Filter()

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
        sites.onChanged = listChange
        tags.onChanged = listChange
    }

    val listChange : (View, IntArray) -> Unit = { sender, value ->
        when (sender) {
            group_by -> filter.groupBy = value.first()
            order_by -> filter.orderBy = value.first()
            length -> filter.length = value.first()
            favorites -> filter.favorites = value.first()
            sites -> filter.sites = sites.value.map { "${sites.entries[it]}" }.toTypedArray()
            tags -> filter.tags = tags.value.map { "${tags.entries[it]}" }.toTypedArray()
        }
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
        filter.dateFrom = date_from.value
        filter.dateTo = date_to.value
    }

    val resetClick = View.OnClickListener {
        
    }

    val filterClick = View.OnClickListener {

    }
}
