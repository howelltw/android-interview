package com.png.interview.weather.ui.model

sealed class AutocompleteViewRepresentation {
    class AutocompleteViewRep(val data: List<AutocompleteViewData>) : AutocompleteViewRepresentation()
    object Empty : AutocompleteViewRepresentation()
    object Error : AutocompleteViewRepresentation()
}
