package br.com.lea.easyvendas.ui.utils.customviews

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import br.com.lea.easyvendas.R
import com.lea.commons.extensions.show

@RequiresApi(Build.VERSION_CODES.M)
class HorizontalBulletListView(context: Context, val attributeSet: AttributeSet?) :
    HorizontalScrollView(context, attributeSet) {

    var clickCallback: (clicked: String) -> Unit = { it }

    constructor(context: Context) : this(context, null)

    private var listViewChilds = arrayListOf<TextView>()

    fun renderBullets(bulletList: ArrayList<String>) {

        val llBase = createLinearLayout()
        this.addView(llBase)

        bulletList.forEach { name ->
            var ll = LayoutInflater.from(context).inflate( R.layout.item_bullet_view, null) as LinearLayoutCompat
            var button = ll.findViewById<AppCompatTextView>(R.id.btnRadioSample)

            with(button) {
                show(true)
                text = " $name  "

                setOnClickListener {
                    refreshList()
                    clickCallback.invoke(name)
                    paintCheck(this)
                }

                llBase.addView(ll)
                listViewChilds.add(this)

            }

        }
    }

    private fun createLinearLayout(): LinearLayoutCompat =
        LinearLayoutCompat(context).apply {
            this.layoutParams = LinearLayoutCompat.LayoutParams(
                MATCH_PARENT, WRAP_CONTENT
            )
            this.orientation = LinearLayoutCompat.HORIZONTAL
        }

    private fun paintUncheck(textView: TextView) {
        with(textView) {
            background =
                context.getDrawable(R.drawable.selector_custom_background_radiobutton_uncheck)
            setTextColor(context.getColor(R.color.principal))
        }
    }


    private fun paintCheck(textView: TextView) {
        with(textView) {
            background =
                context.getDrawable(R.drawable.selector_custom_background_radiobutton_check)
            setTextColor(context.getColor(R.color.white))
        }
    }

    private fun refreshList() {
        for (button: TextView in listViewChilds) {
            with(button) {
                paintUncheck(this)
            }
        }
    }


    fun findViewByName(name:String): TextView?{
        return listViewChilds.find { it.text.toString()==name }
    }


}