package com.summerxia.dateselector.wheelview;

import java.util.Arrays;
import java.util.List;

import android.content.Context;

public class CityWheelAdapter implements WheelAdapter {
	
	private List<String> cities;
	private Context context;
	
	public CityWheelAdapter(Context context, int provinceId){
		this.context = context;
		this.cities = Arrays.asList(context.getResources().getStringArray(provinceId));
	}

	@Override
	public int getItemsCount() {
		return cities == null ? 0 : cities.size();
	}

	@Override
	public String getItem(int index) {
		return index <= cities.size() - 1 ? cities.get(index) : null;
	}

	@Override
	public int getMaximumLength() {
		return 7;
	}
	
	public void setCityList(int provinceId){
		this.cities = Arrays.asList(context.getResources().getStringArray(provinceId));
	}

	@Override
	public String getCurrentId(int index) {
		return "";
	}
}
