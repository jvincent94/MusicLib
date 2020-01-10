package com.jfernandez.musiclib.ui.details;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jfernandez.musiclib.R;
import com.jfernandez.musiclib.ui.base.BaseFragment;
import com.jfernandez.musiclib.ui.main.MainActivity;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link MainActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends BaseFragment {

	public static final String TRACK_IMG_URL = "url";
	public static final String TRACK_DESCRIPTION = "desc";
	public static final String TRACK_TITLE = "title";


	private String url;
	private String desc;
	private String title;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ItemDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(TRACK_IMG_URL) && getArguments().containsKey(TRACK_DESCRIPTION) && getArguments().containsKey(TRACK_TITLE)) {

			url = getArguments().getString(TRACK_IMG_URL);
			desc = getArguments().getString(TRACK_DESCRIPTION);
			title = getArguments().getString(TRACK_TITLE);

			Activity activity = this.getActivity();
			CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
			if (appBarLayout != null) {
				appBarLayout.setTitle(title);
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.item_detail, container, false);
		((TextView) rootView.findViewById(R.id.tv_welcome_message)).setVisibility(View.VISIBLE);

		// Show the description as text in a TextView.
		if (desc != null) {
			((TextView) rootView.findViewById(R.id.tv_welcome_message)).setVisibility(View.GONE);
			((TextView) rootView.findViewById(R.id.item_detail)).setText(desc);
		} else {
			((TextView) rootView.findViewById(R.id.tv_welcome_message)).setVisibility(View.GONE);
			((TextView) rootView.findViewById(R.id.item_detail)).setText("Description not available");
		}

		return rootView;
	}
}
