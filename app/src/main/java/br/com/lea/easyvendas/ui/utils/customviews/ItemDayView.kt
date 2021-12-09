package br.com.lea.easyvendas.ui.utils.customviews

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.*
import android.widget.LinearLayout.HORIZONTAL
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.lea.easyvendas.R
import br.com.lea.easyvendas.databinding.ItemBulletViewBinding
import br.com.lea.easyvendas.databinding.ItemScriptDateBinding
import br.com.lea.easyvendas.databinding.ItemStoresRoadmapBinding
import com.google.android.material.button.MaterialButton
import com.lea.commons.extensions.safeInflate
import com.lea.commons.extensions.show
import com.lea.commons.extensions.toPx

@RequiresApi(Build.VERSION_CODES.M)
class ItemDayView(context: Context, val attributeSet: AttributeSet?) :
    ConstraintLayout(context, attributeSet) {

    var clickCallback: () -> Unit = {  }

    constructor(context: Context) : this(context, null)

    private val binding: ItemScriptDateBinding = ItemScriptDateBinding.inflate(
        LayoutInflater.from(context), this, true
    )


    init {
        safeInflate(context,R.layout.item_script_date,null)
        this.setOnClickListener { clickCallback.invoke() }
    }


    fun setDate(dayOfMonth:String, dayOfWeek:String ){
        binding.textDateDay.text = dayOfMonth
        binding.textDateWeek.text = dayOfWeek
    }






}