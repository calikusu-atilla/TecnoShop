package com.example.tecnoshop.Adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.tecnoshop.Model.SliderModel
import com.example.tecnoshop.R
import com.example.tecnoshop.databinding.ViewholderCategoryBinding
import com.google.api.Context
import kotlinx.coroutines.Runnable

class SliderAdapter(
    private lateinit var sliderItems : List<SliderModel>?, //Slider öğelerinin tutulduğu liste null olarak tanımlandı
    private val viewPager2: ViewPager2                     //Slider'ın gösterileceği ViewPager bileşeni
): RecyclerView.Adapter<SliderAdapter.SliderViewholder>() {
    private lateinit var context: Context                  // Context nesnesi
    private val runnable = Runnable {                      // ViewPager2 için otomatik geçiş işlevi
        sliderItems = sliderItems
        notifyDataSetChanged()                             //çağrısını yaparak slider öğelerinin güncellenmesini sağlar.
    }
    class SliderViewholder(itemView : View):RecyclerView.ViewHolder(itemView) {    // SliderViewHolder sınıfı, RecyclerView.ViewHolder sınıfından kalıtım alır
        private var ImageView = itemView.findViewById(R.id.ima)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewholder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: SliderViewholder, position: Int) {
        TODO("Not yet implemented")
    }


}