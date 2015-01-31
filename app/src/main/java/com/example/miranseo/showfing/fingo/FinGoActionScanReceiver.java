package com.example.miranseo.showfing.fingo;

import com.example.miranseo.showfing.MainActivity;
import com.example.miranseo.showfing.R;

import java.util.HashMap;

import fingo.plugin.IExternalFingoAction;

public class FinGoActionScanReceiver extends AbstractActionScanReceiver
		implements IExternalFingoAction {

	@Override
	public String getClassName() {
		return MainActivity.class.getName();
	}

	@Override
	public String getDescription() {
		return context.getResources().getString(R.string.action_title);
	}

	@Override
	public String getPackageName() {
		return context.getPackageName();
	}

	@Override
	public String getIcon() {
		return "ic_launcher";
	}

	@Override
	public String getSubject() {
		return context.getResources().getString(R.string.action_title);
	}

	@Override
	public Type getType() {
		return Type.TOGGLE;
	}

	@Override
	public HashMap<State, String> getIcons() {
		HashMap<State, String> icons = new HashMap<State, String>();
		icons.put(State.DEFAULT, "ic_launcher");
		icons.put(State.TOGGLE_FIRST, "ic_launcher");
        icons.put(State.TOGGLE_SECOND, "ic_launcher");
        icons.put(State.TOGGLE_THIRD, "ic_launcher");

		return icons;
	}

}
