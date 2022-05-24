package com.github.weiggle.jetpack.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.WindowManager

/**
 * Created by kou on 2020/9/2.
 */
class DpUtils private constructor() {
    companion object {
        /*获取屏幕密度*/
        @JvmStatic
        fun getDensity(): Float {
            return Resources.getSystem().displayMetrics.density
        }

        /**
         * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
         */
        @JvmStatic
        fun dp2px(dpValue: Float): Int {
            return (0.5f + dpValue * getDensity()).toInt()
        }

        /**
         * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
         */
        @JvmStatic
        fun px2dp(pxValue: Float): Int {
            return (pxValue / getDensity() + 0.5f).toInt()
        }

        @JvmStatic
        fun dp2px(context: Context, dipValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dipValue * scale + 0.5f).toInt()
        }

        @JvmStatic
        fun sp2px(context: Context, spVal: Float): Float {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                spVal, context.resources.displayMetrics
            )
        }

        @JvmStatic
        fun px2sp(context: Context, pxValue: Float): Float {
            val fontScale = context.resources.displayMetrics.scaledDensity
            return pxValue / fontScale + 0.5f
        }

        @JvmStatic
        fun getScreenSize(context: Context): IntArray {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val outMetrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(outMetrics)
            return intArrayOf(outMetrics.widthPixels, outMetrics.heightPixels)
        }

        @JvmStatic
        fun measureViewSize(view: View) {
            val width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            val height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            view.measure(width, height)
        }

        @JvmStatic
        fun getViewSize(view: View): IntArray? {
            val size = IntArray(2)
            val width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            val height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            view.measure(width, height)
            size[0] = view.measuredHeight
            size[1] = view.measuredWidth
            return size
        }

        /**
         * 获取屏幕的高度，单位：像素
         *
         * @return 返回 屏幕的高度, 单位：像素
         */
        fun getScreenHeight(context: Context): Int {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val point = Point()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                wm.defaultDisplay.getRealSize(point)
            } else {
                wm.defaultDisplay.getSize(point)
            }
            return point.y
        }

        /**
         * 获取屏幕的宽度，单位：像素
         *
         * @return 返回 屏幕的宽度, 单位：像素
         */
        fun getScreenWidth(context: Context): Int {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val point = Point()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                wm.defaultDisplay.getRealSize(point)
            } else {
                wm.defaultDisplay.getSize(point)
            }
            return point.x
        }

        /**
         * 获取app能展示的宽度, 单位：像素
         */
        fun getAppScreenWidth(context: Context): Int {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val point = Point()
            wm.defaultDisplay.getSize(point)
            return point.x
        }

        /**
         * 获取app能展示的高度, 单位：像素
         */
        fun getAppScreenHeight(context: Context): Int {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val point = Point()
            wm.defaultDisplay.getSize(point)
            return point.y
        }

        /**
         * 判断手机是不是全面屏手机，有些场景需要判断手机是不是全面屏，全面屏的手机布局可能和一般的手机不太一样
         */
        fun isAllScreenDevice(context: Context): Boolean {
            // 低于 API 21的，都不会是全面屏。。。
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                return false
            }

            val screenHeight = getScreenHeight(context)
            val screenWidth = getScreenWidth(context)
            return screenWidth / screenHeight > 1.97 || screenHeight / screenWidth > 1.97 // 竖横比或横竖比大于1.97就认为是全面屏手机
        }
    }
}