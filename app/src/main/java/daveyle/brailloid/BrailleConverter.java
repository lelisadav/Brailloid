package daveyle.brailloid;

import java.util.ArrayList;
import java.util.HashMap;

import daveyle.brailloid.models.BrailleInput;
import daveyle.brailloid.models.BrailleLetter;

/**
 * Created by daveyle on 10/5/2015.
 */
public class BrailleConverter {
    HashMap<Integer, BrailleLetter> letterHashMap=new HashMap<>();
    ArrayList<BrailleLetter> half1=new ArrayList<>();
    ArrayList<BrailleLetter> half2=new ArrayList<>();
    public BrailleConverter(){
        BrailleLetter a=new BrailleLetter(1, "a", "\u2801");
        a.setNumber("1");
        BrailleLetter b=new BrailleLetter(1+2, "b","\u2803" );
        b.setNumber("2");
        BrailleLetter c=new BrailleLetter(1+8, "c", "\u2809");
        c.setNumber("3");
        BrailleLetter d=new BrailleLetter(1+8+16, "d", "\u2819" );
        d.setNumber("4");
        BrailleLetter e=new BrailleLetter(1+16, "e", "\u2811");
        e.setNumber("5");
        BrailleLetter f=new BrailleLetter(1+2+8, "f", "\u280b");
        f.setNumber("6");
        BrailleLetter g=new BrailleLetter(1+2+8+16, "g", "\u281b");
        g.setNumber("7");
        BrailleLetter h=new BrailleLetter(1+2+16, "h", "\u2813");
        h.setNumber("8");
        BrailleLetter i=new BrailleLetter(2+8, "i", "\u280a");
        i.setNumber("9");
        BrailleLetter j=new BrailleLetter(2+8+16, "j", "\u281a");
        j.setNumber("0");
        BrailleLetter k=new BrailleLetter(a.getBinVal()+4, "k", "\u2805");
        BrailleLetter l=new BrailleLetter(b.getBinVal()+4, "l", "\u2807");
        BrailleLetter m=new BrailleLetter(c.getBinVal()+4, "m", "\u280d");
        BrailleLetter n=new BrailleLetter(d.getBinVal()+4, "n", "\u281d");
        BrailleLetter o=new BrailleLetter(e.getBinVal()+4, "o", "\u2815");
        BrailleLetter p=new BrailleLetter(f.getBinVal()+4, "p", "\u280f");
        BrailleLetter q=new BrailleLetter(g.getBinVal()+4, "q", "\u281f");
        BrailleLetter r=new BrailleLetter(h.getBinVal()+4, "r", "\u2817");
        BrailleLetter s=new BrailleLetter(i.getBinVal()+4, "s", "\u280e");
        BrailleLetter t=new BrailleLetter(j.getBinVal()+4, "t", "\u281e");
        BrailleLetter u=new BrailleLetter(k.getBinVal()+32, "u", "\u2825");
        BrailleLetter v=new BrailleLetter(l.getBinVal()+32, "v", "\u2827");
        BrailleLetter x=new BrailleLetter(m.getBinVal()+32, "x", "\u282d");
        BrailleLetter y=new BrailleLetter(n.getBinVal()+32, "y", "\u283d");
        BrailleLetter z=new BrailleLetter(o.getBinVal()+32, "z", "\u2835");
        BrailleLetter w=new BrailleLetter(j.getBinVal()+32, "w", "\u283a");
        BrailleLetter[] arr={a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,BrailleLetter.CapitalInd, BrailleLetter.NumberInd};
        
        for (int index=0; index<arr.length; index++) {
            letterHashMap.put(arr[index].getBinVal(), arr[index]);
            half1.add(arr[index]);
        }
        half2.add(BrailleLetter.CommaEAInd);
        half2.add(new BrailleLetter(2 + 4, ";", "\u2806"));
        half2.add(new BrailleLetter(2 + 16, ":", "\u2812"));
        half2.add(new BrailleLetter(2 + 16 + 32, ".", "\u2832"));
        half2.add(new BrailleLetter(2 + 4 + 16, "!", "\u2816"));

        half2.add(BrailleLetter.ParenthesesInd);
        half2.add(BrailleLetter.QuestionMarkInd);
        half2.add(new BrailleLetter(4 + 16 + 32, "\"", "\u2834"));
        half2.add(BrailleLetter.LetterInd);
        half2.add(new BrailleLetter(4, "'", "\u2804"));

        half2.add(new BrailleLetter(4 + 32, "-", "\u2824"));
        half2.add(BrailleLetter.SpaceInd);
        half2.add(new BrailleLetter(4 + 8, "st", "\u280c"));
        half2.add(new BrailleLetter(4 + 8 + 16, "ar", "\u281c"));
        half2.add(new BrailleLetter(1 + 2 + 4 + 8 + 32, "and", "\u282f"));

        half2.add(new BrailleLetter(1 + 2 + 4 + 8 + 16 + 32, "for", "\u283f"));
        half2.add(new BrailleLetter(1 + 2 + 4 + 16 + 32, "of", "\u2837"));
        half2.add(new BrailleLetter(2 + 4 + 8 + 32, "the", "\u282e"));
        half2.add(new BrailleLetter(2 + 4 + 8 + 16 + 32, "with", "\u283e"));
        half2.add(new BrailleLetter(4 + 8 + 32, "ing", "\u282c"));

        half2.add(new BrailleLetter(1 + 32, "ch", "\u2821"));
        half2.add(new BrailleLetter(1 + 2 + 32, "gh", "\u2823"));
        half2.add(new BrailleLetter(1 + 8 + 32, "sh", "\u2829"));
        half2.add(new BrailleLetter(1 + 8 + 16 + 32, "th", "\u2839"));
        half2.add(new BrailleLetter(1 + 16 + 32, "wh", "\u2831"));

        half2.add(new BrailleLetter(1 + 2 + 8 + 32, "ed", "\u282b"));
        half2.add(new BrailleLetter(1 + 2 + 8 + 16 + 32, "er", "\u283b"));
        half2.add(new BrailleLetter(1 + 2 + 16 + 32, "ou", "\u2833"));
        half2.add(new BrailleLetter(2 + 8 + 32, "ow", "\u282a"));
        half2.add(new BrailleLetter(2 + 32, "en", "\u2822"));

        half2.add(new BrailleLetter(4 + 16, "in", "\u2814"));

        for (int index=0; index<half2.size(); index++) {
            letterHashMap.put(half2.get(index).getBinVal(), half2.get(index));

        }




    }
    public BrailleLetter getLetter(BrailleInput input){
        return letterHashMap.get(input.getBinValue());
    }

}
