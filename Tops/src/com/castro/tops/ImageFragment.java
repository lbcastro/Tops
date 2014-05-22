package com.castro.tops;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageFragment extends Fragment {
	private int mImageResource;
	private View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.row_image, container, false);
		return rootView;
	}

	@Override
	public void onResume() {
		super.onResume();

		ImageView image = (ImageView) rootView.findViewById(R.id.image);
		image.setImageResource(mImageResource);
	}

	public void setImage(int image) {
		mImageResource = image;
	}
}