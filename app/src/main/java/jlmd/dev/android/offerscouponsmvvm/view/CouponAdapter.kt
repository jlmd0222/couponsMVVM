package jlmd.dev.android.offerscouponsmvvm.view

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import jlmd.dev.android.offerscouponsmvvm.model.Coupon
import jlmd.dev.android.offerscouponsmvvm.viewmodel.CouponViewModel

class CouponAdapter (var couponViewModel: CouponViewModel, var resource: Int) : RecyclerView.Adapter<CouponAdapter.CardCouponHolder>() {

    var coupons : List<Coupon>? = null

    fun setCouponsList(coupons : List<Coupon>?){
        this.coupons = coupons
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CardCouponHolder {
        var layoutInflater: LayoutInflater = LayoutInflater.from(viewGroup.context)
        var binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, viewType, viewGroup, false)
        return CardCouponHolder(binding)
    }

    override fun getItemCount(): Int {
        return coupons?.size ?: 0
    }

    override fun onBindViewHolder(p0: CardCouponHolder, p1: Int) {
        var coupon = coupons?.get(p1)
        //p0.setDataCard(coupon)
    }

    class CardCouponHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private var binding: ViewDataBinding? = null

        init {
            this.binding = binding
        }

        fun setDataCard(couponViewModel: CouponViewModel, position: Int){
            //binding?.setVariable(BR.model, couponViewModel)
        }

        override fun onClick(v: View) {
            /*coupon?.let { Log.i("CLICK Coupon: ", it.title) }
            val context = v.context
            val showPhotoIntent = Intent(context, CouponDetailActivity::class.java)
            showPhotoIntent.putExtra("COUPON", coupon)
            context.startActivity(showPhotoIntent)*/
        }
    }
}