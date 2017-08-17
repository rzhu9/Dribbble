package cornelluniversity.dribbble.view.shot_details;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cornelluniversity.dribbble.R;
/**
 * Created by Ruihao on 8/16/17.
 */

public class ImageViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.shot_image)
    ImageView imageView;

    public ImageViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
