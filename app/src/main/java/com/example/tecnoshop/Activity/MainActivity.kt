package com.example.tecnoshop.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.tecnoshop.Adapter.BestSellerAdapter
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
        initBestSeller()
        bottomNavigation()
    }

    private fun bottomNavigation() {
        binding.cartBtn.setOnClickListener { startActivity(Intent(this@MainActivity,CartActivity::class.java))}
        binding.profilBtn.setOnClickListener { startActivity(Intent(this@MainActivity,ProfilActivity::class.java))}
        binding.favoriBtn.setOnClickListener { startActivity(Intent(this,EditProfilActivity::class.java)) }

    }

    private fun initBestSeller() {
        binding.progressBarBestSeller.visibility = View.VISIBLE

        viewModel.bestseller.observe(this, Observer {
            binding.viewBestSeller.layoutManager = GridLayoutManager(this@MainActivity,3)
            binding.viewBestSeller.adapter = BestSellerAdapter(it)
            binding.progressBarBestSeller.visibility = View.GONE
        })
        viewModel.loadBestSeller()
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
       viewModel.loadBanner()
    }

    private fun banners(images: List<SliderModel>) {
        binding.viewPagerSlider.adapter = SliderAdapter(images, binding.viewPagerSlider)
        binding.viewPagerSlider.clipToPadding = false  //ViewPager'ın kenarındaki iç boşluğa (padding) sığmasını sağlar
        binding.viewPagerSlider.clipChildren = false   //ViewPager'ın kenarındaki iç boşluğa (padding) sığmasını sağlar
        binding.viewPagerSlider.offscreenPageLimit = 6  //ViewPager'ın kenarındaki iç boşluğa (padding) sığmasını sağlar
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
