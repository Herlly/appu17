package com.example.appu17.api

import adapter.DetailAdapter
import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.donkingliang.labels.LabelsView
import com.example.appu17.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import domain.DetailComic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DiaLog {
     fun show(mcontext: Context,detailComic: DetailComic?){
         val bottomSheetDialog= BottomSheetDialog(mcontext)
         bottomSheetDialog.setContentView(R.layout.detail_base)
         val detail_name=bottomSheetDialog.findViewById<TextView>(R.id.Detail_name)
         val detail_cover=bottomSheetDialog.findViewById<ImageView>(R.id.Detail_cover)
         val detail_label=bottomSheetDialog.findViewById<LabelsView>(R.id.Detail_label)
         val detail_author=bottomSheetDialog.findViewById<TextView>(R.id.Detail_author)
         val recyclerView=bottomSheetDialog.findViewById<RecyclerView>(R.id.detail_list)

         recyclerView?.layoutManager= GridLayoutManager(mcontext,4)

         CoroutineScope(Dispatchers.Main).launch {
             detail_name?.text=detailComic?.data?.returnData?.comic?.name
             detail_label?.setLabels(detailComic?.data?.returnData?.comic?.theme_ids)
             if (detail_cover != null) {
                 Glide.with(mcontext).load(detailComic?.data?.returnData?.comic?.cover).into(detail_cover)
             }
             detail_author?.text=detailComic?.data?.returnData?.comic?.author?.name
             recyclerView?.adapter= detailComic?.let { it1 -> DetailAdapter(it1,mcontext
             ) }
         }
         bottomSheetDialog.show()
     }

}