package cornelluniversity.dribbble.view.base;

import android.support.v7.widget.RecyclerView;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by Ruihao on 8/16/17.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = space;
        outRect.left = space;
        outRect.right = space;

        if(parent.getChildAdapterPosition(view) == 0) {
            outRect.top = space;
        }
    }
}