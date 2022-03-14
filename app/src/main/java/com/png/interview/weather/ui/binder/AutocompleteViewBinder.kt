package com.png.interview.weather.ui.binder

import android.view.View
import com.png.interview.R
import com.png.interview.databinding.ViewAutocompleteBinding
import com.png.interview.weather.ui.model.AutocompleteViewData
import com.xwray.groupie.viewbinding.BindableItem

class AutocompleteViewBinder(
    val autocompleteViewData: AutocompleteViewData,
) : BindableItem<ViewAutocompleteBinding>() {

    override fun bind(viewBinding: ViewAutocompleteBinding, position: Int) { }

    override fun getLayout() = R.layout.view_autocomplete

    override fun initializeViewBinding(view: View) = ViewAutocompleteBinding.bind(view).apply { viewData = autocompleteViewData }
}