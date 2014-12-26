package com.summerxia.dateselector.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.View;
import android.view.WindowManager;

public class DateUtils {

	/**
	 * 获取屏幕的比例
	 * 
	 * @return
	 */
	public static float getScaledDensity(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		float value = dm.scaledDensity;
		return value;
	}

	/**
	 * 将dip转换为px
	 * 
	 * @return
	 */
	public static int dip2Px(Context context, float dip) {
		return (int) (context.getResources().getDisplayMetrics().density * dip);
	}

	/**
	 * 将px转换为dip
	 * 
	 * @return
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * base64编码后调用
	 * 
	 * @param str
	 * @return
	 */
	public static String encodeReplace(String str) {
		str = str.replace("+", "_").replace("=", "%").replace("/", "*");
		return str;

	}

	/**
	 * base64解码前调用
	 * 
	 * @param str
	 * @return
	 */
	public static String decodeReplace(String str) {
		str = str.replace("_", "+").replace("%", "=").replace("*", "/");
		return str;

	}

	/**
	 * 适用于Adapter中简化ViewHolder相关代码
	 * 
	 * @param convertView
	 * @param id
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends View> T obtainView(View convertView, int id) {
		SparseArray<View> holder = (SparseArray<View>) convertView.getTag();
		if (holder == null) {
			holder = new SparseArray<View>();
			convertView.setTag(holder);
		}
		View childView = holder.get(id);
		if (childView == null) {
			childView = convertView.findViewById(id);
			holder.put(id, childView);
		}
		return (T) childView;
	}

	/**
	 * 获取控件的高度，如果获取的高度为0，则重新计算尺寸后再返回高度
	 * 
	 * @param view
	 * @return
	 */
	public static int getViewMeasuredHeight(View view) {
		calcViewMeasure(view);
		return view.getMeasuredHeight();
	}

	/**
	 * 获取控件的宽度，如果获取的宽度为0，则重新计算尺寸后再返回宽度
	 * 
	 * @param view
	 * @return
	 */
	public static int getViewMeasuredWidth(View view) {
		calcViewMeasure(view);
		return view.getMeasuredWidth();
	}

	/**
	 * 测量控件的尺寸
	 * 
	 * @param view
	 */
	public static void calcViewMeasure(View view) {
		int width = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int expandSpec = View.MeasureSpec.makeMeasureSpec(
				Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
		view.measure(width, expandSpec);
	}
	
	public static String splitDateString(String date){
		//1942年
		return date.split(" ")[0];
	}
	
	/**
	 * 获取屏幕宽度(像素)
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context) {
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		return windowManager.getDefaultDisplay().getWidth();
	}
	/**
	 * 获取屏幕高度(像素)
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context) {
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		return windowManager.getDefaultDisplay().getHeight();
	}
}
