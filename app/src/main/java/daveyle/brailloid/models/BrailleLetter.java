package daveyle.brailloid.models;

/**
 * Created by daveyle on 10/5/2015.
 */
public class BrailleLetter {
    private String letter;
    private final int binVal;
    private String braille;
    private String number;

    public BrailleLetter(int binVal, String letter, String braille) {
        this.binVal = binVal;
        this.letter = letter;
        this.braille=braille;
    }

    public int getBinVal() {
        return binVal;
    }

    public String getLetter() {
        return letter;
    }

    public String getBraille() {
        return braille;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public static BrailleLetter CapitalInd=new BrailleLetter(32, "CAPITAL", "\u2820");

    public static BrailleLetter NumberInd =new BrailleLetter(4+8+16+32, "NUMBER","\u283c" );

    public static BrailleLetter ParenthesesInd =new BrailleLetter(2+4+16+32, "PARENTHESES", "\u2836");

    public static BrailleLetter QuestionMarkInd =new BrailleLetter(2+4+32, "?", "\u2826"); //? "

    public static BrailleLetter CommaEAInd=new BrailleLetter(2, ",", "\u2802"); //, ea

    public static BrailleLetter LetterInd=new BrailleLetter(16+32, "LETTER", "\u2830");

    public static BrailleLetter SpaceInd=new BrailleLetter(0, " ", "\u2800");

}
