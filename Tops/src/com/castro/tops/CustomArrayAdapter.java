//package com.castro.tops;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.graphics.drawable.StateListDrawable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentStatePagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.View.OnLongClickListener;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//public class CustomArrayAdapter extends ArrayAdapter<String> {
//	private Context mArrayContext;
//	private String[] mArrayLabels;
//	private FragmentManager mFragmentManager;
//	private int[] mArrayRanks;
//	private float[] mArrayPrices;
//	private int[] mArrayImages;
//	private String[] mArrayAdress;
//	private String[] mArrayPhone;
//
//	private String[] mArrayDescription;
//	private boolean mMiniMode = false;
//	private int mBackgroundColor = 0;
//	private StateListDrawable mStatesDrawable;
//	private int mSelectedPosition = -1;
//	private OnClickListener[] mButtonClickListener;
//	private OnClickListener[] mItemClickListener;
//	private List<Integer> mSelectedItems = new ArrayList<Integer>();
//
//	public CustomArrayAdapter(Context context, String[] labels, int[] ranks,
//			float[] prices, int[] images, String[] addresses, String[] phones,
//			FragmentManager manager) {
//		super(context, R.layout.list, labels);
//		mArrayContext = context;
//		mArrayLabels = labels;
//		mArrayRanks = ranks;
//		mArrayPrices = prices;
//		mArrayImages = images;
//		mArrayAdress = addresses;
//		mArrayPhone = phones;
//		mFragmentManager = manager;
//	}
//
//	public void setShape(StateListDrawable states) {
//		mStatesDrawable = states;
//	}
//
//	private void setViewpager(ViewPager pager, FragmentManager manager,
//			int image) {
//		ImageFragment imageFragment = new ImageFragment();
//		imageFragment.setImage(image);
//
//		CustomPagerAdapter adapter = new CustomPagerAdapter(manager);
//		adapter.addFragment(imageFragment);
//		adapter.addFragment(new BlankFragment());
//
//		pager.setAdapter(adapter);
//	}
//
//	private class CustomPagerAdapter extends FragmentStatePagerAdapter {
//
//		private ArrayList<Fragment> fragments;
//
//		public CustomPagerAdapter(FragmentManager fm) {
//			super(fm);
//			fragments = new ArrayList<Fragment>();
//		}
//
//		public void addFragment(Fragment fragment) {
//			fragments.add(fragment);
//		}
//
//		@Override
//		public Fragment getItem(int position) {
//			return fragments.get(position);
//		}
//
//		@Override
//		public int getCount() {
//			return fragments.size();
//		}
//	}
//
//	@Override
//	public View getView(final int position, View convertView, ViewGroup parent) {
//
//		// Set views
//		final LayoutInflater inflater = (LayoutInflater) mArrayContext
//				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		final View row = inflater.inflate(R.layout.row, parent, false);
//
//		final TextView label = (TextView) row.findViewById(R.id.label);
//		final TextView rank = (TextView) row.findViewById(R.id.rank);
//		final TextView price = (TextView) row.findViewById(R.id.price);
//		final TextView priceValue = (TextView) row
//				.findViewById(R.id.price_value);
//		final TextView address = (TextView) row.findViewById(R.id.address);
//		final TextView phone = (TextView) row.findViewById(R.id.phone);
//		// final ImageView image = (ImageView) row.findViewById(R.id.image);
//
//		// final ViewPager pager = (ViewPager) row.findViewById(R.id.pager);
//		// pager.setId(position + 1);
//		// setViewpager(pager, mFragmentManager, mArrayImages[position]);
//
//		// Label and description
//		label.setText(mArrayLabels[position]);
//		rank.setText(Integer.toString(mArrayRanks[position]));
//
//		int color = Color.WHITE;
//
//		if (mArrayPrices[position] <= 5) {
//			color = Color.parseColor("#79BD9A");
//		} else if (mArrayPrices[position] <= 9) {
//			color = Color.parseColor("#EDC951");
//		} else {
//			color = Color.parseColor("#C11515");
//		}
//
//		price.setTextColor(color);
//
//		address.setText(mArrayAdress[position]);
//		phone.setText(mArrayPhone[position]);
//
//		// image.setImageResource(mArrayImages[position]);
//
//		// Background and images
//		return row;
//	}
//
//	public void setBackgroundColor(int color) {
//		mBackgroundColor = color;
//	}
//
//	public void setCompactMode(boolean b) {
//		mMiniMode = b;
//	}
//
//	public void setSelectedPosition(int position) {
//		mSelectedPosition = position;
//	}
//
//	public void updateDescription(String[] description) {
//		this.mArrayDescription = description;
//	}
//
//	public void updateLabels(String[] labels) {
//		this.mArrayLabels = labels;
//	}
//
//	public String[] getContent() {
//		return mArrayLabels;
//	}
//
//	public void setButtonClickListener(OnClickListener[] listener) {
//		mButtonClickListener = listener;
//	}
//
//	private OnLongClickListener[] mLongListener;
//
//	public void setLongClickListener(OnLongClickListener[] listener) {
//		mLongListener = listener;
//	}
//
//	public void setItemClickListener(OnClickListener[] listener) {
//		mItemClickListener = listener;
//	}
//
//	private boolean isSelected(Integer position) {
//		if (!mSelectedItems.isEmpty()) {
//			for (Integer i : mSelectedItems) {
//				if (i == position) {
//					return true;
//				}
//			}
//		}
//		if (mSelectedPosition == position) {
//			return true;
//		}
//		return false;
//	}
//
//	public void addSelectedItem(Integer position) {
//		if (isSelected(position)) {
//			mSelectedItems.removeAll(Collections.singleton(position));
//			return;
//		}
//		mSelectedItems.add(position);
//	}
//
//	public List<Integer> getSelectedItems() {
//		return mSelectedItems;
//	}
//}
