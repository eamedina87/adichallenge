package tech.medina.adichallenge.domain.models

import android.content.Context
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import tech.medina.adichallenge.R

@Parcelize
data class Product(
    val currency: String,
    val description: String,
    val id: String,
    val imageUrl: String,
    val name: String,
    val price: String, //since we are not making math operations in this app, we'll leave it as String
    val reviews: List<Review>
): Parcelable {

    //If we have Euro as currency we add first the price, then the symbol. Else symbol, then price
    fun getFormattedPrice(context: Context): String =
        if (currency == context.getString(R.string.currency_euro)) {
            context.getString(R.string.product_price, price, currency)
        } else {
            context.getString(R.string.product_price, currency, price)
        }

}