package com.castro.tops;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

	private class ColorByPrice {
		private final int PRICE_COLOR_LOW = color(R.color.price_low_transparent);
		private final int PRICE_COLOR_MID = color(R.color.price_mid_transparent);
		private final int PRICE_COLOR_HIGH = color(R.color.price_high_transparent);

		private final int PRICE_THRESHOLD_LOW = 5;
		private final int PRICE_THRESHOLD_MID = 9;

		private float mPrice;

		public ColorByPrice(float price) {
			this.mPrice = price;
		}

		private int color(int color) {
			return mContext.getResources().getColor(color);
		}

		public int getColorRank() {
			if (mPrice < PRICE_THRESHOLD_LOW) {
				return PRICE_COLOR_LOW;
			} else if (mPrice < PRICE_THRESHOLD_MID) {
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
		TextView voteUpView;
		ImageView imageView;
		ImageView mapView;
	}

	private CustomAdapterInterface mInterface;
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
	public View getView(final int position, View view, ViewGroup parent) {

		final Restaurant item = mList.get(position);
		ViewHolder holder;

		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.row, parent, false);
			holder = new ViewHolder();

			holder.imageView = (ImageView) view.findViewById(R.id.image_front);
			holder.mapView = (ImageView) view.findViewById(R.id.image_back);
			holder.labelView = (TextView) view.findViewById(R.id.label);
			holder.addressView = (TextView) view.findViewById(R.id.address);
			holder.phoneView = (TextView) view.findViewById(R.id.phone);
			holder.priceView = (TextView) view.findViewById(R.id.price_value);
			holder.rankView = (TextView) view.findViewById(R.id.rank);
			holder.voteUpView = (TextView) view.findViewById(R.id.vote_up);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		ColorByPrice color = new ColorByPrice(item.getPrice());

		OnClickListener imageClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				imageClick(position);
			}
		};
		OnClickListener voteUpClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				voteUpClick(position);
			}
		};
		OnClickListener priceClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				priceClick(position);
			}
		};
		OnClickListener phoneClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				phoneClick(position);
			}
		};

		holder.imageView.setImageResource(item.getImage());
		holder.mapView.setImageResource(item.getMap());

		holder.addressView.setText(item.getAddress());
		holder.phoneView.setText(item.getPhone());
		holder.priceView.setText("€");
		holder.priceView.setTextColor(color.getColorRank());
		holder.labelView.setText(item.getName());
		holder.rankView.setText(Integer.toString(item.getRank()));

		holder.imageView.setOnClickListener(imageClickListener);
		holder.voteUpView.setOnClickListener(voteUpClickListener);
		holder.priceView.setOnClickListener(priceClickListener);
		holder.phoneView.setOnClickListener(phoneClickListener);

		return view;
	}

	private void imageClick(int position) {
		mInterface.onImageClick(position);
	}

	private void voteUpClick(int position) {
		mInterface.onVoteUpClick(position);
	}

	private void priceClick(int position) {
		mInterface.onPriceClick(position);
	}

	private void phoneClick(int position) {
		mInterface.onPhoneClick(position);
	}

	public void setInterface(CustomAdapterInterface adapterInterface) {
		mInterface = adapterInterface;
	}

}
