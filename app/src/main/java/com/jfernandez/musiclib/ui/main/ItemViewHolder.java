package com.jfernandez.musiclib.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jfernandez.musiclib.R;
import com.jfernandez.musiclib.data.model.TrackItem;


/**
 * View holder class for Adapter, use to display items in List
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {

	private TextView songtitle;
	private TextView songgenre;
	private TextView songprice;
	private ImageView thumbnail;
	private Context context;

	/**
	 * Constructor use to initialize in another class
	 * @param itemView needed for instantiating widgets
	 * @param context needed for passing context variable
	 */
	public ItemViewHolder(@NonNull View itemView, Context context) {
		super(itemView);
		this.context = context;
		songtitle = (TextView)itemView.findViewById(R.id.title);
		songgenre = (TextView)itemView.findViewById(R.id.genre);
		songprice = (TextView)itemView.findViewById(R.id.price);
		thumbnail = (ImageView)itemView.findViewById(R.id.list_image);
	}

	/**
	 * Call method to access widgets
	 * @param trackItem needed to access TrackItem getters and setters
	 * @param listener needed to pass listener on ItemClick
	 * @param position needed to pass item position
	 */
	public void onBind(final TrackItem trackItem,
					   final ItemRecyclerViewAdapter.OnItemClickListener listener,
					   final int position){
		songtitle.setText(trackItem.getTrackName());
		songgenre.setText(trackItem.getPrimaryGenreName());
		songprice.setText("$"+trackItem.getTrackPrice());
		itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				listener.onItemClick(trackItem, position);
			}
		});

		Glide.with(context).load(trackItem.artworkUrl100).apply(
				RequestOptions
						.placeholderOf(R.drawable.ic_library_music)
						.error(R.drawable.ic_library_music))
				.into(thumbnail);

	}
}
