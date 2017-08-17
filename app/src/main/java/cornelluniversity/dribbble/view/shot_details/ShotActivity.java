package cornelluniversity.dribbble.view.shot_details;

import android.support.v4.app.Fragment;

import cornelluniversity.dribbble.view.base.SingFragmentActivity;



/**
 * Created by Ruihao on 8/16/17.
 */

public class ShotActivity extends SingFragmentActivity {

    public static final String KEY_SHOT_TITLE = "title";

    @Override
    protected Fragment newFragment() {
        return ShotFragment.newInstance(getIntent().getExtras());
    }

    @Override
    protected String getActivityTitle() {
        return getIntent().getStringExtra(ShotActivity.KEY_SHOT_TITLE);
    }
}