package com.poker_model;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class NyBaseAdapter extends BaseAdapter {

	private int[] imgs;
	private Context context;

	public NyBaseAdapter(Context context, int[] imgs) {
		this.context = context;
		this.imgs = imgs;
	}

	@Override
	public int getCount() {
		return imgs.length;
	}

	@Override
	public Object getItem(int position) {
		return imgs[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		v = LayoutInflater.from(context).inflate(R.layout.poker_model_child,
				null);

		ImageView img_poker = (ImageView) v.findViewById(R.id.img_poker);
		img_poker.setBackgroundResource(imgs[position]);

		img_poker.setTag(position);

		return v;
	}
}
