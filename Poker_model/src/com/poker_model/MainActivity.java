package com.poker_model;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ImageView img1;
	private ImageView img2;
	private ImageView img3;
	private ImageView img4;
	private ImageView currImg;
	private TextView tv_total;
	float startX = 0;
	float moveX = 0;
	float poorX = 0;
	Animation animRo;
	Animation animShan1;
	AnimationSet animS;
	Gallery gallery_poker_model;

	int id1 = R.drawable.a1;
	int id2 = R.drawable.a2;
	int id3 = R.drawable.a3;

	private int[] imgs = new int[] { R.drawable.a10, R.drawable.a2,
			R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6,
			R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a1 };

	private boolean isNext = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.poker_model);

		img1 = (ImageView) findViewById(R.id.img1);
		img2 = (ImageView) findViewById(R.id.img2);
		img3 = (ImageView) findViewById(R.id.img3);
		img4 = (ImageView) findViewById(R.id.img4);
		tv_total = (TextView) findViewById(R.id.tv_total);
		tv_total.setText((curr_position + 1) + "/" + imgs.length);
		gallery_poker_model = (Gallery) findViewById(R.id.gallery_poker_model);
		gallery_poker_model.setAdapter(new NyBaseAdapter(this, imgs));

		gallery_poker_model
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						ImageView img = (ImageView) ((LinearLayout) arg1)
								.getChildAt(0);
						if (currImg != null) {
							currImg.setImageDrawable(null);
						}
						img.setImageResource(R.drawable.red_border);
						currImg = img;
						int position = (Integer) img.getTag();
						curr_position = position;
						changView(position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});

		RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(
				ScreenUtils.getScreenWidth(this),
				ScreenUtils.getScreenWidth(this));
		rl.setMargins(20, 30, 20, 0);

		img1.setLayoutParams(rl);
		img4.setLayoutParams(rl);

		rl = new RelativeLayout.LayoutParams(ScreenUtils.getScreenWidth(this),
				ScreenUtils.getScreenWidth(this));
		rl.setMargins(40, 20, 40, 0);
		img2.setLayoutParams(rl);

		rl = new RelativeLayout.LayoutParams(ScreenUtils.getScreenWidth(this),
				ScreenUtils.getScreenWidth(this));
		rl.setMargins(60, 10, 60, 0);
		img3.setLayoutParams(rl);

		img2.setAlpha(0.8f);
		img3.setAlpha(0.6f);

		img1.setBackgroundResource(imgs[0]);
		img4.setBackgroundResource(imgs[0]);
		img2.setBackgroundResource(imgs[1]);
		img3.setBackgroundResource(imgs[2]);

		img1.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					startX = event.getX();
					break;
				case MotionEvent.ACTION_MOVE:
					Log.i("aaaa", "--ACTION_MOVE--" + event.getX());
					moveX = event.getX();
					poorX = startX - moveX;
					if (poorX > 50) {
						animRo = new RotateAnimation(-poorX / 30, -poorX / 30,
								img1.getX() + img1.getWidth() - 100, img1
										.getY() + img1.getHeight() - 100);
						animShan1 = new RotateAnimation(-poorX / 60,
								-poorX / 60, img1.getX() + img1.getWidth()
										- 100, img1.getY() + img1.getHeight()
										- 100);

						Animation animAp = new AlphaAnimation(0.5f, 0.5f);

						animS = new AnimationSet(false);

						animRo.setFillAfter(true);
						animS.setFillAfter(true);

						animS.addAnimation(animAp);
						animS.addAnimation(animShan1);

						img1.startAnimation(animRo);
						img2.startAnimation(animS);

					}

					break;
				case MotionEvent.ACTION_UP:
					if (animRo != null && animS != null) {
						animRo.setFillAfter(false);
						animS.setFillAfter(false);
					}
					if (poorX > 50) {
						animRo = new RotateAnimation(-poorX / 30, 0, img1
								.getX() + img1.getWidth() - 100, img1.getY()
								+ img1.getHeight() - 100);
						animShan1 = new RotateAnimation(-poorX / 60, 0, img1
								.getX() + img1.getWidth() - 100, img1.getY()
								+ img1.getHeight() - 100);
						Animation animShan2 = new RotateAnimation(poorX / 60,
								0, img1.getX() + img1.getWidth() - 100, img1
										.getY() + img1.getHeight() - 100);

						animRo.setDuration(700);
						animShan1.setDuration(700);

						animShan2.setDuration(800);

						animRo.setInterpolator(new OvershootInterpolator());
						animShan1.setInterpolator(new OvershootInterpolator());
						animShan2.setInterpolator(new OvershootInterpolator());

						img1.startAnimation(animRo);
						img2.startAnimation(animShan1);
						img3.startAnimation(animShan2);

						Animation animT = new TranslateAnimation(100, -800, 0,
								0);
						animT.setDuration(500);
						img4.startAnimation(animT);
						animT.setAnimationListener(new AnimationListener() {

							@Override
							public void onAnimationStart(Animation animation) {
								img4.setVisibility(View.VISIBLE);
								next();
								if (curr_position == imgs.length - 1) {
									curr_position = 0;
								} else
									curr_position++;
							}

							@Override
							public void onAnimationRepeat(Animation animation) {
							}

							@Override
							public void onAnimationEnd(Animation animation) {
								img4.setVisibility(View.GONE);
								animRo.setFillAfter(false);
								animS.setFillAfter(false);
								// img4.setBackground(img1.getBackground());

								gallery_poker_model.setSelection(curr_position);
							}
						});

					} else if (poorX < -50) {
						TranslateAnimation animTr = new TranslateAnimation(
								-800, 0, -200, 0);
						final TranslateAnimation animTr1 = new TranslateAnimation(
								20, 0, 20, 0);
						final TranslateAnimation animTr2 = new TranslateAnimation(
								24, 0, 24, 0);
						animTr1.setDuration(1100);
						animTr2.setDuration(1500);
						animTr.setDuration(500);
						animTr.setInterpolator(new OvershootInterpolator());
						animTr1.setInterpolator(new OvershootInterpolator());
						animTr2.setInterpolator(new OvershootInterpolator());
						img4.startAnimation(animTr);
						animTr.setAnimationListener(new AnimationListener() {

							@Override
							public void onAnimationStart(Animation animation) {
								img4.setVisibility(View.VISIBLE);
								if (curr_position == 0) {
									img4.setBackgroundResource(imgs[imgs.length - 1]);
								} else {
									img4.setBackgroundResource(imgs[curr_position - 1]);
								}
							}

							@Override
							public void onAnimationRepeat(Animation animation) {
							}

							@Override
							public void onAnimationEnd(Animation animation) {
								img4.setVisibility(View.GONE);
								img3.startAnimation(animTr1);
								img2.startAnimation(animTr2);
								// last();
								isNext = false;
								if (curr_position == 0) {
									curr_position = imgs.length - 1;
								} else
									curr_position--;
								gallery_poker_model.setSelection(curr_position);
							}
						});

					}
					break;

				default:
					break;
				}
				return true;
			}
		});
	}

	public void next() {
		img4.setBackground(img1.getBackground());
		img1.setBackground(img2.getBackground());
		img2.setBackground(img3.getBackground());
		if (curr_position == imgs.length - 2) {
			img3.setBackgroundResource(imgs[0]);
		} else if (curr_position == imgs.length - 1) {
			img3.setBackgroundResource(imgs[1]);
		} else {
			img3.setBackgroundResource(imgs[curr_position + 2]);
		}
	}

	public void last() {
		Drawable demo = img1.getBackground();
		img1.setBackground(img3.getBackground());
		img3.setBackground(img2.getBackground());
		img2.setBackground(demo);
	}

	public void changView(int position) {
		img1.setBackgroundResource(imgs[position]);

		if (imgs.length == 1) {

		} else if (imgs.length == 2) {
			img2.setBackgroundResource(imgs[position + 1]);
		} else if (position == imgs.length - 2) {
			img2.setBackgroundResource(imgs[position + 1]);
			img3.setBackgroundResource(imgs[0]);
		} else if (position == imgs.length - 1) {
			img2.setBackgroundResource(imgs[0]);
			img3.setBackgroundResource(imgs[1]);
		} else {
			img2.setBackgroundResource(imgs[position + 1]);
			img3.setBackgroundResource(imgs[position + 2]);

		}
		tv_total.setText((curr_position + 1) + "/" + imgs.length);
	}

	private int curr_position = 0;
}
