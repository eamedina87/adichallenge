package tech.medina.adichallenge.domain.models

import android.content.Context
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import tech.medina.adichallenge.R
import java.math.BigDecimal

@Parcelize
data class Product(
    val currency: String,
    val description: String,
    val id: String,
    val imageUrl: String,
    val name: String,
    val price: Double,
): Parcelable {

    //If we have Euro as currency we add first the price, then the symbol. Else symbol, then price
    fun getFormattedPrice(context: Context): String {
        val euro = context.getString(R.string.currency_euro)
        return if (currency.isBlank() || currency == euro) {
            context.getString(R.string.product_price_inverted, price, euro)
        } else {
            context.getString(R.string.product_price, currency, price)
        }
    }

}