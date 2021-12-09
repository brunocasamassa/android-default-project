package br.com.lea.easyvendas.ui.utils.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.lea.easyvendas.R
import br.com.lea.easyvendas.databinding.HeaderSearchViewBinding
import com.lea.commons.constants.ProjectConstants
import com.lea.commons.datalayer.shared.SharedPreferenceHandler
import com.lea.commons.extensions.DEFAULT_PATTERN_HOUR
import com.lea.commons.extensions.getCurrentlyData
import com.lea.commons.extensions.safeInflate

class HeaderSearchView(context: Context, val attributeSet: AttributeSet?) :
    ConstraintLayout(context, attributeSet) {


    val binding: HeaderSearchViewBinding = HeaderSearchViewBinding.inflate(
        LayoutInflater.from(context), this, true

    )

    var clickCallback: () -> Unit = { }

    var onSearchSubmitted: (String) -> Unit = { it }

    constructor(context: Context) : this(context, null)


    init {
        safeInflate(context, R.layout.header_search_view, null)

        this.setOnClickListener { clickCallback.invoke() }

        with(this.binding) {
            textUsername.text = SharedPreferenceHandler.getStringForced(ProjectConstants.USERNAME)

            textHour.text = DEFAULT_PATTERN_HOUR.getCurrentlyData()
            searchBarStoresMaps.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { onSearchSubmitted.invoke(it) }
                    return !query.isNullOrEmpty()
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return !newText.isNullOrEmpty()
                }

            })
        }

    }


}