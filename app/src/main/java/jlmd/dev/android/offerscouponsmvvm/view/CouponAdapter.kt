package jlmd.dev.android.offerscouponsmvvm.view

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import jlmd.dev.android.offerscouponsmvvm.R
import jlmd.dev.android.offerscouponsmvvm.model.Coupon
import jlmd.dev.android.offerscouponsmvvm.viewmodel.CouponViewModel

class CouponAdapter (var couponViewModel: CouponViewModel, var resource: Int) : RecyclerView.Adapter<CouponAdapter.CardCouponHolder>() {

    var coupons : List<Coupon>? = null

    fun setCouponsList(coupons : List<Coupon>?){
        this.coupons = coupons
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CardCouponHolder {
        var view: View = LayoutInflater.from(p0!!.context).inflate(resource, p0, false)
        return CardCouponHolder(view)
    }

    override fun getItemCount(): Int {
        return coupons?.size ?: 0
    }

    override fun onBindViewHolder(p0: CardCouponHolder, p1: Int) {
        var coupon = coupons?.get(p1)
        p0.setDataCard(coupon)
    }

    class CardCouponHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var coupon: Coupon? = null
        private var imgCoupon: ImageView = v.findViewById(R.id.imgCoupon)
        private var tvTitle: TextView = v.findViewById(R.id.tvTitle)
        private var tvDescriptionShort: TextView = v.findViewById(R.id.tvDescriptionShort)
        private var tvCategory: TextView = v.findViewById(R.id.tvCategory)
        private var tvDate: TextView = v.findViewById(R.id.tvDate)

        init {
            v.setOnClickListener(this)
        }

        fun setDataCard(coupon: Coupon?){
            this.coupon = coupon
            if (coupon?.image_url?.isNotEmpty() == true)
                Picasso.get().load(coupon?.image_url).resize(520, 520).centerCrop().into(imgCoupon)
            tvTitle.text = coupon?.title
            tvDescriptionShort.text = coupon?.descriptionShort
            tvCategory.text = coupon?.category
            tvDate.text = coupon?.endDate
        }

        override fun onClick(v: View) {
            coupon?.let { Log.i("CLICK Coupon: ", it.title) }
            val context = v.context
            val showPhotoIntent = Intent(context, CouponDetailActivity::class.java)
            showPhotoIntent.putExtra("COUPON", coupon)
            context.startActivity(showPhotoIntent)
        }
    }
}