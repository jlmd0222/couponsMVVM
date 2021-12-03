package jlmd.dev.android.offerscouponsmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jlmd.dev.android.offerscouponsmvvm.R
import jlmd.dev.android.offerscouponsmvvm.model.Coupon
import jlmd.dev.android.offerscouponsmvvm.model.CouponObservable
import jlmd.dev.android.offerscouponsmvvm.view.CouponAdapter

class CouponViewModel: ViewModel() {

    private var couponObservable: CouponObservable = CouponObservable()
    private var couponsAdapter: CouponAdapter? = null

    fun callCoupons(){
        couponObservable.callCoupons()
    }

    fun getCoupons(): MutableLiveData<List<Coupon>>{
        return couponObservable.getCoupons()
    }

    fun setCouponsInRecyclerAdapter(coupons: List<Coupon>){
        couponsAdapter?.setCouponsList(coupons)
        couponsAdapter?.notifyDataSetChanged()
    }

    fun getRecycleAdapter(): CouponAdapter? {
        couponsAdapter = CouponAdapter(this, R.layout.card_coupon)
        return couponsAdapter
    }

    fun getCouponAt(position: Int): Coupon? {
        //como ya se llamo a callCoupons entonces ya se tiene la lista en el observable
        var coupons: List<Coupon>? = couponObservable.getCoupons().value
        return coupons?.get(position)
    }
}