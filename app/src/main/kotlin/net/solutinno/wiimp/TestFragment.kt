package net.solutinno.wiimp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.test.view.*
import net.solutinno.wiimp.bindings.Binder

class TestFragment : Fragment() {

    lateinit var binder: TestBinder

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.test, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder = TestBinder(view!!)
    }
}

class TestBinder(val view: View) : Binder() {

    var text1 : CharSequence? by textBinding(view.test_text1)

    var text2 : CharSequence? by textBinding(view.test_text2)

    var text2Visible : Int by visibleBinding(view.test_text2) {
        text1 = "Text2 is ${if (it == View.VISIBLE) "visible" else "invisible"}."
    }

    val button1Click : () -> Unit by clickBinding(view.test_button1) {
        val message = "Text1=$text1; Text2=$text2"
        Toast.makeText(view.context, message, Toast.LENGTH_SHORT).show()
    }

    val button2Click : () -> Unit by clickBinding(view.test_button2) {
        text2Visible = if (text2Visible == View.VISIBLE) View.INVISIBLE else View.VISIBLE
    }
}