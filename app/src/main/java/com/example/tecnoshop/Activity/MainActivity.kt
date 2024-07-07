package com.example.tecnoshop.Activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.tecnoshop.Adapter.CategoryAdapter
import com.example.tecnoshop.Adapter.SliderAdapter
import com.example.tecnoshop.Model.SliderModel
import com.example.tecnoshop.ViewModel.MainViewModel
import com.example.tecnoshop.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        initBanners()
        initCategory()
    }






    private fun initCategory() {
        binding.progressBarCategory.visibility=View.VISIBLE
        viewModel.category.observe(this,Observer{
            binding.viewCategory.layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            binding.viewCategory.adapter=CategoryAdapter(it)
            binding.progressBarCategory.visibility=View.GONE
        })
        viewModel.loadCategory()
    }

    private fun initBanners() {
        binding.progressBarBanner.visibility = View.VISIBLE

        viewModel.banners.observe(this, Observer {  //LiveDate gözlemleyerek bu verilerin değişmesini bekler
            banners(it)
            binding.progressBarBanner.visibility=View.GONE
        })
       viewModel.loadRecommended()
    }

    private fun banners(images: List<SliderModel>) {
        binding.viewPagerSlider.adapter = SliderAdapter(images, binding.viewPagerSlider)
        binding.viewPagerSlider.clipToPadding = false  //ViewPager'ın kenarındaki iç boşluğa (padding) sığmasını sağlar
        binding.viewPagerSlider.clipChildren = false   //ViewPager'ın kenarındaki iç boşluğa (padding) sığmasını sağlar
        binding.viewPagerSlider.offscreenPageLimit = 3  //ViewPager'ın kenarındaki iç boşluğa (padding) sığmasını sağlar
        binding.viewPagerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER  //ViewPager'ın kenarındaki iç boşluğa (padding) sığmasını sağlar

        val compositePageTransformer = CompositePageTransformer().apply {  //'CompositePageTransformer' Birden fazla dönüşümü tek bir yapıda birleştirerek, her bir sayfada farklı dönüşümler uygulamak mümkün olur.
            addTransformer(MarginPageTransformer(40))  //'MarginPageTransformer', sayfa arasında belirli bir boşluk (margin) ekleyen bir tür
        }
        binding.viewPagerSlider.setPageTransformer(compositePageTransformer)
        if (images.size > 1) {   //Eğer images listesinde birden fazla öğe varsa, nokta göstergesini (dotIndicator) görünür hale getirir ve ViewPager'a bağlar.
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.viewPagerSlider)
        }
    }
}
