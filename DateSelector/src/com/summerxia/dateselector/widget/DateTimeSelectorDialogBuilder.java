package com.summerxia.dateselector.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.summerxia.dateselector.R;
import com.summerxia.dateselector.utils.DateUtils;

/**
 * 项目名称: QYH 类名称: DateTimeSelectorDialogBuilder 创建人: xhl 创建时间: 2014-12-24
 * 下午2:15:14 版本: v1.0 类描述: 选择生日日期的Dialog
 */
public class DateTimeSelectorDialogBuilder extends NiftyDialogBuilder implements
		android.view.View.OnClickListener {

	private Context context;
	private RelativeLayout rlCustomLayout;
	private DateSelectorWheelView dateWheelView;
	private FrameLayout flSecondeCustomLayout;
	private OnSaveListener saveListener;
	/**
	 * 默认方向标示
	 */
	private static int mOrientation = 1;
	private static DateTimeSelectorDialogBuilder instance;

	public interface OnSaveListener {
		abstract void onSaveSelectedDate(String selectedDate);
	}

	public DateTimeSelectorDialogBuilder(Context context) {
		super(context);
		this.context = context;
		initDialog();
	}

	public DateTimeSelectorDialogBuilder(Context context, int theme) {
		super(context, theme);
		this.context = context;
		initDialog();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setBackgroundDrawableResource(R.drawable.edit_dialog_coner);
	}

	public static DateTimeSelectorDialogBuilder getInstance(Context context) {

		int ort = context.getResources().getConfiguration().orientation;
		if (mOrientation != ort) {
			mOrientation = ort;
			instance = null;
		}

		if (instance == null || ((Activity) context).isFinishing()) {
			synchronized (DateTimeSelectorDialogBuilder.class) {
				if (instance == null) {
					instance = new DateTimeSelectorDialogBuilder(context,
							R.style.dialog_untran);
				}
			}
		}
		return instance;

	}

	private void initDialog() {
		rlCustomLayout = (RelativeLayout) LayoutInflater.from(context).inflate(
				R.layout.date_time_selector_dialog_layout, null);
		dateWheelView = (DateSelectorWheelView) rlCustomLayout
				.findViewById(R.id.pdwv_date_time_selector_wheelView);
		dateWheelView.setTitleClick(this);
		flSecondeCustomLayout = (FrameLayout) rlCustomLayout
				.findViewById(R.id.fl_date_time_custom_layout);
		setDialogProperties();
	}

	private void setDialogProperties() {
		int width = DateUtils.getScreenWidth(context) * 3 / 4;
		this.withDialogWindows(width, LayoutParams.WRAP_CONTENT)
				.withTitleColor("#FFFFFF").withTitle("选择日期")
				.setDialogClick(this).withPreviousText("取消")
				.withPreviousTextColor("#3598da").withDuration(100)
				.setPreviousLayoutClick(this).withNextText("保存")
				.withMessageMiss(View.GONE).withNextTextColor("#3598da")
				.setNextLayoutClick(this)
				.setCustomView(rlCustomLayout, context);

	}

	/**
	 * 设置自定义布局
	 * 
	 * @param view
	 * @param context
	 * @return
	 */
	public DateTimeSelectorDialogBuilder setSencondeCustomView(View view,
			Context context) {
		if (flSecondeCustomLayout.getChildCount() > 0) {
			flSecondeCustomLayout.removeAllViews();
		}
		flSecondeCustomLayout.addView(view);
		return this;
	}

	/**
	 * 设置自定义布局
	 * 
	 * @param resId
	 * @param context
	 * @return
	 */
	public DateTimeSelectorDialogBuilder setSencondeCustomView(int resId,
			Context context) {
		View view = View.inflate(context, resId, null);
		if (flSecondeCustomLayout.getChildCount() > 0) {
			flSecondeCustomLayout.removeAllViews();
		}
		flSecondeCustomLayout.addView(view);
		return this;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.rl_date_time_title:
			if (dateWheelView.getDateSelectorVisibility() == View.VISIBLE) {
				dateWheelView.setDateSelectorVisiblility(View.GONE);
			} else {
				dateWheelView.setDateSelectorVisiblility(View.VISIBLE);
			}
			break;
		case R.id.fl_dialog_title_previous:
			dismiss();
			break;
		case R.id.fl_dialog_title_next:
			if (null != saveListener) {
				saveListener.onSaveSelectedDate(dateWheelView.getSelectedDate());
			}
			dismiss();
			break;
		}
	}

	/**
	 * 获取日期选择器
	 * 
	 * @return
	 */
	public DateSelectorWheelView getDateWheelView() {
		return dateWheelView;
	}

	/**
	 * 设置保存监听
	 * 
	 * @param saveListener
	 */
	public void setOnSaveListener(OnSaveListener saveListener) {
		this.saveListener = saveListener;
	}

	/**
	 * 最初显示时是否可以可见
	 * 
	 * @param visibility
	 */
	public void setWheelViewVisibility(int visibility) {
		dateWheelView.setDateSelectorVisiblility(visibility);
	}

	@Override
	public void dismiss() {
		super.dismiss();
		instance = null;
	}

}
