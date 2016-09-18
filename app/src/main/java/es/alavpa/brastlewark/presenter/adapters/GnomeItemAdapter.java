package es.alavpa.brastlewark.presenter.adapters;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.alavpa.brastlewark.R;
import es.alavpa.brastlewark.interactors.model.GnomeItem;
import es.alavpa.brastlewark.presenter.App;

/**
 * Created by alavpa on 14/9/16.
 */
public class GnomeItemAdapter extends RecyclerView.Adapter<GnomeItemAdapter.GnomeItemHolder> {

    List<GnomeItem> items;
    GnomeItemAdapterOnClickListener onClickListener;

    public GnomeItemAdapter(List<GnomeItem> items, GnomeItemAdapterOnClickListener onClickListener){
        this.items = items;
        this.onClickListener = onClickListener;
    }

    @Override
    public GnomeItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(App.get()).inflate(R.layout.layout_item, parent, false);
        return new GnomeItemHolder(v);
    }

    @Override
    public void onBindViewHolder(GnomeItemHolder holder, int position) {
        GnomeItem item = items.get(position);
        holder.load(item, onClickListener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class GnomeItemHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_thumbnail)
        ImageView iv_thumbnail;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_age)
        TextView tv_age;
        @BindView(R.id.tv_weight)
        TextView tv_weight;
        @BindView(R.id.tv_height)
        TextView tv_height;
        @BindView(R.id.tv_hair)
        TextView tv_hair;
        @BindView(R.id.ll_professions)
        LinearLayout ll_professions;
        @BindView(R.id.tv_professions)
        TextView tv_professions;

        public GnomeItemHolder(View view){

            super(view);
            ButterKnife.bind(this,view);
        }

        public void load(final GnomeItem item, final GnomeItemAdapterOnClickListener onClickListener){

            tv_name.setText(item.getName());

            Glide.with(App.get())
                    .load(item.getThumbnail())
                    .asBitmap()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new BitmapImageViewTarget(iv_thumbnail) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(App.get().getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    iv_thumbnail.setImageDrawable(circularBitmapDrawable);
                }
            });

            tv_age.setText(item.getAge());
            tv_weight.setText(item.getWeight());
            tv_height.setText(item.getHeight());
            tv_hair.setText(item.getHairColor());

            if(TextUtils.isEmpty(item.getProfessions())){
                ll_professions.setVisibility(View.GONE);
            }else{
                ll_professions.setVisibility(View.VISIBLE);
                tv_professions.setText(item.getProfessions());
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onClick(item);
                }
            });

        }
    }
}
