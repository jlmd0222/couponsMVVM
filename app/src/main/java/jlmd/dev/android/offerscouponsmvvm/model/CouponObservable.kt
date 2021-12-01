package jlmd.dev.android.offerscouponsmvvm.model

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import jlmd.dev.android.offerscouponsmvp.model.CouponRepository
import jlmd.dev.android.offerscouponsmvp.model.CouponRepositoryImpl

class CouponObservable: BaseObservable() {
    //Repository
    private var couponRepository: CouponRepository = CouponRepositoryImpl()
    fun callCoupons(){
        couponRepository.callCouponsAPI()
    }

    //ViewModel
    fun getCoupons(): MutableLiveData<List<Coupon>> {
        return couponRepository.getCoupons()
    }
}