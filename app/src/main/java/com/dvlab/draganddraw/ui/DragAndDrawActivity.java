package com.dvlab.draganddraw.ui;

import android.support.v4.app.Fragment;

public class DragAndDrawActivity extends SingleFragmentActivity {

    public static final String TAG = DragAndDrawActivity.class.getSimpleName();

    @Override
    protected Fragment createFragment() {
        return new DragAndDrawFragment();
    }

}
