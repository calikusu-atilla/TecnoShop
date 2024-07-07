package com.example.tecnoshop.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.tecnoshop.Model.SliderModel
import com.example.tecnoshop.R

class SliderAdapter(
    private var sliderItems: List<SliderModel>,  //Slider öğelerinin tutulduğu liste
    private val viewPager2: ViewPager2           //Slider'ın gösterileceği ViewPager bileşeni
) : RecyclerView.Adapter<SliderAdapter.SliderViewholder>() {

    private val runnable = Runnable {            // ViewPager2 için otomatik geçiş işlevi
        sliderItems = sliderItems
        notifyDataSetChanged()                   //çağrısını yaparak slider öğelerinin güncellenmesini sağlar.
    }

    class SliderViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {    // SliderViewHolder sınıfı, RecyclerView.ViewHolder sınıfından kalıtım alır
        private var imageView: ImageView = itemView.findViewById(R.id.imageSlider)

        fun setImage(sliderItems: SliderModel, context: android.content.Context) {  //Slider öğesinin görüntüsünü ayarlayan yöntem
            val requestOptions = RequestOptions().transform(CenterInside())        // Glide için görüntü yükleme ve görüntüyü ortalayarak boyutlandırma seçenekleri oluşturuluyor
            Glide.with(context)                                                    // Glide kütüphanesi kullanılarak context üzerinden görüntü yükleniyor
                .load(sliderItems.url)                                             // SliderModel içindeki url kullanılarak görüntü yükleniyor
                .apply(requestOptions)                                             // Yükleme seçenekleri uygulanıyor
                .into(imageView)                                                   // Yüklenen görüntü imageView bileşenine aktarılıyor
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderAdapter.SliderViewholder {
        val context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slider_image_container, parent, false)
        return SliderViewholder(view)
    }

    override fun onBindViewHolder(holder: SliderAdapter.SliderViewholder, position: Int) {
        holder.setImage(sliderItems[position], holder.itemView.context)

        if (position == sliderItems.lastIndex - 1)
            viewPager2.post(runnable)
    }

    override fun getItemCount(): Int = sliderItems.size
}
