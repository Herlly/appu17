package com.example.appu17

import adapter.RankListAdapter
import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.appu17.api.ApiClient
import com.example.appu17.api.GetData
import domain.RankComicList
import kotlinx.android.synthetic.main.activity_rank_list.*
import kotlinx.coroutines.*

class RankListActivity : AppCompatActivity(),SwipeRefreshLayout.OnRefreshListener {
    lateinit var recyclerView: RecyclerView
    lateinit var mcontext:Context
    lateinit var adapter: RankListAdapter
    var page:Int=1
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mcontext=this
        setContentView(R.layout.activity_rank_list)
        recyclerView=this.findViewById(R.id.Rank_list)
        recyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        CoroutineScope(Dispatchers.Main).launch {
            adapter=RankListAdapter(GetData.getRankComicList(page).data.returnData.comics,mcontext)
            recyclerView.adapter=adapter
        }

        Rank_Refresh.setColorSchemeColors(R.color.colorPrimary)
        Rank_Refresh.setOnRefreshListener(this)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var lastItemNum:Int=0
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager=recyclerView.layoutManager as LinearLayoutManager
                lastItemNum=layoutManager.findLastVisibleItemPosition()
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState==RecyclerView.SCROLL_STATE_IDLE&& lastItemNum +1==adapter.itemCount){
                    page++
                    if(page<=7){
                        CoroutineScope(Dispatchers.Main).launch {
                            adapter.addData(GetData.getRankComicList(page))
                        }
                    }else{
                        Toast.makeText(this@RankListActivity,"已加载全部",Toast.LENGTH_SHORT).show()
                    }
                }

            }
        })

    }


    override fun onRefresh() {
        CoroutineScope(Dispatchers.Main).launch {
            page=1
            adapter.Refresh(GetData.getRankComicList(page))
            adapter.notifyDataSetChanged()
            Toast.makeText(this@RankListActivity,"刷新成功",Toast.LENGTH_SHORT).show()
        }
        Rank_Refresh.isRefreshing=false

    }

}
