package com.luseen.ribble.di.component

import com.luseen.ribble.di.module.ActivityModule
import com.luseen.ribble.di.scope.PerActivity
import com.luseen.ribble.presentation.screens.home.HomeActivity
import com.luseen.ribble.presentation.screens.shot.ShotFragment
import dagger.Component

/**
 * Created by Chatikyan on 31.07.2017.
 */

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(homeActivity: HomeActivity)

    fun inject(shotFragment: ShotFragment)
}