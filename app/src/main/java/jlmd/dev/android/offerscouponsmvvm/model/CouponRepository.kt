package jlmd.dev.android.offerscouponsmvp.model

import androidx.lifecycle.MutableLiveData
import jlmd.dev.android.offerscouponsmvvm.model.Coupon

interface CouponRepository {
    fun getCoupons(): MutableLiveData<List<Coupon>>

    fun callCouponsAPI()
}