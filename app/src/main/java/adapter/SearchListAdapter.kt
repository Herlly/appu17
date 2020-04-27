package adapter

import GreenDao.CoverInfo
import GreenDao.CoverManager
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.donkingliang.labels.LabelsView
import com.example.appu17.DetailActivity
import com.example.appu17.R
import com.example.appu17.api.ApiClient
import com.example.appu17.api.DiaLog
import com.example.appu17.api.GetData
import com.example.appu17.api.SaveFile
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import domain.DetailComic
import domain.SearchComic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchListAdapter (private var mData:MutableList<SearchComic.Comic>?= mutableListOf(),private var mcontext:Context):RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemview= LayoutInflater.from(parent.context).inflate(R.layout.item_comic,parent,false)
        return ViewHolder(itemview)
    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
    }

    override fun getItemCount(): Int {
        return mData!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemView=holder.itemView
        val ComicCover=itemView.findViewById(R.id.Comic_cover)as ImageView
        val ComicName=itemView.findViewById<TextView>(R.id.comic_name)
        val Comic_intro=itemView.findViewById<TextView>(R.id.name_intro)
        val Comic_Tag=itemView.findViewById<LabelsView>(R.id.Comic_tag)
        val Comic_collect=itemView.findViewById<Button>(R.id.collect)

        Comic_Tag.setLabels(mData?.get(position)?.tags)
        Glide.with(itemView.context).load(mData?.get(position)?.cover).into(ComicCover)
        ComicName.text=mData?.get(position)?.name
        Comic_intro.text=mData?.get(position)?.description
        if(CoverManager.instances.search_CoverInfo(mData?.get(position)?.name)!=null){
            Comic_collect.text="已收藏"
        }


        Comic_collect.setOnClickListener(View.OnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                if(CoverManager.instances.search_CoverInfo(mData?.get(position)?.name)==null){
                    val bitmap=
                        mData?.get(position)?.cover?.let { it1 -> SaveFile.getBitmap(mcontext, it1) }
                    val pathname= bitmap?.let { it1 -> mData?.get(position)?.name?.let { it2 ->
                        SaveFile.onSaveBitmap(it1, it2) } }
                    val coverInfo= CoverInfo(mData?.get(position)?.name,
                        pathname,
                        System.currentTimeMillis().toString(),
                        "未阅读",
                        mData?.get(position)?.comicId)
                    CoverManager.instances.add(coverInfo)
                    Toast.makeText(mcontext,"收藏成功", Toast.LENGTH_SHORT).show()
                    Comic_collect.text="已收藏"
                }else{
                    Toast.makeText(mcontext,"已收藏", Toast.LENGTH_SHORT).show()
                }
            }
        })



        itemView.setOnClickListener(View.OnClickListener {
//            val bottomSheetDialog=BottomSheetDialog(mcontext)
//            bottomSheetDialog.setContentView(R.layout.detail_base)
//            val detail_name=bottomSheetDialog.findViewById<TextView>(R.id.Detail_name)
//            val detail_cover=bottomSheetDialog.findViewById<ImageView>(R.id.Detail_cover)
//            val detail_label=bottomSheetDialog.findViewById<LabelsView>(R.id.Detail_label)
//            val detail_author=bottomSheetDialog.findViewById<TextView>(R.id.Detail_author)
//            val recyclerView=bottomSheetDialog.findViewById<RecyclerView>(R.id.detail_list)
//            recyclerView?.layoutManager= GridLayoutManager(mcontext,4)
//            CoroutineScope(Dispatchers.Main).launch {
//                val detailComic= mData?.get(position)?.comicId?.let { it1 -> getData(it1) }
//                detail_name?.text=detailComic?.data?.returnData?.comic?.name
//                detail_label?.setLabels(detailComic?.data?.returnData?.comic?.theme_ids)
//                if (detail_cover != null) {
//                    Glide.with(mcontext).load(detailComic?.data?.returnData?.comic?.cover).into(detail_cover)
//                }
//                detail_author?.text=detailComic?.data?.returnData?.comic?.author?.name
//                recyclerView?.adapter= detailComic?.let { it1 -> DetailAdapter(it1,mcontext
//                ) }
//            }
//            bottomSheetDialog.show()

            CoroutineScope(Dispatchers.Main).launch {
                DiaLog.show(mcontext,mData?.get(position)?.comicId?.let { it1 -> GetData.getDetailComic(it1) })
            }

        })
    }

}