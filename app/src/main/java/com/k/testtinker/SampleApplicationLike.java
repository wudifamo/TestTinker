package com.k.testtinker;


import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;

import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

@DefaultLifeCycle(application = "com.k.testtinker.SampleApplication",
		flags = ShareConstants.TINKER_ENABLE_ALL,
		loadVerifyFlag = false)
public class SampleApplicationLike extends DefaultApplicationLike {

	public SampleApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag,
								 long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
		super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
	}

	/**
	 * install multiDex before install tinker
	 * so we don't need to put the tinker lib classes in the main dex
	 *
	 * @param base
	 */
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	public void onBaseContextAttached(Context base) {
		super.onBaseContextAttached(base);
		//you must install multiDex whatever tinker is installed!
		MultiDex.install(base);
		TinkerInstaller.install(this);
	}

}
