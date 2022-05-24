package com.github.weiggle.jetpack;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.github.weiggle.jetpack.utils.DpUtils;


public class ShadowLayout extends RelativeLayout {
    //阴影类型
    private static final int TYPE_ALL = 0;
    private static final int TYPE_LEFT = 1;
    private static final int TYPE_TOP = 2;
    private static final int TYPE_RIGHT = 3;
    private static final int TYPE_BOTTOM = 4;

    private int mShadowType;
    private int mShadowColor;
    private int mSelectedShadowColor;
    private float mShadowRadius;
    private float mCornerRadius;
    private float mDx;
    private float mDy;
    private boolean mShowShadow = true;
    private RectF shadowRect;
    private Paint shadowPaint;
    private int shadowW;
    private int shadowH;

    public ShadowLayout(Context context) {
        super(context);
        initView(context, null);
    }

    public ShadowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public ShadowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && h > 0) {
            shadowH = h;
            shadowW = w;
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        if (mShowShadow) {
            shadowRect.set(mShadowRadius + mDx, mShadowRadius + mDy, shadowW - mShadowRadius - mDx,
                    shadowH - mShadowRadius - mDy);
            if (!isInEditMode()) {
                shadowPaint.setShadowLayer(mShadowRadius, mDx, mDy, isSelected() ? mSelectedShadowColor : mShadowColor);
            }
            canvas.drawRoundRect(shadowRect, mCornerRadius, mCornerRadius, shadowPaint);
        }
        super.dispatchDraw(canvas);
    }


    private void initView(Context context, AttributeSet attrs) {
        initAttributes(context, attrs);
        mDx = Math.abs(mDx);
        mDy = Math.abs(mDy);
        int xPadding = (int) (mShadowRadius + mDx);
        int yPadding = (int) (mShadowRadius + mDy);
        int leftPadding;
        int topPadding;
        int rightPadding;
        int bottomPadding;
        leftPadding = rightPadding = xPadding;
        topPadding = bottomPadding = yPadding;
        switch (mShadowType) {
            case TYPE_TOP:
                leftPadding = rightPadding = 0;
                bottomPadding = 0;
                topPadding = yPadding;
                break;
            case TYPE_BOTTOM:
                leftPadding = rightPadding = 0;
                topPadding = 0;
                bottomPadding = yPadding;
                break;
            case TYPE_LEFT:
                topPadding = bottomPadding = 0;
                leftPadding = xPadding;
                rightPadding = 0;
                break;
            case TYPE_RIGHT:
                topPadding = bottomPadding = 0;
                leftPadding = 0;
                rightPadding = xPadding;
                break;
        }
        setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
        setLayerType(LAYER_TYPE_SOFTWARE, null);//禁用硬件加速，否则部分机型不显示
        shadowPaint = new Paint();
        shadowPaint.setAntiAlias(true);
        shadowPaint.setStyle(Paint.Style.FILL);
        shadowPaint.setColor(Color.TRANSPARENT);
        shadowRect = new RectF();
    }


    public void invalidateShadow() {
        postInvalidate();
    }

    public void setShadowColor(int shadowColor) {
        this.mShadowColor = shadowColor;
        invalidateShadow();
    }

    public void showShadow(boolean showShadow) {
        mShowShadow = showShadow;
        invalidateShadow();
    }

    public void setCircleRadius(int radius) {
        this.mCornerRadius = DpUtils.dp2px(radius);
        invalidateShadow();
    }

    public void setShadowRadius(int radius) {
        this.mShadowRadius = DpUtils.dp2px(radius);
        invalidateShadow();
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        invalidateShadow();
    }


    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray attr = getTypedArray(context, attrs, R.styleable.shadowlayout);
        if (attr == null) {
            return;
        }

        try {
            mShadowType = attr.getInt(R.styleable.shadowlayout_sl_shadow_type, TYPE_ALL);
            mCornerRadius = attr.getDimension(R.styleable.shadowlayout_sl_cornerRadius, 0);
            mShadowRadius = attr.getDimension(R.styleable.shadowlayout_sl_shadowRadius, 0);
            mDx = attr.getDimension(R.styleable.shadowlayout_sl_dx, 0);
            mDy = attr.getDimension(R.styleable.shadowlayout_sl_dy, 0);
            mShadowColor = attr.getColor(R.styleable.shadowlayout_sl_shadowColor,
                    getResources().getColor(R.color.default_shadow_color));
            mSelectedShadowColor =
                    attr.getColor(R.styleable.shadowlayout_sl_selected_shadowColor, mShadowColor);
        } finally {
            attr.recycle();
        }
    }

    private TypedArray getTypedArray(Context context, AttributeSet attributeSet, int[] attr) {
        return context.obtainStyledAttributes(attributeSet, attr, 0, 0);
    }

}
