package com.more.mtg.sharedui.models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.more.shareddata.network.provideScryFallService
import com.more.shareddata.network.scryfall.ScryfallMagicSet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SetsScreenViewModel : ViewModel() {
    private val _state = mutableStateOf<LCE<DisplaySets>>(LCE.Loading())
    val state: State<LCE<DisplaySets>> = _state

    fun fetchSets() {
       viewModelScope.launch {
           withContext(Dispatchers.IO) {
               val coreSets = async {provideScryFallService().getSets().data.filter {
                    it.setType == "core" && (!it.code.startsWith("p") && !it.code.startsWith("t"))
                }
               }
               val latestSets = async { provideScryFallService().getSets().data.filter {
                    (it.setType == "expansion" || it.setType == "commander") &&
                    (!it.code.startsWith("p") && !it.code.startsWith("t"))
                }.sortedByDescending { it.releasedAt }
               }
               _state.value = LCE.Content(DisplaySets(coreSets.await(), latestSets.await()))
           }
       }
    }
}

data class DisplaySets(
    val officialSets: List<ScryfallMagicSet>,
    val latestSets: List<ScryfallMagicSet>,
)