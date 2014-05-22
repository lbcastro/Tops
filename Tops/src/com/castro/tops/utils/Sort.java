package com.castro.tops.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.castro.tops.Restaurant;


public class Sort {
	public final static int MODE_RANK = 0;
	public final static int MODE_LETTER = 1;
	public final static int MODE_VOTES = 2;

	private static void sortByLetter(List<Restaurant> list, boolean reverse) {
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

	private static void sortByRank(List<Restaurant> list, boolean reverse) {
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

	private static void sortByVotes(List<Restaurant> list,
			final boolean reverse) {
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

	public static void sortList(List<Restaurant> list, int mode,
			final boolean reverse) {
		switch (mode) {
		case MODE_RANK:
			sortByRank(list, reverse);
			break;
		case MODE_LETTER:
			sortByLetter(list, reverse);
			break;
		case MODE_VOTES:
			sortByVotes(list, reverse);
			break;
		}
	}
}