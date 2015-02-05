package com.summerxia.dateselector.animation;

import com.summerxia.dateselector.animation.FadeIn;
import com.summerxia.dateselector.animation.Fall;
import com.summerxia.dateselector.animation.FlipH;
import com.summerxia.dateselector.animation.FlipV;
import com.summerxia.dateselector.animation.NewsPaper;
import com.summerxia.dateselector.animation.RotateBottom;
import com.summerxia.dateselector.animation.RotateLeft;
import com.summerxia.dateselector.animation.Shake;
import com.summerxia.dateselector.animation.SideFall;
import com.summerxia.dateselector.animation.SlideBottom;
import com.summerxia.dateselector.animation.SlideLeft;
import com.summerxia.dateselector.animation.SlideRight;
import com.summerxia.dateselector.animation.SlideTop;
import com.summerxia.dateselector.animation.Slit;

/**
  * 类名称:   Effectstype 
  * 创建人:    xhl 
  * 创建时间:  2014-12-24 下午2:16:20     
  * 版本:      v1.0  
  * 类描述:	NiftyDialogBuilder的动画效果枚举类
 */
public enum Effectstype {

    Fadein(FadeIn.class),
    Slideleft(SlideLeft.class),
    Slidetop(SlideTop.class),
    SlideBottom(SlideBottom.class),
    Slideright(SlideRight.class),
    Fall(Fall.class),
    Newspager(NewsPaper.class),
    Fliph(FlipH.class),
    Flipv(FlipV.class),
    RotateBottom(RotateBottom.class),
    RotateLeft(RotateLeft.class),
    Slit(Slit.class),
    Shake(Shake.class),
    Sidefill(SideFall.class);
    private Class<? extends BaseEffects> effectsClazz;

    private Effectstype(Class<? extends BaseEffects> mclass) {
        effectsClazz = mclass;
    }

    public BaseEffects getAnimator() {
        BaseEffects bEffects=null;
	try {
		bEffects = effectsClazz.newInstance();
	} catch (ClassCastException e) {
		throw new Error("Can not init animatorClazz instance");
	} catch (InstantiationException e) {
		throw new Error("Can not init animatorClazz instance");
	} catch (IllegalAccessException e) {
		throw new Error("Can not init animatorClazz instance");
	}
	return bEffects;
    }
}
