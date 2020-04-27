package com.example.appu17

import adapter.DetailAdapter
import adapter.ImageListAdapter
import android.app.Dialog
import android.content.pm.PackageManager
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appu17.R
import com.example.appu17.api.ApiClient
import com.example.appu17.api.GetData
import com.google.android.material.bottomsheet.BottomSheetBehavior
import domain.ComicImage
import domain.DetailComic
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.detail_base.*
import kotlinx.android.synthetic.main.image_list_item.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {
    lateinit var detailComic: DetailComic
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val intent=intent
        val comic_Id=intent.getStringExtra("chapter_Id")

        CoroutineScope(Dispatchers.Main).launch {
            val comicImage= GetData.getComicImage(comic_Id)
            ViewPager.adapter=ImageListAdapter(comicImage,this@DetailActivity,ViewPager)
        }
    }



}
