package br.com.lea.easyvendas.ui.utils.customviews.stores

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
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import br.com.lea.easyvendas.R
import br.com.lea.easyvendas.databinding.ItemSelectedViewBinding
import br.com.lea.easyvendas.databinding.ItemStoresRoadmapBinding
import br.com.lea.easyvendas.domain.model.stores.StoreRoadmap
import br.com.lea.easyvendas.ui.utils.customviews.ItemBulletView
import com.google.android.material.button.MaterialButton
import com.lea.commons.extensions.safeInflate
import com.lea.commons.extensions.show
import com.lea.commons.extensions.toPx

@RequiresApi(Build.VERSION_CODES.M)
class StoresRoadmapView(context: Context, val attributeSet: AttributeSet?) :
    LinearLayoutCompat(context, attributeSet) {

    var clickCallback: () -> Unit = { }

    var defineHourCallback: () -> Unit = { }

    var seeRouteCallback: () -> Unit = { }

    private val binding: ItemStoresRoadmapBinding = ItemStoresRoadmapBinding.inflate(
        LayoutInflater.from(context), this, true
    )


    constructor(context: Context) : this(context, null)


    init {
        safeInflate(context, R.layout.item_stores_roadmap, null)


    }


    fun setStore(storeRoadmap: StoreRoadmap) {

        binding.storeTitle.text = storeRoadmap.store.name
        binding.storeDate.text = storeRoadmap.date
        binding.distance.text = "35min - 23km"
        binding.defineHour.setOnClickListener { defineHourCallback.invoke() }

        with(binding.seeRoute) {
            setBulletName("Ver Rota")
            setOnClickListener { seeRouteCallback.invoke() }
        }

        this.setOnClickListener { clickCallback.invoke() }

    }


}