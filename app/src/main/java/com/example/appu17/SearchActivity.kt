package com.example.appu17

import adapter.SearchListAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appu17.api.GetData
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchActivity : AppCompatActivity(),TextView.OnEditorActionListener {
    lateinit var Rearch_List: RecyclerView
    lateinit var adapter: SearchListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        cancle.setOnClickListener(View.OnClickListener {
            finish()
        })
        search.setOnEditorActionListener(this)
        Rearch_List=this.findViewById(R.id.search_list)
        Rearch_List.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if(event != null && KeyEvent.KEYCODE_ENTER == event.keyCode && KeyEvent.ACTION_DOWN == event.action){
            val name:String=search.text.toString()
            CoroutineScope(Dispatchers.Main).launch {
                adapter=SearchListAdapter(GetData.getSearchComic(name).data.returnData.comics,this@SearchActivity)
                Rearch_List.adapter=adapter
            }
        }
        return true
    }

}
