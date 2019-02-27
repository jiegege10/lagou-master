package com.lagou.hyj.lagou

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.CoordinatorLayout
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageView
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.goach.mdstudy.RecycAdapter
import com.gyf.barlibrary.ImmersionBar
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.lagou.hyj.lagou.behavior.HeaderBehavior
import com.lagou.hyj.lagou.behavior.SearchBehavior
import com.youth.banner.Banner
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.onClick
import java.util.*

/**
 * Created by Administrator on 2019/2/12.
 */
class MainActivity : AppCompatActivity() {


    private lateinit var mHeaderBehavior: HeaderBehavior
    private lateinit var mSearchBehavior: SearchBehavior
    private val mDatas = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        var mImmersionBar = ImmersionBar.with(this).statusBarDarkFont(true, 0.2f).titleBar(relativeLayout)
        mImmersionBar.init()
        mDatas.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1549880138318&di=549b045c3f99b4d3bdb55a176a210974&imgtype=0&src=http%3A%2F%2Fpic17.nipic.com%2F20111021%2F8633866_210108284151_2.jpg")
        mDatas.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1549880165679&di=dcf8eb88189a637dba12981fd6aeef4f&imgtype=0&src=http%3A%2F%2Fpic21.nipic.com%2F20120519%2F5454342_154115399000_2.jpg")
        mHeaderBehavior = (find<NestedScrollView>(R.id.fl_head).layoutParams as CoordinatorLayout.LayoutParams).behavior as HeaderBehavior
        mSearchBehavior = (find<RelativeLayout>(R.id.relativeLayout).layoutParams as CoordinatorLayout.LayoutParams).behavior as SearchBehavior
        findViewById<Banner>(R.id.iv_head).setImageLoader(HomeImageLoader()).setImages(mDatas).start()
        recyclerview.adapter = RecycAdapter(this)
        recyclerview.layoutManager = LinearLayoutManager(this)
        materialRefreshLayout.
        materialRefreshLayout.setOnRefreshListener {
            materialRefreshLayout.finishRefresh(2000)
        }

        radio.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.radio1->{

                }
                R.id.radio2->{}
            }
        }
        mSearchBehavior.setOnListener(object : SearchBehavior.OnListener {
            override fun onXListener(force: Boolean) {
                recyclerview.setPullRefreshEnabled(force)
            }

            override fun onListener(force: Boolean) {

                materialRefreshLayout.setEnableRefresh(force)
            }
        })
        materialRefreshLayout.setEnableLoadmore(false)
        materialRefreshLayout.setDisableContentWhenRefresh(true)
        recyclerview.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onLoadMore() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onRefresh() {
                Handler().postDelayed({
                    recyclerview.refreshComplete()
                }, 3000)
            }

        })

        ivArrow.onClick {
            openHeader()
        }
    }
    //打开头部
    private fun openHeader() {
        if (mHeaderBehavior.isClose()) {
//            recyclerview.scrollTo(0, 0)
            recyclerview.smoothScrollToPosition(0)
            mHeaderBehavior.scrollToOpen()
            recyclerview.refreshComplete()
        }
    }

    override fun onBackPressed() {
        if (mHeaderBehavior.isClose()) {
            recyclerview.smoothScrollToPosition(0)
            mHeaderBehavior.scrollToOpen()
            recyclerview.refreshComplete()
        } else {
            super.onBackPressed()
        }

    }
    //加班banner图片
    class HomeImageLoader : ImageLoader() {
        override fun displayImage(context: Context, path: Any, imageView: ImageView) {
            imageView.scaleType = ImageView.ScaleType.FIT_XY
            Glide.with(context).asBitmap().load(path.toString()).into(imageView)
        }
    }

}