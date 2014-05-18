package com.castro.tops;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
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
				int count = 1;

				for (String s : items) {
					item = s.split(ITEM_SPLIT_CHAR);
					Restaurant r = new Restaurant();
					r.setName(item[0]);
					r.setAddress(item[1]);
					r.setPhone(item[2]);
					r.setRank(count);
					r.setPrice(Integer.parseInt(item[3]));
					r.setImage(getResources().getIdentifier(item[4],
							"drawable", getPackageName()));

					list.add(r);
					count++;
				}
			} catch (IOException e) {
			}

			return list;
		}

		@Override
		protected void onPostExecute(List<Restaurant> list) {
			mRestaurantList.clear();
			mRestaurantList.addAll(list);
			mCustomAdapter.notifyDataSetChanged();
		}
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

}
