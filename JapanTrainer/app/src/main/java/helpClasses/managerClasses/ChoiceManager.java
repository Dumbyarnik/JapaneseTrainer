package helpClasses.managerClasses;

import android.app.Activity;

public class ChoiceManager {

    // For initilialization of GlobalVariables
    Activity activity;
    GlobalVariables global;

    // Final variables for game regimes
    final static String syllables = "syllables";
    final static String words = "words";
    final static String images = "images";
    // Final variables for fonts
    final static String katakana = "katakana";
    final static String hiragana = "hiragana";
    final static String kanji = "kanji";
    //Final variables for letters
    final static String romaji = "romaji";
    final static String japanese = "japanese";



    public ChoiceManager(Activity activity){
        this.activity = activity;
        global = (GlobalVariables) activity.getApplication();
    }

    // Setters for game regimes
    public void setSyllables(){
        global.game = syllables;
    }
    public void setWords(){
        global.game = words;
    }
    public void setImages(){
        global.game = images;
    }

    // Setters for font
    public void setKatakana(){
        global.font = katakana;
    }
    public void setHiragana(){
        global.font = hiragana;
    }
    public void setKanji(){
        global.font = kanji;
    }

    // Setters for letter
    public void setRomaji(){
        global.letter =  romaji;
    }
    public void setJapanese(){
        global.letter = japanese;
    }




    // Checks for games
    public boolean isSyllables(){
        if (global.game.equals(syllables))
            return true;
        else
            return false;
    }
    public boolean isWords(){
        if (global.game.equals(words))
            return true;
        else
            return false;
    }
    public boolean isImages(){
        if (global.game.equals(images))
            return true;
        else
            return false;
    }

    // Checks for fonts
    public boolean isKatakana(){
        if (global.font.equals(katakana))
            return true;
        else
            return false;
    }
    public boolean isHiragana(){
        if (global.font.equals(hiragana))
            return true;
        else
            return false;
    }
    public boolean isKanji(){
        if (global.font.equals(kanji))
            return true;
        else
            return false;
    }

    // Checks for letters
    public boolean isRomaji(){
        if (global.letter.equals(romaji))
            return true;
        else
            return false;
    }
    public boolean isJapanese(){
        if (global.letter.equals(japanese))
            return true;
        else
            return false;
    }

}
