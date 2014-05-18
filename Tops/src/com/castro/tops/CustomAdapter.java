package com.castro.tops;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

	private Context mContext;
	private List<Restaurant> mList;

	public CustomAdapter(Context context, List<Restaurant> list) {
		mContext = context;
		mList = list;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Restaurant getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final Restaurant item = mList.get(position);
		ViewHolder holder;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.row, parent, false);
			holder = new ViewHolder();

			holder.imageView = (ImageView) convertView
					.findViewById(R.id.image_front);
			holder.labelView = (TextView) convertView.findViewById(R.id.label);
			holder.addressView = (TextView) convertView
					.findViewById(R.id.address);
			holder.phoneView = (TextView) convertView.findViewById(R.id.phone);
			holder.priceView = (TextView) convertView.findViewById(R.id.price);
			holder.rankView = (TextView) convertView.findViewById(R.id.rank);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		ColorByPrice c = new ColorByPrice(item.getPrice());

		holder.imageView.setImageResource(item.getImage());
		holder.addressView.setText(item.getAddress());
		holder.phoneView.setText(item.getPhone());
		holder.priceView.setText("€");
		holder.priceView.setTextColor(c.getColorRank());
		holder.labelView.setText(Integer.toString(item.getRank()) + ". "
				+ item.getName());
		// holder.rankView.setText(Integer.toString(item.getRank()));

		return convertView;
	}

	private class ColorByPrice {
		private final int PRICE_COLOR_LOW = Color.parseColor("#79BD9A");
		private final int PRICE_COLOR_MID = Color.parseColor("#EDC951");
		private final int PRICE_COLOR_HIGH = Color.parseColor("#C11515");

		private final int PRICE_THRESHOLD_LOW = 5;
		private final int PRICE_THRESHOLD_MID = 9;

		private float price;

		public ColorByPrice(float price) {
			this.price = price;
		}

		public int getColorRank() {
			if (price < PRICE_THRESHOLD_LOW) {
				return PRICE_COLOR_LOW;
			} else if (price < PRICE_THRESHOLD_MID) {
				return PRICE_COLOR_MID;
			} else {
				return PRICE_COLOR_HIGH;
			}
		}
	}

	private static class ViewHolder {
		TextView labelView;
		TextView addressView;
		TextView phoneView;
		TextView priceView;
		TextView rankView;
		ImageView imageView;
	}

}
