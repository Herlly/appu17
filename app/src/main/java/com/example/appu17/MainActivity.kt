package com.example.appu17

import Fragment.CollectFragment
import Fragment.CovertFragment
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.appu17.api.Internet
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    lateinit var fragment1:CovertFragment
    lateinit var fragment2: CollectFragment
    val frag_list:MutableList<Fragment>?= mutableListOf()
    private val REQUEST_EXTERNAL_STORAGE :Int= 1
    private val PERMISSIONS_STORAGE= arrayOf("android.permission.READ_EXTERNAL_STORAGE","android.permission.WRITE_EXTERNAL_STORAGE")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.setTitle("有妖气（仿）")
        setSupportActionBar(toolbar)
        try {
            val permission= ActivityCompat.checkSelfPermission(this,
                "android.permission.WRITE_EXTERNAL_STORAGE")
            if(permission!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE)
            }
        }catch (e:Exception){
            throw e
        }

        //切换对应的按钮
        vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                bottomNavigationView.menu.getItem(position).isChecked=true
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })

        //删除
        delete.setOnClickListener(View.OnClickListener {
            when(vp.currentItem){
                1->{
                    fragment2.delete()
                    fragment2.edit()
                }
                0->{
                    fragment1.delete()
                    fragment1.edit()
                }
            }
            edit_layout.visibility=View.GONE
            vp.isUserInputEnabled=true
        })
        inFragment()
    }
    fun inFragment(){
        fragment1=CovertFragment.newInstance()
        fragment2=CollectFragment.newInstance()
        frag_list?.add(fragment1)
        frag_list?.add(fragment2)
        vp.adapter=object :FragmentStateAdapter(this){
            override fun getItemCount(): Int {
               return frag_list!!.size
            }

            override fun createFragment(position: Int): Fragment {
                return frag_list!![position]
            }

        }

        vp.isUserInputEnabled=true
        vp.setCurrentItem(0,false)
        bottomNavigationView.setOnNavigationItemSelectedListener{item ->
            when(item.itemId){
                R.id.tab_collect->{
                    vp.setCurrentItem(0,false)
                }
                R.id.tab_history->{
                    vp.setCurrentItem(1,false)
                }
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.rank->{
                if(Internet.isNetworkConnected(this)){
                    startActivity(Intent(this,RankListActivity::class.java))
                }else{
                    Toast.makeText(this,"当前无网络连接",Toast.LENGTH_SHORT).show()
                }
            }
            R.id.edit->{
                if(edit_layout.visibility==View.VISIBLE){
                    edit_layout.visibility=View.GONE
                    vp.isUserInputEnabled=true
                }else{
                    edit_layout.visibility=View.VISIBLE
                    vp.isUserInputEnabled=false
                }
                when(vp.currentItem){
                    0->fragment1.edit()
                    1->fragment2.edit()
                }
            }
            R.id.search->{
                if(Internet.isNetworkConnected(this)){
                    startActivity(Intent(this,SearchActivity::class.java))
                }else{
                    Toast.makeText(this,"当前无网络连接",Toast.LENGTH_SHORT).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        fragment1.update()
        fragment2.update()
    }
}
