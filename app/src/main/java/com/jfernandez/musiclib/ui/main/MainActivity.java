package com.jfernandez.musiclib.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.jfernandez.musiclib.MusicLibApp;
import com.jfernandez.musiclib.R;
import com.jfernandez.musiclib.data.DataManager;
import com.jfernandez.musiclib.data.model.TrackItem;
import com.jfernandez.musiclib.ui.base.BaseActivity;
import com.jfernandez.musiclib.ui.details.ItemDetailActivity;
import com.jfernandez.musiclib.ui.details.ItemDetailFragment;

import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * But for now it is set only to landscape orientation.
 */
public class MainActivity extends BaseActivity implements MainMvpView{

	private boolean mTwoPane;
	private ItemRecyclerViewAdapter itemRecyclerViewAdapter;
	private RecyclerView recyclerView;
	private DataManager dataManager;
	private MainPresenter mainPresenter;
	private CoordinatorLayout coordinatorLayout;

	/**
	 * Ovveride method that handles UI widget instantiation
	 * and other necessary class
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_list);

		//instantiate datamanager and presenter
		dataManager = ((MusicLibApp) getApplication()).getDataManager();
		mainPresenter = new MainPresenter(dataManager, this);
		mainPresenter.onAttach(this);

		//instantiate toolbar
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		toolbar.setTitle(getTitle());

		//check if layout is not null
		if (findViewById(R.id.item_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-w900dp).
			// If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;
		}

		//initialize recyclerview widget and Coordinator layout
		recyclerView = (RecyclerView)findViewById(R.id.item_list);
		coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);

		//call Music Library API from iTunes
		mainPresenter.getTracks();
	}


	/**
	 * This method will display the data from API call
	 * @param trackItems needed to display data in recyclerview
	 */
	@Override
	public void displayTrackList(List<TrackItem> trackItems) {
		itemRecyclerViewAdapter = new ItemRecyclerViewAdapter(this, mTwoPane, new ItemRecyclerViewAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(TrackItem trackItem, int position) {
				if (mTwoPane) {
					Bundle arguments = new Bundle();
					arguments.putString(ItemDetailFragment.TRACK_TITLE, trackItem.getTrackName());
					arguments.putString(ItemDetailFragment.TRACK_DESCRIPTION, trackItem.getLongDescription());
					arguments.putString(ItemDetailFragment.TRACK_IMG_URL, trackItem.getTrackViewUrl());
					ItemDetailFragment fragment = new ItemDetailFragment();
					fragment.setArguments(arguments);
					getSupportFragmentManager().beginTransaction()
							.replace(R.id.item_detail_container, fragment)
							.commit();
				} else {
					Intent intent = new Intent(MainActivity.this, ItemDetailActivity.class);
					intent.putExtra(ItemDetailActivity.TRACK_TITLE, trackItem.getTrackName());
					intent.putExtra(ItemDetailActivity.TRACK_DESCRIPTION, trackItem.getLongDescription());
					intent.putExtra(ItemDetailActivity.TRACK_IMG_URL, trackItem.getTrackViewUrl());

					startActivity(intent);
				}
			}
		});
		itemRecyclerViewAdapter.setTracks(trackItems);
		recyclerView.setAdapter(itemRecyclerViewAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

	}

	/**
	 * This method is use to display toast message from API call result
	 * @param msg needed to display in Snackbar widget
	 */
	@Override
	public void showSnackBarMessage(String msg) {
		final Snackbar snackBar = Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_LONG);
		snackBar.setAction("DISMISS", new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Dismiss Snackbar
				snackBar.dismiss();
			}
		});
		snackBar.show();
	}

}
