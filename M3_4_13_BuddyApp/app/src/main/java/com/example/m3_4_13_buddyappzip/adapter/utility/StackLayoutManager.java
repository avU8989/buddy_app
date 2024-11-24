package com.example.m3_4_13_buddyappzip.adapter.utility;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class StackLayoutManager extends RecyclerView.LayoutManager {

	private static final int CARD_SPACING = 0; // Adjust this value to control the spacing between stacked cards
	private static final int TOP_MARGIN = 0; // Adjust this value to control the top margin between stacked cards

	@Override
	public RecyclerView.LayoutParams generateDefaultLayoutParams() {
		return new RecyclerView.LayoutParams(
				RecyclerView.LayoutParams.WRAP_CONTENT,
				RecyclerView.LayoutParams.WRAP_CONTENT
		);
	}

	@Override
	public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
		detachAndScrapAttachedViews(recycler);

		int itemCount = getItemCount();
		if (itemCount == 0) {
			return;
		}

		int parentHeight = getHeight();
		int parentWidth = getWidth();

		int topMargin = TOP_MARGIN; // Set the initial top margin
		int maxVisibleItems = 3; // Adjust this value to control the number of visible stacked items

		for (int i = Math.min(maxVisibleItems, itemCount) - 1; i >= 0; i--) {
			View view = recycler.getViewForPosition(i);
			addView(view);

			measureChildWithMargins(view, 0, 0);

			int viewHeight = getDecoratedMeasuredHeight(view);
			int viewWidth = getDecoratedMeasuredWidth(view);

			int left = (parentWidth - viewWidth) / 2;
			int top = topMargin;

			int right = left + viewWidth;
			int bottom = top + viewHeight;

			layoutDecorated(view, left, top, right, bottom);

			topMargin -= CARD_SPACING; // Adjust the top margin to reduce padding between stacked cards
			left -= CARD_SPACING; // Adjust the spacing between stacked cards
			right += CARD_SPACING; // Adjust the spacing between stacked cards
		}
	}
}

