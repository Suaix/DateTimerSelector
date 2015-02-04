package com.summerxia.dateselector.widget;


import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.summerxia.dateselector.R;
import com.summerxia.dateselector.utils.DateUtils;
import com.summerxia.dateselector.wheelview.OnWheelChangedListener;
import com.summerxia.dateselector.wheelview.StrericWheelAdapter;
import com.summerxia.dateselector.wheelview.WheelView;
/**
  * 项目名称:  DateSelector   
  * 类名称:   DateSelectorWheelView 
  * 创建人:    xhl 
  * 创建时间:  2015-1-14 下午5:07:34     
  * 版本:      v1.0  
  * 类描述:
 */
public class DateSelectorWheelView extends RelativeLayout implements
		OnWheelChangedListener {
	private final String flag = "PfpsDateWheelView";
	private RelativeLayout rlTitle;
	private LinearLayout llWheelViews;
	private TextView tvSubTitle;
	private TextView tvYear;
	private TextView tvMonth;
	private TextView tvDay;
	private WheelView wvYear;
	private WheelView wvMonth;
	private WheelView wvDay;
	private String[] years = new String[100];
	private String[] months = new String[12];
	private String[] tinyDays = new String[28];
	private String[] smallDays = new String[29];
	private String[] normalDays = new String[30];
	private String[] bigDays = new String[31];
	private StrericWheelAdapter yearsAdapter;
	private StrericWheelAdapter monthsAdapter;
	private StrericWheelAdapter tinyDaysAdapter;
	private StrericWheelAdapter smallDaysAdapter;
	private StrericWheelAdapter bigDaysAdapter;
	private StrericWheelAdapter normalDaysAdapter;

	public DateSelectorWheelView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initLayout(context);
	}

	public DateSelectorWheelView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initLayout(context);
	}

	public DateSelectorWheelView(Context context) {
		super(context);
		initLayout(context);
	}

	private void initLayout(Context context) {
		LayoutInflater.from(context).inflate(R.layout.dete_time_layout, this,
				true);
		rlTitle = (RelativeLayout) findViewById(R.id.rl_date_time_title);
		llWheelViews = (LinearLayout) findViewById(R.id.ll_wheel_views);
		tvSubTitle = (TextView) findViewById(R.id.tv_date_time_subtitle);
		tvYear = (TextView) findViewById(R.id.tv_date_time_year);
		tvMonth = (TextView) findViewById(R.id.tv_date_time_month);
		tvDay = (TextView) findViewById(R.id.tv_date_time_day);
		wvYear = (WheelView) findViewById(R.id.wv_date_of_year);
		wvMonth = (WheelView) findViewById(R.id.wv_date_of_month);
		wvDay = (WheelView) findViewById(R.id.wv_date_of_day);
		wvYear.addChangingListener(this);
		wvMonth.addChangingListener(this);
		wvDay.addChangingListener(this);
		setData();
	}

	private void setData() {
		for (int i = 0; i < years.length; i++) {
			years[i] = 1960 + i + " 年";
		}
		for (int i = 0; i < months.length; i++) {
			if (i < 9) {
				months[i] = "0" + (1 + i) + " 月";
			} else {
				months[i] = (1 + i) + " 月";
			}
		}
		for (int i = 0; i < tinyDays.length; i++) {
			if (i < 9) {
				tinyDays[i] = "0" + (1 + i) + " 日";
			} else {
				tinyDays[i] = (1 + i) + " 日";
			}
		}
		for (int i = 0; i < smallDays.length; i++) {
			if (i < 9) {
				smallDays[i] = "0" + (1 + i) + " 日";
			} else {
				smallDays[i] = (1 + i) + " 日";
			}
		}
		for (int i = 0; i < normalDays.length; i++) {
			if (i < 9) {
				normalDays[i] = "0" + (1 + i) + " 日";
			} else {
				normalDays[i] = (1 + i) + " 日";
			}
		}
		for (int i = 0; i < bigDays.length; i++) {
			if (i < 9) {
				bigDays[i] = "0" + (1 + i) + " 日";
			} else {
				bigDays[i] = (1 + i) + " 日";
			}
		}
		yearsAdapter = new StrericWheelAdapter(years);
		monthsAdapter = new StrericWheelAdapter(months);
		tinyDaysAdapter = new StrericWheelAdapter(tinyDays);
		smallDaysAdapter = new StrericWheelAdapter(smallDays);
		normalDaysAdapter = new StrericWheelAdapter(normalDays);
		bigDaysAdapter = new StrericWheelAdapter(bigDays);
		wvYear.setAdapter(yearsAdapter);
		wvYear.setCurrentItem(getTodayYear());
		wvYear.setCyclic(true);
		wvMonth.setAdapter(monthsAdapter);
		wvMonth.setCurrentItem(getTodayMonth());
		wvMonth.setCyclic(true);
		wvDay.setAdapter(normalDaysAdapter);
		wvDay.setCurrentItem(getTodayDay());
		wvDay.setCyclic(true);
	}
	/**
	 * 获取当前日期的天数的日子
	 * @return
	 */
	private int getTodayDay() {
		// 2015年12月01日
		int position = 0;
		String today = getToday();
		String day = today.substring(8, 10);
		day = day + " 日";
		for (int i = 0; i < bigDays.length; i++) {
			if (day.equals(bigDays[i])) {
				position = i;
				break;
			}
		}
		return position;
	}
	/**
	 * 获取当前日期的月数的位置
	 * @return
	 */
	private int getTodayMonth() {
		// 2015年12月01日
		int position = 0;
		String today = getToday();
		String month = today.substring(5, 7);
		month = month + " 月";
		for (int i = 0; i < months.length; i++) {
			if (month.equals(months[i])) {
				position = i;
				break;
			}
		}
		return position;
	}

	/**
	 * 获取当天的年份
	 * 
	 * @return
	 */
	private int getTodayYear() {
		int position = 0;
		String today = getToday();
		String year = today.substring(0, 4);
		year = year + " 年";
		for (int i = 0; i < years.length; i++) {
			if (year.equals(years[i])) {
				position = i;
				break;
			}
		}
		return position;
	}

	/**
	 * 设置当前显示的年份
	 * 
	 * @param year
	 */
	public void setCurrentYear(String year) {
		boolean overYear = true;
		year = year + " 年";
		for (int i = 0; i < years.length; i++) {
			if (year.equals(years[i])) {
				wvYear.setCurrentItem(i);
				overYear = false;
				break;
			}
		}
		if (overYear) {
			Log.e(flag, "设置的年份超出了数组的范围");
		}
	}

	/**
	 * 设置当前显示的月份
	 * 
	 * @param month
	 */
	public void setCurrentMonth(String month) {
		month = month + " 月";
		for (int i = 0; i < months.length; i++) {
			if (month.equals(months[i])) {
				wvMonth.setCurrentItem(i);
				break;
			}
		}
	}

	/**
	 * 设置当前显示的日期号
	 * 
	 * @param day
	 *            14
	 */
	public void setCurrentDay(String day) {
		day = day + " 日";
		for (int i = 0; i < smallDays.length; i++) {
			if (day.equals(smallDays[i])) {
				wvDay.setCurrentItem(i);
				break;
			}
		}
	}

	/**
	 * 获取选择的日期的值
	 * 
	 * @return
	 */
	public String getSelectedDate() {
		return tvYear.getText().toString().trim() + "-"
				+ tvMonth.getText().toString().trim() + "-"
				+ tvDay.getText().toString().trim();
	}

	/**
	 * 设置标题的点击事件
	 * 
	 * @param onClickListener
	 */
	public void setTitleClick(OnClickListener onClickListener) {
		rlTitle.setOnClickListener(onClickListener);
	}

	/**
	 * 设置日期选择器的日期转轮是否可见
	 * 
	 * @param visibility
	 */
	public void setDateSelectorVisiblility(int visibility) {
		llWheelViews.setVisibility(visibility);
	}

	public int getDateSelectorVisibility() {
		return llWheelViews.getVisibility();
	}

	/**
	 * 判断是否是闰年
	 * 
	 * @param year
	 * @return
	 */
	private boolean isLeapYear(String year) {
		int temp = Integer.parseInt(year);
		return temp % 4 == 0 && (temp % 100 != 0 || temp % 400 == 0) ? true
				: false;
	}

	/**
	 * 判断是否是大月
	 * 
	 * @param month
	 * @return
	 */
	private boolean isBigMonth(int month) {
		boolean isBigMonth = false;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			isBigMonth = true;
			break;

		default:
			isBigMonth = false;
			break;
		}
		return isBigMonth;
	}

	int currentMonth = 1;

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		String trim = null;
		switch (wheel.getId()) {
		case R.id.wv_date_of_year:
			trim = DateUtils.splitDateString(wvYear.getCurrentItemValue())
					.trim();
			tvYear.setText(trim);
			if (isLeapYear(trim)) {
				if (currentMonth == 2) {
					wvDay.setAdapter(smallDaysAdapter);
				} else if (isBigMonth(currentMonth)) {
					wvDay.setAdapter(bigDaysAdapter);
				} else {
					wvDay.setAdapter(normalDaysAdapter);
				}
			} else if (currentMonth == 2) {
				wvDay.setAdapter(tinyDaysAdapter);
			} else if (isBigMonth(currentMonth)) {
				wvDay.setAdapter(bigDaysAdapter);
			} else {
				wvDay.setAdapter(smallDaysAdapter);
			}
			break;
		case R.id.wv_date_of_month:
			trim = DateUtils.splitDateString(wvMonth.getCurrentItemValue())
					.trim();
			currentMonth = Integer.parseInt(trim);
			tvMonth.setText(trim);
			switch (currentMonth) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				wvDay.setAdapter(bigDaysAdapter);
				break;
			case 2:
				String yearString = DateUtils.splitDateString(
						wvYear.getCurrentItemValue()).trim();
				if (isLeapYear(yearString)) {
					wvDay.setAdapter(smallDaysAdapter);
				} else {
					wvDay.setAdapter(tinyDaysAdapter);
				}
				break;
			default:
				wvDay.setAdapter(smallDaysAdapter);
				break;
			}
			break;
		case R.id.wv_date_of_day:
			tvDay.setText(DateUtils.splitDateString(wvDay.getCurrentItemValue())
					.trim());
			break;
		}
	}

	/**
	 * 获取今天的日期
	 * 
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	private String getToday() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		return str;
	}

}
