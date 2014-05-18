package com.castro.tops;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.castro.tops.swipelistview.SwipeListView;

public class MainActivity extends ActionBarActivity {

	private class BuildRestaurantList extends
			AsyncTask<Void, Void, List<Restaurant>> {

		private final String LINE_SPLIT_CHAR = ";;";
		private final String ITEM_SPLIT_CHAR = ";";

		private final String NAME_CHAR = "n:";
		private final String ADDRESS_CHAR = "a:";
		private final String PHONE_CHAR = "p:";
		private final String PRICE_CHAR = "c:";
		private final String IMAGE_CHAR = "i:";
		private final String RANK_CHAR = "r:";

		private final String LIST_FILE = "list.txt";

		@Override
		protected List<Restaurant> doInBackground(Void... params) {
			AssetManager assets = getAssets();
			List<Restaurant> list = new ArrayList<Restaurant>();
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(assets.open(LIST_FILE)));
				String[] items = reader.readLine().split(LINE_SPLIT_CHAR);
				String[] item;

				for (String s : items) {
					item = s.split(ITEM_SPLIT_CHAR);
					Restaurant r = new Restaurant();
					for (String ss : item) {
						String temp = ss.substring(0, 2);
						String content = ss.substring(2);
						if (temp.contains(NAME_CHAR)) {
							r.setName(content);
						} else if (temp.contains(ADDRESS_CHAR)) {
							r.setAddress(content);
						} else if (temp.contains(PHONE_CHAR)) {
							r.setPhone(content);
						} else if (temp.contains(PRICE_CHAR)) {
							r.setPrice(Integer.parseInt(content));
						} else if (temp.contains(IMAGE_CHAR)) {
							r.setImage(getResources().getIdentifier(content,
									"drawable", getPackageName()));
						} else if (temp.contains(RANK_CHAR)) {
							r.setVotes(Integer.parseInt(content));
						}
					}
					r.setMissingValues();
					list.add(r);
				}
			} catch (IOException e) {
			}
			sortList(list, Sort.MODE_VOTES, false);
			return list;
		}

		@Override
		protected void onPostExecute(List<Restaurant> list) {
			mRestaurantList.clear();
			mRestaurantList.addAll(list);
			mCustomAdapter.notifyDataSetChanged();
		}
	}

	private interface Sort {
		public static int MODE_RANK = 0;
		public static int MODE_LETTER = 1;
		public static int MODE_VOTES = 2;
	}

	private List<Restaurant> mRestaurantList;
	private CustomAdapter mCustomAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.swipelistview_fragment);

		setSwipeList();
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

	private void setSwipeList() {
		mRestaurantList = new ArrayList<Restaurant>();
		mCustomAdapter = new CustomAdapter(this, mRestaurantList);
		SwipeListView listView = (SwipeListView) findViewById(R.id.example_lv_list);

		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		listView.setSwipeMode(SwipeListView.SWIPE_MODE_LEFT);

		listView.setAdapter(mCustomAdapter);

		new BuildRestaurantList().execute();
	}

	private void sortByLetter(List<Restaurant> list, boolean reverse) {
		List<String> names = new ArrayList<String>();
		List<Restaurant> temp = new ArrayList<Restaurant>();

		for (Restaurant r : list) {
			names.add(r.getName());
		}
		Collections.sort(names);
		if (reverse) {
			Collections.reverse(names);
		}

		for (String s : names) {
			for (Restaurant r : list) {
				if (s.equals(r.getName())) {
					temp.add(r);
					list.remove(list.indexOf(r));
					break;
				}
			}
		}
		list.clear();
		list.addAll(temp);
	}

	private void sortByRank(List<Restaurant> list, boolean reverse) {
		List<Restaurant> temp = new ArrayList<Restaurant>(list);
		for (Restaurant r : list) {
			temp.set(r.getRank() - 1, r);
		}
		list.clear();
		list.addAll(temp);
		if (reverse) {
			Collections.reverse(list);
		}
	}

	private void sortByVotes(List<Restaurant> list, final boolean reverse) {
		final Comparator<Restaurant> comparator = new Comparator<Restaurant>() {
			@Override
			public int compare(Restaurant first, Restaurant second) {
				int result = Integer.valueOf(first.getRank()).compareTo(
						second.getRank());
				return reverse ? result : -result;
			}
		};
		Collections.sort(list, comparator);
		for (int x = 0; x < list.size(); x++) {
			list.get(x).setRank(x + 1);
		}
	}

	private void sortList(List<Restaurant> list, int mode, final boolean reverse) {
		switch (mode) {
		case Sort.MODE_RANK:
			sortByRank(list, reverse);
			break;
		case Sort.MODE_LETTER:
			sortByLetter(list, reverse);
			break;
		case Sort.MODE_VOTES:
			sortByVotes(list, reverse);
			break;
		}
	}
}
