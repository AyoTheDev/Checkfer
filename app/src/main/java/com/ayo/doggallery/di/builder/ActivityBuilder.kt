package com.ayo.doggallery.di.builder

import com.ayo.doggallery.di.module.FragmentModules
import com.ayo.doggallery.ui.MainActivity
import com.ayo.doggallery.ui.dogList.DogListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilder {
    @ContributesAndroidInjector(
        modules = [FragmentModules::class]
    )
    fun contributeActivity(): MainActivity
}
