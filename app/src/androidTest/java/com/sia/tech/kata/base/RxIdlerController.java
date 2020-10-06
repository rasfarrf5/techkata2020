package com.sia.tech.kata.base;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;

import com.squareup.rx2.idler.Rx2Idler;

import java.util.Collection;

import io.reactivex.Scheduler;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

public class RxIdlerController {

    private IdlingResource[] idlingResources = null;

    public static void initialize() {
        RxJavaPlugins.setInitIoSchedulerHandler(Rx2Idler.create("RxJava 2.x Io Scheduler"));
        Scheduler io = Schedulers.io(); //force initialization
        if (io == null || !isInitialized()) {
            throw new RuntimeException("Initialization failed");
        }
    }

    public void unregisterAll() {
        idlingResources = getIdlingResources();
        IdlingRegistry.getInstance().unregister(idlingResources);
    }

    public void reregisterAll() {
        IdlingRegistry.getInstance().register(idlingResources);
    }

    private IdlingResource[] getIdlingResources() {
        Collection<IdlingResource> resources = IdlingRegistry.getInstance().getResources();
        return resources.toArray(new IdlingResource[]{});
    }

    public static boolean isInitialized() {
        return !IdlingRegistry.getInstance().getResources().isEmpty();
    }

}
