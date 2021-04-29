package com.ayo.doggallery.di.module

import com.ayo.doggallery.ui.dogList.DogListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModules {
    @ContributesAndroidInjector(
        modules = [
            ViewModelModule::class
        ]
    )
    fun homeFragment(): DogListFragment

}