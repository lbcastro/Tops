package com.castro.tops;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setList();

		int size = mRestaurantList.size();
		String[] labels = new String[size];
		String[] address = new String[size];
		String[] phone = new String[size];
		int[] ranks = new int[size];
		float[] prices = new float[size];
		int[] images = new int[size];

		for (int x = 0; x < size; x++) {
			labels[x] = mRestaurantList.get(x).getName();
			address[x] = mRestaurantList.get(x).getAddress();
			phone[x] = mRestaurantList.get(x).getPhone();
			prices[x] = mRestaurantList.get(x).getPrice();
			ranks[x] = x + 1;
			images[x] = mRestaurantList.get(x).getImage();
		}

		CustomArrayAdapter adapter = new CustomArrayAdapter(this, labels,
				ranks, prices, images, address, phone,
				this.getSupportFragmentManager());

		com.castro.tops.swipelistview.SwipeListView listView = (com.castro.tops.swipelistview.SwipeListView) findViewById(R.id.example_lv_list);
		listView.setAdapter(adapter);
		// if (savedInstanceState == null) {
		// PlaceholderFragment fragment = new PlaceholderFragment();
		// getSupportFragmentManager().beginTransaction()
		// .add(R.id.container, fragment).commit();
		// }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public static int getResId(String variableName, Class<?> c) {

		try {
			Field idField = c.getDeclaredField(variableName);
			return idField.getInt(idField);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	private void setList() {
		mRestaurantList = new ArrayList<Restaurant>();
		BufferedReader br;
		try {
			AssetManager assets = getAssets();
			br = new BufferedReader(new InputStreamReader(
					assets.open("list.txt")));

			String items = br.readLine();
			String[] list = items.split(";;");

			for (String s : list) {
				String[] split = s.split(";");
				Restaurant r = new Restaurant(split[0], split[1], split[2],
						Integer.parseInt(split[3]), getResources()
								.getIdentifier(split[4], "drawable",
										getPackageName()));
				mRestaurantList.add(r);
			}

		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	private List<Restaurant> mRestaurantList;

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		private List<Restaurant> mRestaurantList;
		private CustomArrayAdapter adapter;
		private View mRootView;

		public static int getResId(String variableName, Class<?> c) {

			try {
				Field idField = c.getDeclaredField(variableName);
				return idField.getInt(idField);
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}

		private void setList() {
			mRestaurantList = new ArrayList<Restaurant>();
			BufferedReader br;
			try {
				AssetManager assets = getActivity().getAssets();
				br = new BufferedReader(new InputStreamReader(
						assets.open("list.txt")));

				String items = br.readLine();
				String[] list = items.split(";;");

				for (String s : list) {
					String[] split = s.split(";");
					Restaurant r = new Restaurant(split[0], split[1], split[2],
							Integer.parseInt(split[3]), getResources()
									.getIdentifier(split[4], "drawable",
											getActivity().getPackageName()));
					mRestaurantList.add(r);
				}

			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			mRootView = inflater.inflate(R.layout.fragment_main, container,
					false);

			setList();

			int size = mRestaurantList.size();
			String[] labels = new String[size];
			String[] address = new String[size];
			String[] phone = new String[size];
			int[] ranks = new int[size];
			float[] prices = new float[size];
			int[] images = new int[size];

			for (int x = 0; x < size; x++) {
				labels[x] = mRestaurantList.get(x).getName();
				address[x] = mRestaurantList.get(x).getAddress();
				phone[x] = mRestaurantList.get(x).getPhone();
				prices[x] = mRestaurantList.get(x).getPrice();
				ranks[x] = x + 1;
				images[x] = mRestaurantList.get(x).getImage();
			}

			adapter = new CustomArrayAdapter(getActivity(), labels, ranks,
					prices, images, address, phone, getChildFragmentManager());

			com.castro.tops.swipelistview.SwipeListView listView = (com.castro.tops.swipelistview.SwipeListView) mRootView
					.findViewById(R.id.example_lv_list);

			// ListView listView = (ListView) mRootView
			// .findViewById(R.id.listview);
			listView.setAdapter(adapter);

			// String[] labels = { "Porto", "Ar do rio", "Downtown" };
			// int[] ranks = { 1, 2, 3 };
			// String[] address = { "Rua de exemplo", "Avenida Diogo Leite nº5",
			// "Rua do Bonjardim nº87" };
			// String[] phone = { "22 332 4562", "22 200 6465", "22 370 1797" };
			// float[] prices = { 10, 8.5f, 5 };
			// int[] images = { R.drawable.one, R.drawable.two, R.drawable.three
			// };
			//
			// adapter = new CustomArrayAdapter(getActivity(), labels, ranks,
			// prices, images, address, phone, getChildFragmentManager());

			return mRootView;
		}
	}

}
