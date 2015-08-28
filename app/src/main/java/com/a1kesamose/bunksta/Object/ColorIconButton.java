package com.a1kesamose.bunksta.Object;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class ColorIconButton extends Button
{
    public ColorIconButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if(width > (int)(height + 0.5))
        {
            width = (int)(height + 0.5);
        }
        else
        {
            height = (int) (width + 0.5);
        }

        super.onMeasure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }
}
