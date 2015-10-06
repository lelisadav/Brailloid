package daveyle.brailloid;

import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by daveyle on 10/5/2015.
 */
public class MultiKeyTouchListener {
    ArrayList<View> views=new ArrayList<View>();
    public MultiKeyTouchResult onTouchEvent(View v, MotionEvent event){
        //Log.wtf("brailloid", "All");
        final int action= event.getAction();
        if ((action & MotionEvent.ACTION_MASK)==MotionEvent.ACTION_MOVE){
            return null;
        }
        final int orig_loc[] = {0, 0};
        v.getLocationOnScreen(orig_loc);
        final int actionPointerIndex=event.getActionIndex();
        float rawX=(int) event.getX(actionPointerIndex)+orig_loc[0];
        float rawY=(int) event.getY(actionPointerIndex)+orig_loc[1];
        View eventView= getViewByLocation((int)rawX, (int) rawY);
        //Log.wtf("brailloid", eventView+"");
        if (eventView==null){return null;}
        MultiKeyTouchResult result=new MultiKeyTouchResult();
        result.setView(eventView);
        switch(action & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:{
                result.setEventType(MotionEvent.ACTION_DOWN);
                return result;

            }
            case MotionEvent.ACTION_UP:{
                result.setEventType(MotionEvent.ACTION_UP);
                return result;
            }
            case MotionEvent.ACTION_CANCEL:{
                result.setEventType(MotionEvent.ACTION_CANCEL);
                return result;
            }
            case MotionEvent.ACTION_POINTER_UP:{
                result.setEventType(MotionEvent.ACTION_UP);
                return result;
            }
            case MotionEvent.ACTION_POINTER_DOWN:{
                result.setEventType(MotionEvent.ACTION_UP);
                return result;
            }
            default:
                break;

        }
        return null;
    }
    public void addMultiTouchView(View v){
        views.add(v);
    }
    public void removeMultiTouchView(View v){
        views.remove(v);
    }
    public View getViewByLocation(int x, int y){
        //Log.wtf("brailloid", views.size()+"");
        for (int key=0; key!=views.size(); key++){
            View v=views.get(key);
            int[] v_loc={0,0};
            v.getLocationOnScreen(v_loc);
            //Log.wtf("brailloid", v_loc+"test");
            Point min=new Point(v_loc[0], v_loc[1]);
            Point max=new Point(min.x+v.getWidth(), min.y+v.getHeight());
            if (x>=min.x && x<=max.x && y>=min.y && y<=max.y){
                return v;
            }
        }
        return null;
    }

}
