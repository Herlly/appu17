package Fragment

import GreenDao.CoverManager
import adapter.CollectListAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.example.appu17.R
import kotlinx.android.synthetic.main.fragment_collect.*


class CollectFragment : Fragment(),SwipeRefreshLayout.OnRefreshListener {
    lateinit var recyclerView:RecyclerView
     var adapter:CollectListAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_collect, container, false)
        recyclerView=view.findViewById(R.id.collect_list)
        val collect_refresh=view.findViewById<SwipeRefreshLayout>(R.id.collect_refresh)
        collect_refresh.setOnRefreshListener(this)
        recyclerView.layoutManager=LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        adapter=CollectListAdapter(CoverManager.instances.searchCollectInfo(),this.context!!)
        recyclerView.adapter=adapter
        return view
    }
    fun edit(){
        adapter?.edit()
        adapter?.notifyDataSetChanged()
    }
    fun delete(){
        adapter?.delete()
        adapter?.notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        fun newInstance() = CollectFragment()
    }

    override fun onRefresh() {
        adapter?.update()
        collect_refresh.isRefreshing=false
    }
}
