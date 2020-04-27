package adapter

import GreenDao.CollectInfo
import GreenDao.CoverManager
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.donkingliang.labels.LabelsView
import com.example.appu17.DetailActivity
import com.example.appu17.R
import com.example.appu17.api.ApiClient
import com.example.appu17.api.DiaLog
import com.example.appu17.api.GetData
import domain.DetailComic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CollectListAdapter(private var mData:MutableList<CollectInfo>,private var mContext:Context): RecyclerView.Adapter<CollectListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_history,parent,false))
    }
    lateinit var holder: ViewHolder
    var ischeckbox=false
    var mList:MutableList<Boolean>?= mutableListOf()
    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val ComicCover=itemView.findViewById<ImageView>(R.id.Comic_cover)
        val ComicName=itemView.findViewById<TextView>(R.id.comic_name)
        val Comic_intro=itemView.findViewById<TextView>(R.id.name_intro)
        val Comic_Tag=itemView.findViewById<LabelsView>(R.id.Comic_tag)
        val checkBox=itemView.findViewById<CheckBox>(R.id.checkbox)
        val index=itemView.findViewById<TextView>(R.id.index)
    }
    fun initList(){
        mList?.clear()
        for(i in 0 until mData.size){
            mList?.add(false)
        }
    }
    override fun getItemCount(): Int {
        return mData.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        this.holder=holder
        val path=mData.get(position).path
        if(ischeckbox){
            holder.checkBox.visibility=View.VISIBLE
        }else{
            holder.checkBox.visibility=View.GONE
        }
        Glide.with(mContext).load(path).into(holder.ComicCover)
        holder.ComicName.text=mData.get(position).name
        holder.Comic_intro.text=mData.get(position).info
        holder.Comic_Tag.setLabels(mData.get(position).tags)
        holder.checkBox.isChecked=false
        holder.index.text="阅读到第"+mData.get(position).index+"话"
        initList()

        holder.checkBox.setOnCheckedChangeListener{buttonView, isChecked ->
            if(isChecked){
                mList?.set(position,true)
            }else{
                mList?.set(position,false)
            }
        }

        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent= Intent(mContext, DetailActivity::class.java)
            CoroutineScope(Dispatchers.Main).launch {
                val po=mData.get(position).index.toInt()
                intent.putExtra("chapter_Id",GetData.getDetailComic(mData.get(position).comic_Id).data.returnData.chapter_list.get(po-1).chapter_id)
                mContext.startActivity(intent)
            }
        })
        holder.itemView.setOnLongClickListener(View.OnLongClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                DiaLog.show(mContext,GetData.getDetailComic(mData.get(position).comic_Id))
            }
            true
        })

    }
    fun delete(){
        for(i in 0 until mData.size){
            if(mList?.get(i)==true){
               CoverManager.instances.delete_CollectInfo(mData.get(i).name)
                notifyItemRemoved(i)
            }
        }
        update()
        Toast.makeText(mContext,"删除成功",Toast.LENGTH_SHORT).show()
    }
    fun edit(){
        ischeckbox=!ischeckbox
        notifyDataSetChanged()
    }
    fun update(){
        mData.clear()
        mData.addAll(CoverManager.instances.searchCollectInfo())
        initList()
        notifyDataSetChanged()
    }

}