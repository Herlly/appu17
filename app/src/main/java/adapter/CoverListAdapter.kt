package adapter

import GreenDao.CoverInfo
import GreenDao.CoverManager
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appu17.R
import com.example.appu17.api.ApiClient
import com.example.appu17.api.DiaLog
import com.example.appu17.api.GetData
import domain.DetailComic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class CoverListAdapter(private var mData:MutableList<CoverInfo>, private var mContext: Context): RecyclerView.Adapter<CoverListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.collect_item,parent,false))
    }
    var ischeck=false
    var mList:MutableList<Boolean>?= mutableListOf()
    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
        val cover=itemView.findViewById<ImageView>(R.id.collect_image)
        val collect_name=itemView.findViewById<TextView>(R.id.collect_name)
        val index=itemView.findViewById<TextView>(R.id.collect_index)
        val checkBox=itemView.findViewById<CheckBox>(R.id.cover_checkbox)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val file= mData.get(position).path.let { it->File(it) }
        Glide.with(mContext).load(file).into(holder.cover)
        holder.collect_name.text=mData.get(position).name
        holder.checkBox.isChecked=false
        if(CoverManager.instances.search_CollectInfo(mData.get(position).name)!= null){
            holder.index.text="读到第"+CoverManager.instances.search_CollectInfo(mData.get(position).name).index+"话"
        }else{
            holder.index.text="未阅读"
        }
        initList()

        if(ischeck){
            holder.checkBox.visibility=View.VISIBLE
        }else{
            holder.checkBox.visibility=View.GONE
        }

        holder.checkBox.setOnCheckedChangeListener{buttonView, isChecked ->
            if(isChecked){
                mList?.set(position,true)
            }else{
                mList?.set(position,false)
            }
        }

        holder.itemView.setOnClickListener(View.OnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                DiaLog.show(mContext,GetData.getDetailComic(mData.get(position).comic_Id))
            }
        })
    }
    fun edit(){
        ischeck=!ischeck
        notifyDataSetChanged()
    }
    fun initList(){
        mList?.clear()
        for(i in 0 until mData.size){
            mList?.add(false)
        }
    }
    fun delete(){
        for(i in 0 until mData.size){
            if(mList?.get(i)==true){
                CoverManager.instances.delete_CoverInfo(mData.get(i).name)
                notifyItemRemoved(i)
            }
        }
        update()
    }
    fun update(){
        mData.clear()
        mData.addAll(CoverManager.instances.searchCoverInfo())
        initList()
        notifyDataSetChanged()
    }

}