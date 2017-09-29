package com.lab603.picencyclopedias.PicPhotoSimple;import android.content.Context;import android.net.Uri;import android.support.v7.widget.RecyclerView;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.ImageView;import android.widget.RelativeLayout;import com.bumptech.glide.Glide;import com.lab603.picencyclopedias.R;import com.lab603.picencyclopedias.pickphotoview.util.PickConfig;import com.lab603.picencyclopedias.pickphotoview.util.PickUtils;import java.util.ArrayList;/** * Created by EdwardZhang on 2017/9/29. */public class SampleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {    private ArrayList<String> imagePaths;    private Context context;    public SampleAdapter(Context c, ArrayList<String> imagePaths) {        this.context = c;        this.imagePaths = imagePaths;    }    @Override    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {        return new GridImageViewHolder(LayoutInflater.from(context).inflate(R.layout.pick_item_grid_layout, parent, false));    }    @Override    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {        if(imagePaths != null) {            String path = imagePaths.get(position);            GridImageViewHolder gridImageViewHolder = (GridImageViewHolder) holder;            gridImageViewHolder.bindItem(path);        }    }    @Override    public int getItemCount() {        if(imagePaths != null) {            return imagePaths.size();        }else {            return 0;        }    }    public void updateData(ArrayList<String> paths) {        imagePaths = paths;        notifyDataSetChanged();    }    // ViewHolder    private class GridImageViewHolder extends RecyclerView.ViewHolder {        private ImageView gridImage, selectImage;        private int scaleSize;        GridImageViewHolder(View itemView) {            super(itemView);            gridImage = (ImageView) itemView.findViewById(R.id.iv_grid);            selectImage = (ImageView) itemView.findViewById(R.id.iv_select);            selectImage.setVisibility(View.GONE);            int screenWidth = PickUtils.getInstance(context).getWidthPixels();            int space = PickUtils.getInstance(context).dp2px(PickConfig.ITEM_SPACE);            scaleSize = (screenWidth - (4 + 1) * space) / 4;            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) gridImage.getLayoutParams();            params.width = scaleSize;            params.height = scaleSize;        }        void bindItem(final String path) {            Glide.with(context).load(Uri.parse("file://" + path)).into(gridImage);        }    }}