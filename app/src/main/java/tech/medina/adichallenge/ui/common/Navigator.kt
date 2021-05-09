package tech.medina.adichallenge.ui.common

import android.content.Intent
import android.os.Bundle
import tech.medina.adichallenge.ui.common.dialog.DialogWithTwoOptions
import tech.medina.adichallenge.ui.product.detail.ProductDetailActivity
import tech.medina.adichallenge.ui.utils.Constants
import tech.medina.adichallenge.ui.utils.Utils
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {

    private fun goToActivity(source: BaseActivity,
                             destination: Class<*>,
                             extras: Bundle? = null,
                             requestCode: Int? = null,
                             finish: Boolean = false) {

        val intent = Intent(source, destination)

        extras?.let {
            intent.putExtras(extras)
        }

        if (requestCode == null) {
            source.startActivity(intent)
        } else {
            source.startActivityForResult(intent, requestCode)
        }

        if (finish) source.finish()

    }

    fun showTwoOptionsDialog(activity: BaseActivity,
                             title: String? = null,
                             message: String,
                             leftButtonText: String? = null,
                             rightButtonText: String? = null,
                             leftButtonFunction: (() -> Unit)? = null,
                             rightButtonFunction: (() -> Unit)? = null) {
       DialogWithTwoOptions(title, message, leftButtonText, rightButtonText,
            leftButtonFunction, rightButtonFunction).
        show(activity.supportFragmentManager, "twoOptionsDialog")
    }

    fun goToDetail(source: BaseActivity, id: String, containerId: Int = 0) {
        val extras = Bundle().apply {
            putString(Constants.INTENT_EXTRA_PRODUCT_ID, id)
        }
        goToActivity(source = source, destination = ProductDetailActivity::class.java, extras = extras)
       // if (Utils.isTablet(source)) {
            /*source.replaceFragment(
                containerViewId = containerId,
                fragment = DeliveryDetailFragment.createWithExtras(extras),
                tag = "delivery.detail") */
        //} else {
            /*goToActivity(
                source = source,
                destination = Activity.DeliveryDetail,
                extras = extras)*/
        //}
    }

}