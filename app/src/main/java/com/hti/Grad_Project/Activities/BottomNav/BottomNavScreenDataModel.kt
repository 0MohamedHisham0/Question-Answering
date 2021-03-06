package com.hti.Grad_Project.Activities.BottomNav

import com.hti.Grad_Project.R
import com.hti.Grad_Project.Utilities.ConstantsBottomNav.ROUTE_HOME
import com.hti.Grad_Project.Utilities.ConstantsBottomNav.ROUTE_GOOGLE_ENHANCED
import com.hti.Grad_Project.Utilities.ConstantsBottomNav.ROUTE_SAVED_PDF
import com.hti.Grad_Project.Utilities.ConstantsBottomNav.ROUTE_SAVED_QUESTIONS

sealed class BottomNavScreenDataModel(val route: String?, var label: String, val icon: Int) {

    object Home : BottomNavScreenDataModel(ROUTE_HOME, "Home", R.drawable.icon_home )
    object MyPdf : BottomNavScreenDataModel(ROUTE_SAVED_PDF, "My Pdf", R.drawable.icon_saved_books)
    object SavedBooks : BottomNavScreenDataModel(ROUTE_SAVED_QUESTIONS, "Questions",R.drawable.icon_save)

    object Items {
        val items = listOf(
            Home, MyPdf, SavedBooks
        )
    }
}