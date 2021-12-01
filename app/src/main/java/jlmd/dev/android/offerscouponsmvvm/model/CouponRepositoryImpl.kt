package jlmd.dev.android.offerscouponsmvp.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import jlmd.dev.android.offerscouponsmvvm.model.ApiAdapter
import jlmd.dev.android.offerscouponsmvvm.model.Coupon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CouponRepositoryImpl: CouponRepository {

    //El patron Observador obedece a q se tenga una lista de elementos q se denomina SUJETO
    //Subject MutableLiveData
    private var coupons = MutableLiveData<List<Coupon>>()

    override fun getCoupons(): MutableLiveData<List<Coupon>> {
        //lo q se hace es retornar el Subject
        return coupons
    }

    //TODA LA LOGICA DE CONEXION
    override fun callCouponsAPI() {
        //CONTROLLER
        //Observers List Coupon -> si esta lista cambia, como al final se la asigna a la lista mutable, la afecta y actualiza
        //la lista mutable tiene metodos especiales como Observe que lo que hace es notificar los cambios, en donde sea q se esten ejecutando
        var couponsList: ArrayList<Coupon>? = ArrayList<Coupon>()
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getCoupons()
        call.enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                t.message?.let { Log.e("ERROR: ", it) }
                t.stackTrace
                //Toast.makeText(applicationContext,t.message, Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val offersJsonArray = response.body()?.getAsJsonArray("offers")
                offersJsonArray?.forEach { jsonElement: JsonElement ->
                    var jsonObject = jsonElement.asJsonObject
                    var coupon = Coupon(jsonObject)
                    couponsList?.add(coupon)
                }
                coupons.value = couponsList
            }
        })
        //CONTROLLER
    }
}