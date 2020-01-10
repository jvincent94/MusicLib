package com.jfernandez.musiclib.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jfernandez.musiclib.R;
import com.jfernandez.musiclib.data.model.TrackItem;

import java.util.Collections;
import java.util.List;

/**
 * Adapter class use to manage list of data in Recyclerview
 */
public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemViewHolder> {

	private final MainActivity mParentActivity;
	private final boolean mTwoPane;
	private List<TrackItem> trackItems = Collections.emptyList();
	private final OnItemClickListener listener;
	private Context context;

	/**
	 * Interface to handle item click method
	 */
	public interface OnItemClickListener {
		void onItemClick(TrackItem trackItem, int position);
	}

	/**
	 * Call constructor to instantiate the adapter class
	 * @param parent nneded to pass context
	 * @param twoPane needed to determine the display
	 * @param listener needed to pass listener
	 */
	public ItemRecyclerViewAdapter(MainActivity parent,
								   boolean twoPane,
								   OnItemClickListener listener){
		this.listener = listener;
		this.mParentActivity = parent;
		this.mTwoPane = twoPane;

	}

	/**
	 * Call method to pass arraylist data
	 * @param items needed to set data to be displayed
	 */
	public void setTracks(List<TrackItem> items){
		this.trackItems = items;
		notifyDataSetChanged();
	}


	/**
	 * Override method to display UI
	 */
	@NonNull
	@Override
	public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
		View view =
				LayoutInflater.from(parent.getContext())
						.inflate(R.layout.item_track, parent, false);
		return new ItemViewHolder(view,this.mParentActivity);
	}

	/**
	 * Override method to set each item of the arraylist
	 */
	@Override
	public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
		itemViewHolder.onBind(trackItems.get(i),listener,i);
	}

	/**
	 * Override method that returns the size of arraylist
	 * @return int / size o arraylist
	 */
	@Override
	public int getItemCount() {
		return trackItems.size();
	}
}
