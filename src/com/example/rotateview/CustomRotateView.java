package com.example.rotateview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

public class CustomRotateView extends View {

	private Paint mCirclePaint;
	private float mStrokeWidth = 10.0f;
	private int mDefaultSize = 200;
	private int mBackgroundColor = Color.GREEN;
	private int mCircleColor = Color.WHITE;
	private float mGapAngel = 30.0f;
	private int mArcCount = 3;
	private RectF mRect;
	private float mOffset = 0.0f;
	private int mDuration = 2000;

	public CustomRotateView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO 自动生成的构造函数存根
		initPaint();
		startAnimation();
	}

	private void initPaint() {
		// TODO 自动生成的方法存根
		mCirclePaint = new Paint();
		mCirclePaint.setColor(mCircleColor);
		mCirclePaint.setAntiAlias(true);
		mCirclePaint.setStyle(Style.STROKE);
		mCirclePaint.setStrokeCap(Cap.ROUND);
		mCirclePaint.setStrokeWidth(mStrokeWidth);
	}

	private void startAnimation() {
		ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 360.0f);
		animator.setDuration(mDuration);
		animator.setInterpolator(new AccelerateDecelerateInterpolator());
		animator.setRepeatCount(-1);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO 自动生成的方法存根
				mOffset = Float.parseFloat(animation.getAnimatedValue()
						.toString());
				invalidate();
			}
		});
		animator.start();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO 自动生成的方法存根
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int width = measureSize(widthMode, widthSize);
		int height = measureSize(heightMode, heightSize);
		setMeasuredDimension(width, height);
		mRect = new RectF(mStrokeWidth / 2 + getPaddingLeft(), mStrokeWidth / 2
				+ getPaddingTop(), getMeasuredWidth() - mStrokeWidth / 2
				- getPaddingRight(), getMeasuredHeight() - mStrokeWidth / 2
				- getPaddingBottom());// 支持Padding属性
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO 自动生成的方法存根
		canvas.drawColor(mBackgroundColor);
		float eachArcSweepAngel = (360 - mArcCount * mGapAngel) / mArcCount;
		canvas.save();
		// canvas.translate(centerX, centerY);
		for (int i = 0; i < mArcCount; i++) {
			canvas.drawArc(mRect, mOffset + mGapAngel * (i + 1) + i
					* eachArcSweepAngel, eachArcSweepAngel, false, mCirclePaint);
		}
		canvas.restore();
	}

	private int measureSize(int mode, int size) {
		// TODO 自动生成的方法存根
		int finalSize = size;
		if (mode == MeasureSpec.EXACTLY) {
			return finalSize;
		} else {
			finalSize = mDefaultSize;
			if (mode == MeasureSpec.AT_MOST) {
				finalSize = Math.min(mDefaultSize, size);
			}
		}
		return finalSize;
	}
}
