package com.myhome.android.cryptoapp.di

import androidx.work.ListenableWorker
import com.myhome.android.cryptoapp.data.workers.ChildWorkerFactory
import com.myhome.android.cryptoapp.data.workers.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshDataWorkerFactory(worker: RefreshDataWorker.Factory): ChildWorkerFactory
}