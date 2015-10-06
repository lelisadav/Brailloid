package daveyle.brailloid;

import android.view.View;

/**
 * Created by daveyle on 10/5/2015.
 */
public class MultiKeyTouchResult {
    public View view;
    public int event_type;

    public MultiKeyTouchResult() {
    }

    public int getEventType() {
        return event_type;
    }

    public void setEventType(int event_type) {
        this.event_type = event_type;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
