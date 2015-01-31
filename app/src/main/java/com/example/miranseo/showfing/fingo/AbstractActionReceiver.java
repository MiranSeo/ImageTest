package com.example.miranseo.showfing.fingo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.WindowManager;

import fingo.plugin.IExternalFingoAction;

public abstract class AbstractActionReceiver extends BroadcastReceiver
		implements IExternalAction {
	protected Context context;
	protected WindowManager window;

	protected abstract String getClassName();

	@Override
	public void onReceive(Context context, Intent intent) {
		String actionKey = getClassName() + '@' + context.getPackageName();
		this.context = context;
		int iState = intent.getIntExtra(actionKey, -1);
		if (iState < 0)
			return;

		window = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);

		IExternalFingoAction.State state = IExternalFingoAction.State.values()[iState];

		switch (state) {
		case DEFAULT:
		case TOGGLE_FIRST:
			action1();
			break;

		}
		
		FingoApplication.getInstance().setCurrentState(state);
	}

}
