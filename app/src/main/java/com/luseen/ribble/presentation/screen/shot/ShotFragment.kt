package com.luseen.ribble.presentation.screen.shot


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luseen.logger.Logger
import com.luseen.ribble.R
import com.luseen.ribble.presentation.adapter.ShotRecyclerViewAdapter
import com.luseen.ribble.presentation.adapter.listener.ShotClickListener
import com.luseen.ribble.presentation.base_mvp.base.BaseFragment
import com.luseen.ribble.presentation.model.Shot
import com.luseen.ribble.presentation.screen.shot_detail.ShotDetailActivity
import kotlinx.android.synthetic.main.fragment_shot.*
import javax.inject.Inject


class ShotFragment : BaseFragment<ShotContract.View, ShotContract.Presenter>(),
        ShotContract.View, ShotClickListener {

    @Inject
    protected lateinit var shotPresenter: ShotPresenter

    override fun initPresenter(): ShotContract.Presenter = shotPresenter

    private var shotList: MutableList<Shot> = arrayListOf()

    private var recyclerAdapter: ShotRecyclerViewAdapter? = null

    companion object {
        const val SHOT_EXTRA_ID = "shot_extra_id"
        fun newInstance(): ShotFragment {
            return ShotFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_shot, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateAdapter(shotList)
    }

    private fun updateAdapter(shotList: MutableList<Shot>) {
        if (shotList.size > 0)
            recyclerAdapter?.update(shotList) ?: setUpRecyclerView(shotList)
    }

    private fun setUpRecyclerView(shotList: MutableList<Shot>) {
        recyclerAdapter = ShotRecyclerViewAdapter(shotList, this)//TODO
        shotRecyclerView.layoutManager = LinearLayoutManager(activity)
        shotRecyclerView.adapter = recyclerAdapter
    }

    override fun injectDependencies() {
        activityComponent.inject(this)
    }

    override fun onShotClicked(shot: Shot) {
        val startIntent = ShotDetailActivity.startIntent(activity, shot)
        startActivity(startIntent)
    }

    override fun showLoading() {
        //TODO("not implemented")
    }

    override fun hideLoading() {
        //TODO("not implemented")
    }

    override fun showError() {
        ///TODO("not implemented")
    }

    override fun onShotListReceive(shotList: MutableList<Shot>) {
        Logger.log("onShotListReceive ${shotList.size}")
        this.shotList = shotList
        updateAdapter(shotList)
    }
}
