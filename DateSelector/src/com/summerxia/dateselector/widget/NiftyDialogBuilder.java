package com.summerxia.dateselector.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.summerxia.dateselector.R;
import com.summerxia.dateselector.animation.BaseEffects;
import com.summerxia.dateselector.animation.Effectstype;

/**
 * 
 * @author xhl 2014-10-05
 */
public class NiftyDialogBuilder extends Dialog implements DialogInterface {
	/**
	 * 默认文本颜色
	 */
	private final String defTextColor = "#FFFFFFFF";
	/**
	 * 默认分割线颜色
	 */
	private final String defDividerColor = "#11000000";
	/**
	 * 默认消息文本颜色
	 */
	private final String defMsgColor = "#FFFFFFFF";
	/**
	 * 默认Dialog背景颜色
	 */
	private final String defDialogColor = "#FFE74C3C";
	/**
	 * Dialog动画效果
	 */
	private Effectstype type = null;

	private LinearLayout mLinearLayoutView;

	private RelativeLayout mRelativeLayoutView;
	/**
	 * 消息布局文件
	 */
	private LinearLayout mLinearLayoutMsgView;
	/**
	 * 顶部标题Title布局
	 */
	private LinearLayout mLinearLayoutTopView;
	/**
	 * 用户自动以布局容器
	 */
	private FrameLayout mFrameLayoutCustomView;
	/**
	 * 标题栏右侧自定义布局容器
	 */
	private FrameLayout mNextCustomLayout;
	/**
	 * Dialog对象
	 */
	private View mDialogView;
	/**
	 * 分割线对象
	 */
	private View mDivider;
	/**
	 * 标题栏TextView对象
	 */
	private TextView mTitle;
	/**
	 * 消息TextView对象
	 */
	private TextView mMessage;
	/**
	 * 标题栏左侧布局
	 */
	private FrameLayout mPreviousLayout;
	/**
	 * 标题栏左侧图片
	 */
	private ImageView mIcon;
	/**
	 * 标题栏左侧文字
	 */
	private TextView mPreviousText;
	/**
	 * 标题栏右侧布局
	 */
	private FrameLayout mNextLayout;
	/**
	 * 标题栏右侧图片
	 */
	private ImageView mNextImage;
	/**
	 * 标题栏右侧文本
	 */
	private TextView mNextText;
	/**
	 * 左侧按钮
	 */
	private Button mButton1;
	/**
	 * 右侧按钮
	 */
	private Button mButton2;
	/**
	 * 默认动画执行时间
	 */
	private int mDuration = -1;
	/**
	 * 默认方向标示
	 */
	private static int mOrientation = 1;
	/**
	 * 点击Dialog以外的区域是否取消Dialog
	 */
	private boolean isCancelable = true;
	/**
	 * Dialog创建器
	 */
	private volatile static NiftyDialogBuilder instance;
	/**
	 * 窗体的宽度
	 */
	private int windowWidth = 0;
	/**
	 * 窗体的高度
	 */
	private int windowHeight = 0;
	
	private Context context;
	

	public NiftyDialogBuilder(Context context) {
		super(context);
		this.context = context;
		init(context);
	}

	public NiftyDialogBuilder(Context context, int theme) {
		super(context, theme);
		this.context = context;
		init(context);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WindowManager.LayoutParams params = getWindow().getAttributes();
		//设置窗体的宽高来控制Dialog的大小
		if (windowWidth == 0 || windowHeight == 0) {
			params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
			params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
		} else {
			params.width = windowWidth;
			params.height = windowHeight;
		}
		getWindow().setAttributes(
				(android.view.WindowManager.LayoutParams) params);
		getWindow().setBackgroundDrawableResource(
				R.drawable.background_transparent);
	}

	public static NiftyDialogBuilder getInstance(Context context) {

		int ort = context.getResources().getConfiguration().orientation;
		if (mOrientation != ort) {
			mOrientation = ort;
			instance = null;
		}

		if (instance == null || ((Activity) context).isFinishing()) {
			synchronized (NiftyDialogBuilder.class) {
				if (instance == null) {
					instance = new NiftyDialogBuilder(context,
							R.style.dialog_untran);
				}
			}
		}
		return instance;

	}

