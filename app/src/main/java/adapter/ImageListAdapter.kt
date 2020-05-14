package adapter

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Environment
import android.provider.DocumentsContract
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.example.appu17.R
import domain.ComicImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.Okio
import java.io.File
import java.io.FileOutputStream
import java.util.*
import kotlin.Exception

class ImageListAdapter(val mComicImage: ComicImage,val mContext: Context,val mViewPager2: ViewPager2) :RecyclerView.Adapter<ImageListAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_list_item,parent,false))
    }
    lateinit var mDiaLog:Dialog
    override fun getItemCount(): Int {
        return mComicImage.data.returnData.image_list.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val image_list_item=holder.itemView.findViewById<ImageView>(R.id.image_list_item)
        Glide.with(mContext)
            .load(mComicImage.data.returnData.image_list[position].location).into(image_list_item)

        holder.itemView.setOnClickListener(View.OnClickListener {
            show();
            val list_way=mDiaLog.findViewById<TextView>(R.id.list_way)
            list_way.text="改变滑动方向"
            list_way.setOnClickListener(View.OnClickListener {
                if(mViewPager2.orientation==ViewPager2.ORIENTATION_HORIZONTAL){
                    mViewPager2.orientation=ViewPager2.ORIENTATION_VERTICAL
                    Toast.makeText(mContext,"卷纸阅读",Toast.LENGTH_SHORT).show()
                    notifyDataSetChanged()
                }else{
                    mViewPager2.orientation=ViewPager2.ORIENTATION_HORIZONTAL
                    Toast.makeText(mContext,"水平阅读",Toast.LENGTH_SHORT).show()
                    notifyDataSetChanged()
                }
            })
        })
    }

    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
    }
    private fun show(){
         mDiaLog= Dialog(mContext,R.style.BottomDialog)
        val root=LayoutInflater.from(mContext).inflate(R.layout.list_way,null)
        mDiaLog.setContentView(root)
        val dialogWindow=mDiaLog.window
        dialogWindow?.setGravity(Gravity.CENTER)
        val lp=dialogWindow?.attributes
        lp?.x=0
        lp?.y=0
        lp?.width=mContext.resources.displayMetrics.widthPixels
        root.measure(0,0)
        lp?.height=root.measuredHeight
        lp?.alpha=9f
        dialogWindow?.attributes=lp
        mDiaLog.show()
    }
}