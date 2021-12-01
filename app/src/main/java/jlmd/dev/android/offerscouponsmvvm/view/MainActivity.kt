package jlmd.dev.android.offerscouponsmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import jlmd.dev.android.offerscouponsmvvm.R
import jlmd.dev.android.offerscouponsmvvm.databinding.ActivityMainBinding
import jlmd.dev.android.offerscouponsmvvm.model.Coupon
import jlmd.dev.android.offerscouponsmvvm.viewmodel.CouponViewModel

class MainActivity : AppCompatActivity() {

    private var couponViewModel: CouponViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        //VIEW
        setupBindings(savedInstanceState)
        //CallCoupons
        //getCoupons -> trae la lista de cupones
    }

    fun setupBindings(savedInstanceState: Bundle?){
        var activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        couponViewModel = ViewModelProvider.NewInstanceFactory().create(CouponViewModel::class.java)

        activityMainBinding.model = couponViewModel

        setupListUpdate()
    }

    fun setupListUpdate(){
        //llamar al listado de coupons CallCoupons
        couponViewModel?.callCoupons()
        //getCoupons
        couponViewModel?.getCoupons()?.observe(this, Observer { coupons: List<Coupon> ->
            Log.w("COUPON", coupons[0].title)
            couponViewModel?.setCouponsInRecyclerAdapter(coupons)
        })
    }
}