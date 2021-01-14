package com.example.projecttest2.promotion

import android.app.Application
import android.widget.Adapter
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecttest2.database.DaoMap
import kotlinx.coroutines.launch
import java.security.AccessController.getContext

class PromotionViewModel(
        val database: DaoMap,
        application: Application) : AndroidViewModel(application) {

    private val promotionRepository = PromotionRepository(database, application )

    init {
        viewModelScope.launch {
            promotionRepository.refreshPromotionPage()
        }
    }


}
