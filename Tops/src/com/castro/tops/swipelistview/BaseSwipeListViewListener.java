package com.castro.tops.swipelistview;

public class BaseSwipeListViewListener implements SwipeListViewListener {
	@Override
	public int onChangeSwipeMode(int position) {
		return SwipeListView.SWIPE_MODE_DEFAULT;
	}

	@Override
	public void onChoiceChanged(int position, boolean selected) {
	}

	@Override
	public void onChoiceEnded() {
	}

	@Override
	public void onChoiceStarted() {
	}

	@Override
	public void onClickBackView(int position) {
	}

	@Override
	public void onClickFrontView(int position) {
	}

	@Override
	public void onClosed(int position, boolean fromRight) {
	}

	@Override
	public void onDismiss(int[] reverseSortedPositions) {
	}

	@Override
	public void onFirstListItem() {
	}

	@Override
	public void onLastListItem() {
	}

	@Override
	public void onListChanged() {
	}

	@Override
	public void onMove(int position, float x) {
	}

	@Override
	public void onOpened(int position, boolean toRight) {
	}

	@Override
	public void onStartClose(int position, boolean right) {
	}

	@Override
	public void onStartOpen(int position, int action, boolean right) {
	}
}
