package com.example.miranseo.showfing.fingo;

import android.content.Intent;

import com.example.miranseo.showfing.FingoActivity;
import com.example.miranseo.showfing.MainActivity;

public class FinGoActionReceiver extends AbstractActionReceiver {


	@Override
	public void action1() {
        Intent intent = new Intent(context, FingoActivity.class );
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }




    @Override
	protected String getClassName() {
		return MainActivity.class.getName();
	}
}
