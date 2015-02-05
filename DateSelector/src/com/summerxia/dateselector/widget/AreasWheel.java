package com.summerxia.dateselector.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.summerxia.dateselector.R;
import com.summerxia.dateselector.wheelview.CityWheelAdapter;
import com.summerxia.dateselector.wheelview.OnWheelChangedListener;
import com.summerxia.dateselector.wheelview.ProvinceWheelAdapter;
import com.summerxia.dateselector.wheelview.WheelView;
/**
  * 类描述:	地址选择器的自定义布局   
  * 项目名称:  DateSelector   
  * 类名称:   AreasWheel 
  * 创建人:    xhl  
  * 创建时间:  2015-2-5 上午10:11:53     
  * 版本:      v1.0
 */
public class AreasWheel extends LinearLayout {
	private WheelView wv_province;
	private WheelView wv_city;
	public int screenheight;
	private Context context;
	private OnWheelChangedListener provinceChangedListener;
	private CityWheelAdapter cityWheelAdapter;
	private ProvinceWheelAdapter provinceWheelAdapter;
	/**
	 * 城市列表
	 */
	private final int[] ARRAY_CITY = new int[] { R.array.beijin_province_item,
			R.array.heibei_province_item, R.array.shandong_province_item,
			R.array.shanghai_province_item, R.array.guangdong_province_item,
			R.array.anhui_province_item, R.array.fujian_province_item,
			R.array.gansu_province_item, R.array.guangxi_province_item,
			R.array.guizhou_province_item, R.array.hainan_province_item,
			R.array.henan_province_item, R.array.heilongjiang_province_item,
			R.array.hubei_province_item, R.array.hunan_province_item,
			R.array.jilin_province_item, R.array.jiangsu_province_item,
			R.array.jiangxi_province_item, R.array.liaoning_province_item,
			R.array.neimenggu_province_item, R.array.ningxia_province_item,
			R.array.qinghai_province_item, R.array.shanxi1_province_item,
			R.array.shanxi2_province_item, R.array.sichuan_province_item,
			R.array.tianjin_province_item, R.array.xizang_province_item,
			R.array.xinjiang_province_item, R.array.yunnan_province_item,
			R.array.zhejiang_province_item, R.array.chongqing_province_item,
			R.array.taiwan_province_item, R.array.hongkong_province_item,
			R.array.aomen_province_item };
	

	public AreasWheel(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		initView();
	}

	public AreasWheel(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initView();
	}

	public AreasWheel(Context context) {
		super(context);
		this.context = context;
		initView();
	}

	private void initView() {
		LayoutInflater.from(context).inflate(
				R.layout.province_city_selector_layout, this, true);
		wv_province = (WheelView) findViewById(R.id.wv_province);
		wv_city = (WheelView) findViewById(R.id.wv_city);

		provinceWheelAdapter = new ProvinceWheelAdapter(context);
		wv_province.setAdapter(provinceWheelAdapter);
		wv_province.setCyclic(false);
		wv_province.setVisibleItems(5);
		wv_province.setCurrentItem(0);

		cityWheelAdapter = new CityWheelAdapter(context,
				R.array.beijin_province_item);
		wv_city.setAdapter(cityWheelAdapter);
		wv_city.setCyclic(false);
		wv_city.setVisibleItems(5);

		provinceChangedListener = new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				cityWheelAdapter.setCityList(ARRAY_CITY[newValue]);
				wv_city.setAdapter(cityWheelAdapter);
				wv_city.setCurrentItem(0);
			}
		};
		wv_province.addChangingListener(provinceChangedListener);
	}

	/**
	 * 获取省市字符串
	 * 
	 * @return
	 */
	public String getArea() {
		return wv_province.getCurrentItemValue() + " "
				+ wv_city.getCurrentItemValue();
	}
	/**
	 * 获取省份的Id
	 * @return
	 */
	public String getProvinceId(){
		return wv_province.getCurrentItemId();
	}
	/**
	 * 获取城市的Id
	 * @return
	 */
	public String getCityId(){
		return wv_city.getCurrentItemId();
	}

}
