package daveyle.brailloid.controllers;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import daveyle.brailloid.BrailleConverter;
import daveyle.brailloid.MultiKeyTouchListener;
import daveyle.brailloid.MultiKeyTouchResult;
import daveyle.brailloid.R;
import daveyle.brailloid.models.BrailleInput;
import daveyle.brailloid.models.BrailleLetter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link KeyboardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link KeyboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KeyboardFragment extends Fragment {

    ImageButton imageButton;
    ImageButton imageButton2;
    ImageButton imageButton3;
    ImageButton imageButton4;
    ImageButton imageButton5;
    ImageButton imageButton6;
    TextView tv;
    TextView tv2;
    Button button;
    private OnFragmentInteractionListener mListener;
    private ArrayList<BrailleInput> input=new ArrayList<>();
    private BrailleInput currLetter=new BrailleInput();
    MultiKeyTouchListener listener=new MultiKeyTouchListener();
    private View.OnTouchListener listener_touch_button= new View.OnTouchListener(){
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            MultiKeyTouchResult result= listener.onTouchEvent(v, event);
            if (result==null){return true;}
            switch (result.event_type){
                case MotionEvent.ACTION_DOWN:
                    doDown(result.getView());
                    break;
                case MotionEvent.ACTION_UP:
                    doUp(result.getView());
                    break;
                case MotionEvent.ACTION_CANCEL:
                    doCancel(result.getView());
                    break;
                default:
                    break;

            }
            return false;
        }

    };


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KeyboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KeyboardFragment newInstance(String param1, String param2) {
        KeyboardFragment fragment = new KeyboardFragment();

        return fragment;
    }

    public KeyboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_main, container, false);
        imageButton=(ImageButton) view.findViewById(R.id.imageButton);
        imageButton2=(ImageButton) view.findViewById(R.id.imageButton2);
        imageButton3=(ImageButton) view.findViewById(R.id.imageButton3);
        imageButton4=(ImageButton) view.findViewById(R.id.imageButton4);
        imageButton5=(ImageButton) view.findViewById(R.id.imageButton5);
        imageButton6=(ImageButton) view.findViewById(R.id.imageButton6);
        button=(Button) view.findViewById(R.id.button);
        listener.addMultiTouchView(imageButton);
        listener.addMultiTouchView(imageButton2);
        listener.addMultiTouchView(imageButton3);
        listener.addMultiTouchView(imageButton4);
        listener.addMultiTouchView(imageButton5);
        listener.addMultiTouchView(imageButton6);
        listener.addMultiTouchView(button);
        imageButton.setOnTouchListener(listener_touch_button);
        imageButton2.setOnTouchListener(listener_touch_button);
        imageButton3.setOnTouchListener(listener_touch_button);
        imageButton4.setOnTouchListener(listener_touch_button);
        imageButton5.setOnTouchListener(listener_touch_button);
        imageButton6.setOnTouchListener(listener_touch_button);
        button.setOnTouchListener(listener_touch_button);
        tv=(TextView)view.findViewById(R.id.textView);
        tv.setText("");
        tv2=(TextView)view.findViewById(R.id.textView2);
        tv2.setText("");
        Button button2= (Button) view.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = new ArrayList<BrailleInput>();
                refresh();
            }
        });
        Button button3= (Button) view.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.size()>=1) {
                    input.remove(input.size() - 1);
                }
                refresh();
            }
        });
        return view;

    }
    private void refresh(){
        String s="";
        String s2="";
        BrailleConverter bc= new BrailleConverter();
        boolean nextCapital=false;
        boolean isNumbers=false;
        for (BrailleInput i : input){
            if (bc.getLetter(i)==BrailleLetter.SpaceInd){
                Log.wtf("brailloid", "Space!");
            }
            if (bc.getLetter(i)!=null) {
                s += bc.getLetter(i).getBraille();
                if (bc.getLetter(i).getBinVal()== BrailleLetter.CapitalInd.getBinVal()) {
                    nextCapital = true;
                }
                else if (bc.getLetter(i).getBinVal()==BrailleLetter.NumberInd.getBinVal()){
                    isNumbers=true;

                }
                else if (bc.getLetter(i).getBinVal()==BrailleLetter.LetterInd.getBinVal()){
                    isNumbers=false;
                }
                else if (bc.getLetter(i).getBinVal()==BrailleLetter.SpaceInd.getBinVal()){
                    isNumbers=false;
                    s2+=bc.getLetter(i).getLetter();
                }
                else{
                   if (nextCapital){
                        String s3=bc.getLetter(i).getLetter();
                        s2+=(s3.charAt(0)+"").toUpperCase()+s3.substring(1);
                        nextCapital=false;
                    }
                    else if (isNumbers){
                        s2+=bc.getLetter(i).getNumber();
                    }
                    else{
                        s2+= bc.getLetter(i).getLetter();
                    }
                }



            }
        }
        tv.setText(s);
        tv2.setText(s2);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public void doDown(View v){
        if (v.equals(imageButton)){
            Log.wtf("brailloid", "1");
            currLetter.setKey(true, 0);
        }
        else if (v.equals(imageButton2)){
            Log.wtf("brailloid", "2");
            currLetter.setKey(true, 1);
        }
        else if (v.equals(imageButton3)){
            Log.wtf("brailloid", "3");
            currLetter.setKey(true, 2);
        }
        else if (v.equals(imageButton4)){
            Log.wtf("brailloid", "4");
            currLetter.setKey(true, 3);
        }
        else if (v.equals(imageButton5)){
            Log.wtf("brailloid", "5");
            currLetter.setKey(true, 4);
        }
        else if (v.equals(imageButton6)){
            Log.wtf("brailloid", "6");
            currLetter.setKey(true, 5);
        }
        else if (v.equals(button)){
            Log.wtf("brailloid", "7");
            moveToNextLetter();
            refresh();
            //mListener.onLettersEntered(input);
        }

    }
    public void doUp(View v){
        if (v.equals(imageButton)){
            currLetter.setKey(false, 0);
        }
        else if (v.equals(imageButton2)){
            currLetter.setKey(false, 1);
        }
        else if (v.equals(imageButton3)){
            currLetter.setKey(false, 2);
        }
        else if (v.equals(imageButton4)){
            currLetter.setKey(false, 3);
        }
        else if (v.equals(imageButton5)){
            currLetter.setKey(false, 4);
        }
        else if (v.equals(imageButton6)){
            currLetter.setKey(false, 5);
        }
        else if (v.equals(button)){
            //moveToNextLetter();
        }

    }
    public void doCancel(View v){
        currLetter=new BrailleInput();
        moveToNextLetter();

    }
    public void moveToNextLetter(){
        input.add(currLetter);
        currLetter=new BrailleInput();
        
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onLettersEntered(List<BrailleInput> inputs);
    }



}
