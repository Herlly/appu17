package adapter

import GreenDao.CollectInfo
import GreenDao.CoverManager
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.appu17.DetailActivity
import com.example.appu17.R
import com.example.appu17.api.SaveFile
import domain.DetailComic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailAdapter(private var mDetailComic: DetailComic,private var mcontext:Context) : RecyclerView.Adapter<DetailAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.chapter_list_item,parent,false))
    }
    class ViewHolder(itemView :View):RecyclerView.ViewHolder(itemView) {
    }

    override fun getItemCount(): Int {
        return mDetailComic.data.returnData.chapter_list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DetailAdapter.ViewHolder, position: Int) {
        val chapter_name=holder.itemView.findViewById<TextView>(R.id.chapter_name)
        chapter_name.text=mDetailComic.data.returnData.chapter_list.get(position).index+"话"
        holder.itemView.setOnClickListener(View.OnClickListener {
            if(mDetailComic.data.returnData.chapter_list.get(position).vip_images!="0"){
                Toast.makeText(mcontext,"付费章节无法获取",Toast.LENGTH_SHORT).show()
            }
            else{
                val intent=Intent(mcontext,DetailActivity::class.java)
                intent.putExtra("chapter_Id",mDetailComic.data.returnData.chapter_list.get(position).chapter_id)
                //历史记录
                CoroutineScope(Dispatchers.Main).launch {
                    if(CoverManager.instances.search_CollectInfo(mDetailComic.data.returnData.comic.name)==null){
                        //还可以判断一下是不是存在图片了
                        val bitmap=SaveFile.getBitmap(mcontext,mDetailComic.data.returnData.comic.cover)
                        val pathname=SaveFile.onSaveBitmap(bitmap,mDetailComic.data.returnData.comic.name)
                        val collectInfo=CollectInfo(mDetailComic.data.returnData.comic.name,
                            pathname,
                            System.currentTimeMillis().toString(),
                            mDetailComic.data.returnData.comic.description,
                            mDetailComic.data.returnData.chapter_list.get(position).index,
                            mDetailComic.data.returnData.comic.comic_id,
                            mDetailComic.data.returnData.comic.theme_ids
                        )
                        CoverManager.instances.add(collectInfo)
                    }else{
                        val collectInfo=CoverManager.instances.getCollectInfo(mDetailComic.data.returnData.comic.name)
                        collectInfo.time=System.currentTimeMillis().toString()
                        CoverManager.instances.update(collectInfo)
                    }
                }
                mcontext.startActivity(intent)
            }

        })
    }


}