	private void init(Context context) {
		
		mDialogView = View.inflate(context, R.layout.dialog_layout, null);
		mLinearLayoutView = (LinearLayout) mDialogView
				.findViewById(R.id.parentPanel);
		mRelativeLayoutView = (RelativeLayout) mDialogView
				.findViewById(R.id.main);
		mLinearLayoutTopView = (LinearLayout) mDialogView
				.findViewById(R.id.topPanel);
		mLinearLayoutMsgView = (LinearLayout) mDialogView
				.findViewById(R.id.contentPanel);
		mFrameLayoutCustomView = (FrameLayout) mDialogView
				.findViewById(R.id.customPanel);
		mNextCustomLayout = (FrameLayout) mDialogView
				.findViewById(R.id.fl_custom_next_layout);
		mNextCustomLayout.setVisibility(View.GONE);

		mTitle = (TextView) mDialogView.findViewById(R.id.alertTitle);
		mMessage = (TextView) mDialogView.findViewById(R.id.message);

		mPreviousLayout = (FrameLayout) mDialogView
				.findViewById(R.id.fl_dialog_title_previous);
		mIcon = (ImageView) mDialogView.findViewById(R.id.icon);
		mPreviousText = (TextView) mDialogView
				.findViewById(R.id.tv_dialog_title_previous_text);
		mNextLayout = (FrameLayout) mDialogView
				.findViewById(R.id.fl_dialog_title_next);
		mNextLayout.setVisibility(View.VISIBLE);
		mNextImage = (ImageView) mDialogView
				.findViewById(R.id.iv_dialog_title_next_img);
		mNextText = (TextView) mDialogView
				.findViewById(R.id.tv_dialog_title_next_text);

		mDivider = mDialogView.findViewById(R.id.titleDivider);
		mButton1 = (Button) mDialogView.findViewById(R.id.button1);
		mButton2 = (Button) mDialogView.findViewById(R.id.button2);

		setContentView(mDialogView);

		this.setOnShowListener(new OnShowListener() {
			@Override
			public void onShow(DialogInterface dialogInterface) {

				mLinearLayoutView.setVisibility(View.VISIBLE);
				if (type == null) {
					// Dialog默认的动画效果
					type = Effectstype.Fall;
				}
				start(type);
			}
		});
		mRelativeLayoutView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (isCancelable)
					dismiss();
			}
		});
	}

	public void toDefault() {
		mTitle.setTextColor(Color.parseColor(defTextColor));
		mDivider.setBackgroundColor(Color.parseColor(defDividerColor));
		mMessage.setTextColor(Color.parseColor(defMsgColor));
		mLinearLayoutView.setBackgroundColor(Color.parseColor(defDialogColor));
	}
	/**
	 * 设置窗体的宽高大小;默认的宽高为WRAP_CONTENT
	 * @param width
	 * @param height
	 * @return
	 */
	public NiftyDialogBuilder withDialogWindows(int width, int height){
		//设置窗体的宽高来控制Dialog的大小
		windowWidth = width;
		windowHeight = height;
		return this;
	}
	/**
	 * 设置Dialog的背景颜色
	 * 
	 * @param colorString
	 *            具体的颜色值，如红色："#FF0000"
	 * @return
	 */
	public NiftyDialogBuilder withDialoagBackGround(String colorString) {
		mLinearLayoutView.setBackgroundColor(Color.parseColor(colorString));
		return this;
	}

	/**
	 * 设置消息是否可见
	 * 
	 * @param visibility
	 * @return
	 */
	public NiftyDialogBuilder withMessageMiss(int visibility) {
		mLinearLayoutMsgView.setVisibility(visibility);
		return this;
	}

	/**
	 * 设置分割线的颜色
	 * 
	 * @param colorString
	 *            具体的颜色值，如红色："#FF0000"
	 * @return
	 */
	public NiftyDialogBuilder withDividerColor(String colorString) {
		mDivider.setBackgroundColor(Color.parseColor(colorString));
		return this;
	}

	/**
	 * 设置Dialog的标题
	 * 
	 * @param title
	 *            标题
	 * @return
	 */
	public NiftyDialogBuilder withTitle(CharSequence title) {
		toggleView(mLinearLayoutTopView, title);
		mTitle.setText(title);
		return this;
	}

	/**
	 * 设置标题文字的颜色
	 * 
	 * @param colorString
	 *            具体的颜色值，如红色："#FF0000"
	 * @return
	 */
	public NiftyDialogBuilder withTitleColor(String colorString) {
		mTitle.setTextColor(Color.parseColor(colorString));
		return this;
	}

	/**
	 * 设置消息的文字
	 * 
	 * @param textResId
	 * @return
	 */
	public NiftyDialogBuilder withMessage(int textResId) {
		toggleView(mLinearLayoutMsgView, textResId);
		mMessage.setText(textResId);
		return this;
	}

	/**
	 * 设置消息的内容
	 * 
	 * @param msg
	 *            具体的消息文字
	 * @return
	 */
	public NiftyDialogBuilder withMessage(CharSequence msg) {
		toggleView(mLinearLayoutMsgView, msg);
		mMessage.setText(msg);
		return this;
	}

	/**
	 * 设置消息的文字颜色
	 * 
	 * @param colorString
	 *            具体的颜色值，如红色："#FF0000"
	 * @return
	 */
	public NiftyDialogBuilder withMessageColor(String colorString) {
		mMessage.setTextColor(Color.parseColor(colorString));
		return this;
	}

	/**
	 * 设置标题左侧的图标
	 * 
	 * @param drawableResId
	 *            图标的资源id
	 * @return
	 */
	public NiftyDialogBuilder withIcon(int drawableResId) {
		mIcon.setVisibility(View.VISIBLE);
		mIcon.setImageResource(drawableResId);
		return this;
	}

	/**
	 * 设置标题左侧的图标
	 * 
	 * @param icon
	 * @return
	 */
	public NiftyDialogBuilder withIcon(Drawable icon) {
		mIcon.setVisibility(View.VISIBLE);
		mIcon.setImageDrawable(icon);
		return this;
	}

	/**
	 * 设置标题左侧的文字
	 * 
	 * @param text
	 * @return
	 */
	public NiftyDialogBuilder withPreviousText(String text) {
		mPreviousText.setVisibility(View.VISIBLE);
		mPreviousText.setText(text);
		return this;
	}

	/**
	 * 设置左侧文字的颜色
	 * 
	 * @param colorString
	 * @return
	 */
	public NiftyDialogBuilder withPreviousTextColor(String colorString) {
		mPreviousText.setTextColor(Color.parseColor(colorString));
		return this;
	}

	/**
	 * 设置标题栏右侧图标
	 * 
	 * @param drawableResId
	 * @return
	 */
	public NiftyDialogBuilder withNextImage(int drawableResId) {
		mNextLayout.setVisibility(View.VISIBLE);
		mNextCustomLayout.setVisibility(View.GONE);
		mNextImage.setVisibility(View.VISIBLE);
		mNextImage.setImageResource(drawableResId);
		return this;
	}

	/**
	 * 设置标题栏右侧图标
	 * 
	 * @param icon
	 * @return
	 */
	public NiftyDialogBuilder withNextImage(Drawable icon) {
		mNextLayout.setVisibility(View.VISIBLE);
		mNextCustomLayout.setVisibility(View.GONE);
		mNextImage.setVisibility(View.VISIBLE);
		mNextImage.setImageDrawable(icon);
		return this;
	}

	/**
	 * 设置标题栏右侧文字
	 * 
	 * @param text
	 * @return
	 */
	public NiftyDialogBuilder withNextText(String text) {
		mNextLayout.setVisibility(View.VISIBLE);
		mNextCustomLayout.setVisibility(View.GONE);
		mNextText.setVisibility(View.VISIBLE);
		mNextText.setText(text);
		return this;
	}

	/**
	 * 设置标题右侧文字的颜色
	 * 
	 * @param colorString
	 * @return
	 */
	public NiftyDialogBuilder withNextTextColor(String colorString) {
		mNextText.setTextColor(Color.parseColor(colorString));
		return this;
	}

	/**
	 * 设置Dialog效果执行的时间
	 * 
	 * @param duration
	 * @return
	 */
	public NiftyDialogBuilder withDuration(int duration) {
		this.mDuration = duration;
		return this;
	}

	/**
	 * 设置Dialog的动画效果
	 * 
	 * @param type
	 * @return
	 */
	public NiftyDialogBuilder withEffect(Effectstype type) {
		this.type = type;
		return this;
	}

	/**
	 * 设置按钮的样式
	 * 
	 * @param resid
	 * @return
	 */
	public NiftyDialogBuilder withButtonDrawable(int resid) {
		mButton1.setBackgroundResource(resid);
		mButton2.setBackgroundResource(resid);
		return this;
	}

	/**
	 * 设置左侧按钮的文字
	 * 
	 * @param text
	 * @return
	 */
	public NiftyDialogBuilder withButton1Text(CharSequence text) {
		mButton1.setVisibility(View.VISIBLE);
		mButton1.setText(text);

		return this;
	}

	/**
	 * 设置右侧按钮的文字
	 * 
	 * @param text
	 * @return
	 */
	public NiftyDialogBuilder withButton2Text(CharSequence text) {
		mButton2.setVisibility(View.VISIBLE);
		mButton2.setText(text);
		return this;
	}

	/**
	 * 设置左侧按钮的点击事件
	 * 
	 * @param click
	 * @return
	 */
	public NiftyDialogBuilder setButton1Click(View.OnClickListener click) {
		mButton1.setOnClickListener(click);
		return this;
	}

	/**
	 * 设置右侧按钮的点击事件
	 * 
	 * @param click
	 * @return
	 */
	public NiftyDialogBuilder setButton2Click(View.OnClickListener click) {
		mButton2.setOnClickListener(click);
		return this;
	}

	/**
	 * 设置标题栏左侧内容点击事件
	 * 
	 * @param click
	 * @return
	 */
	public NiftyDialogBuilder setPreviousLayoutClick(View.OnClickListener click) {
		mPreviousLayout.setOnClickListener(click);
		return this;
	}

	/**
	 * 设置标题栏右侧内容点击事件
	 * 
	 * @param click
	 * @return
	 */
	public NiftyDialogBuilder setNextLayoutClick(View.OnClickListener click) {
		mNextLayout.setOnClickListener(click);
		return this;
	}

	public NiftyDialogBuilder setDialogClick(View.OnClickListener click) {
		mRelativeLayoutView.setOnClickListener(click);
		return this;
	}

	/**
	 * 设置自定义布局
	 * 
	 * @param resId
	 * @param context
	 * @return
	 */
	public NiftyDialogBuilder setCustomView(int resId, Context context) {
		View customView = View.inflate(context, resId, null);
		if (mFrameLayoutCustomView.getChildCount() > 0) {
			mFrameLayoutCustomView.removeAllViews();
		}
		mFrameLayoutCustomView.addView(customView);
		return this;
	}

	/**
	 * 设置自定义布局
	 * 
	 * @param view
	 * @param context
	 * @return
	 */
	public NiftyDialogBuilder setCustomView(View view, Context context) {
		if (mFrameLayoutCustomView.getChildCount() > 0) {
			mFrameLayoutCustomView.removeAllViews();
		}
		mFrameLayoutCustomView.addView(view);

		return this;
	}
	/**
	 * 设置下一步的自定义布局
	 * @param resId
	 * @param context
	 * @return
	 */
	public NiftyDialogBuilder setNextCustomView(int resId, Context context){
		mNextLayout.setVisibility(View.GONE);
		mNextCustomLayout.setVisibility(View.VISIBLE);
		View nextCustonView = View.inflate(context, resId, null);
		if (mNextCustomLayout.getChildCount() > 0) {
			mNextCustomLayout.removeAllViews();
		}
		mNextCustomLayout.addView(nextCustonView);
		return this;
	}
	/**
	 * 设置下一步的自定义布局
	 * @param view
	 * @param context
	 * @return
	 */
	public NiftyDialogBuilder setNextCustomView(View view, Context context){
		mNextLayout.setVisibility(View.GONE);
		mNextCustomLayout.setVisibility(View.VISIBLE);
		if (mNextCustomLayout.getChildCount() > 0) {
			mNextCustomLayout.removeAllViews();
		}
		mNextCustomLayout.addView(view);
		return this;
	}
	/**
	 * 设置是否点击Dialog以外的地方，Dialog是否关闭
	 * 
	 * @param cancelable
	 * @return
	 */
	public NiftyDialogBuilder isCancelableOnTouchOutside(boolean cancelable) {
		this.isCancelable = cancelable;
		this.setCanceledOnTouchOutside(cancelable);
		return this;
	}

	/**
	 * 设置点击Dialog非内容部分是否关闭Dialog
	 * 
	 * @param cancelable
	 * @return
	 */
	public NiftyDialogBuilder isCancelable(boolean cancelable) {
		this.isCancelable = cancelable;
		this.setCancelable(cancelable);
		return this;
	}

	private void toggleView(View view, Object obj) {
		if (obj == null) {
			view.setVisibility(View.GONE);
		} else {
			view.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void show() {

		super.show();
	}

	private void start(Effectstype type) {
		BaseEffects animator = type.getAnimator();
		if (mDuration != -1) {
			animator.setDuration(Math.abs(mDuration));
		}
		animator.start(mRelativeLayoutView);
	}

	@Override
	public void dismiss() {
		super.dismiss();
		mButton1.setVisibility(View.GONE);
		mButton2.setVisibility(View.GONE);
	}

	/**
	 * 获取用户自定义的布局
	 * 
	 * @return
	 */
	public FrameLayout getCustomLayout() {
		return mFrameLayoutCustomView;
	}

	/**
	 * 获取Title左侧文本对象
	 * 
	 * @return
	 */
	public TextView getPreviousTextView() {
		return mPreviousText;
	}
}
