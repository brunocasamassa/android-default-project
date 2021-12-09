package br.com.lea.easyvendas.ui.utils.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import br.com.lea.easyvendas.R
import br.com.lea.easyvendas.databinding.ItemHeaderBinding
import com.lea.commons.constants.ProjectConstants
import com.lea.commons.datalayer.shared.SharedPreferenceHandler

class CustomViewHeader(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

     val binding: ItemHeaderBinding = ItemHeaderBinding.inflate(
        LayoutInflater.from(context),this, true

    )

    init{
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomViewHeader)

        with(this.binding){
            textName.text = SharedPreferenceHandler.getStringForced(ProjectConstants.USERNAME)
            textUpdateHora.text = attributes.getString(R.styleable.CustomViewHeader_textUdateHora)

            menuHamburg.setOnClickListener {
                menuKOnClickListener.invoke()
            }

            //todo swipe refresh layout
/*            imgUpdate.setOnClickListener {
                refreshOnClickListener.invoke()
            }*/
        }

    }
    companion object{

        private var menuKOnClickListener: () -> Unit ={}
        fun setOnClickListenerMenu(callback:() -> Unit){
            menuKOnClickListener = callback
        }

        private var refreshOnClickListener: () -> Unit ={}
        fun setOnClickListenerRefresh(callback:() -> Unit){
            refreshOnClickListener = callback
        }
    }

}