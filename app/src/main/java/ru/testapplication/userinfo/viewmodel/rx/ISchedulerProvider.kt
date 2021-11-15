package ru.testapplication.userinfo.viewmodel.rx

import io.reactivex.Scheduler

interface ISchedulerProvider {

    val ui: Scheduler

    val io: Scheduler
}
