package Fragment

import GreenDao.CoverManager
import adapter.CoverListAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.example.appu17.R
import kotlinx.android.synthetic.main.fragment_cover.*

class CovertFragment : Fragment(),SwipeRefreshLayout.OnRefreshListener {
    lateinit var recyclerView:RecyclerView
    var adapter:CoverListAdapter?=null
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_cover, container, false)
        val refresh=view.findViewById<SwipeRefreshLayout>(R.id.cover_refresh)
        refresh.setOnRefreshListener(this)
        recyclerView=view.findViewById<RecyclerView>(R.id.cover_list)
        recyclerView.layoutManager=GridLayoutManager(activity,3);
        adapter= this.context?.let { CoverListAdapter(CoverManager.instances.searchCoverInfo(), it) }
        recyclerView.adapter=adapter
        return view
    }
    fun edit(){
        adapter?.edit()
    }
    fun delete(){
        adapter?.delete()
    }
    companion object {
        fun newInstance() =
            CovertFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onRefresh() {
        adapter?.update()
        cover_refresh.isRefreshing=false
    }
    fun update(){
        adapter?.update()
    }
}
