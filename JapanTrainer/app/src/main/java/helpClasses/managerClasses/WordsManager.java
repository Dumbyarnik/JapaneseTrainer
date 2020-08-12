
// Class for giving words to the TextGame and QuizGame

package helpClasses.managerClasses;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import helpClasses.syllableClasses.HiraganaSyllable;
import helpClasses.syllableClasses.KatakanaSyllable;
import helpClasses.wordClasses.HiraganaWord;
import helpClasses.wordClasses.KatakanaWord;
import helpClasses.wordClasses.Word;

public class WordsManager {

    // For initilialization of GlobalVariables
    Activity activity;
    GlobalVariables global;
    ChoiceManager choiceManager;

    public WordsManager(Activity activity){
        this.activity = activity;
        global = (GlobalVariables) activity.getApplication();
        choiceManager = new ChoiceManager(activity);
    }

    // Get IDs of all the words in the DB
    // Used in FontChoice
    public void setID(){

        DatabaseManager db = new DatabaseManager(activity);

        // If we have syllables
        if (choiceManager.isSyllables()){
            if (choiceManager.isKatakana()){
                global.id_permanent = db.getAllIdSyllable(choiceManager.katakana);

                for (Integer tmp : global.id_permanent)
                {
                    global.id_tmp.add(tmp);
                }
            }
            else {
                global.id_permanent = db.getAllIdSyllable(choiceManager.hiragana);

                for (Integer tmp : global.id_permanent)
                {
                    global.id_tmp.add(tmp);
                }
            }
        }
        // If we have words
        else {

            if (choiceManager.isKatakana()) {
                global.id_permanent = db.getAllId(choiceManager.katakana);

                for (Integer tmp : global.id_permanent) {
                    global.id_tmp.add(tmp);
                }
            } else {
                global.id_permanent = db.getAllId(choiceManager.hiragana);

                for (Integer tmp : global.id_permanent) {
                    global.id_tmp.add(tmp);
                }
            }
        }
    }

    // Gives the right answer
    // Used in TextGame, QuizGame
    public String[] getRightAnswer(){

        // if game mode sillables
        if (choiceManager.isSyllables()){
            return getSyllable();
        }
        else {
            return getWord();
        }
    }

    private String[] getSyllable(){

        DatabaseManager db = new DatabaseManager(activity);
        // Strings for question and answer
        String[] syllable = new String[2];

        // if user wants to train with Katakana font
        if (choiceManager.isKatakana()) {

            KatakanaSyllable katakanaSyllable;
            global.current_id = getID();
            katakanaSyllable = (KatakanaSyllable) db.getSyllable(global.current_id, choiceManager.katakana);

            // Romaji Training
            if (choiceManager.isRomaji()) {
                syllable[0] = katakanaSyllable.getRomaji();
                syllable[1] = katakanaSyllable.getKatakana();
            }
            // Katakana Training
            else {
                syllable[0] = katakanaSyllable.getKatakana();
                syllable[1] = katakanaSyllable.getRomaji();
            }
        }

        // if user wants to train with Hiragana font
        else {
            HiraganaSyllable hiraganaSyllable;
            global.current_id = getID();
            hiraganaSyllable = (HiraganaSyllable) db.getSyllable(global.current_id, choiceManager.hiragana);

            // Hiragana
            if (choiceManager.isHiragana()) {
                // Romaji Training
                if (choiceManager.isRomaji()) {
                    syllable[0] = hiraganaSyllable.getRomaji();
                    syllable[1] = hiraganaSyllable.getHiragana();
                }
                // Hiragana Training
                else {
                    syllable[0] = hiraganaSyllable.getHiragana();
                    syllable[1] = hiraganaSyllable.getRomaji();
                }
            }
        }

        return syllable;
    }
    private String[] getWord(){

        DatabaseManager db = new DatabaseManager(activity);
        // Strings for question and answer
        String[] word = new String[2];

        // if user wants to train with Katakana font
        if (choiceManager.isKatakana()) {
            KatakanaWord katakanaWord;
            global.current_id = getID();
            katakanaWord = (KatakanaWord) db.getWord(global.current_id, choiceManager.katakana);

            // Romaji Training
            if (choiceManager.isRomaji()) {
                word[0] = katakanaWord.getRomaji();
                word[1] = katakanaWord.getKatakana();
            }
            // Katakana Training
            else {
                word[0] = katakanaWord.getKatakana();
                word[1] = katakanaWord.getRomaji();
            }
        }

        // if user wants to train with Kanji or Hiragana fonts
        else {
            HiraganaWord hiraganaWord;
            global.current_id = getID();
            hiraganaWord = (HiraganaWord) db.getWord(global.current_id, choiceManager.hiragana);

            // Hiragana
            if (choiceManager.isHiragana()) {
                // Romaji Training
                if (choiceManager.isRomaji()) {
                    word[0] = hiraganaWord.getRomaji();
                    word[1] = hiraganaWord.getHiragana();
                }
                // Hiragana Training
                else {
                    word[0] = hiraganaWord.getHiragana();
                    word[1] = hiraganaWord.getRomaji();
                }
            }

            // Kanji
            else {
                // Romaji Training
                if (choiceManager.isRomaji()) {
                    word[0] = hiraganaWord.getRomaji();
                    word[1] = hiraganaWord.getKanji();
                }
                // Kanji Training
                else {
                    word[0] = hiraganaWord.getKanji();
                    word[1] = hiraganaWord.getRomaji();
                }
            }
        }

        return word;
    }
    public String getWordAnswerImage(){

        DatabaseManager db = new DatabaseManager(activity);
        // Strings for question and answer
        String answer;

        // if user wants to train with Katakana font
        if (choiceManager.isKatakana()) {
            KatakanaWord katakanaWord;
            global.current_id = getID();
            katakanaWord = (KatakanaWord) db.getWord(global.current_id, choiceManager.katakana);

            // Romaji Training
            if (choiceManager.isRomaji()) {
                answer = katakanaWord.getRomaji();
            }
            // Katakana Training
            else {
                answer = katakanaWord.getKatakana();
            }
        }

        // if user wants to train with Kanji or Hiragana fonts
        else {
            HiraganaWord hiraganaWord;
            global.current_id = getID();
            hiraganaWord = (HiraganaWord) db.getWord(global.current_id, choiceManager.hiragana);

            // Hiragana
            if (choiceManager.isHiragana()) {
                // Romaji Training
                if (choiceManager.isRomaji()) {
                    answer = hiraganaWord.getRomaji();
                }
                // Hiragana Training
                else {
                    answer = hiraganaWord.getHiragana();
                }
            }

            // Kanji
            else {
                // Romaji Training
                if (choiceManager.isRomaji()) {
                    answer = hiraganaWord.getRomaji();
                }
                // Kanji Training
                else {
                    answer = hiraganaWord.getKanji();
                }
            }
        }

        return answer;

    };
    public byte[] getImage(){

        DatabaseManager db = new DatabaseManager(activity);
        // Strings for question and answer
        byte[] image;

        // if user wants to train with Katakana font
        if (choiceManager.isKatakana()) {
            KatakanaWord katakanaWord;
            katakanaWord = (KatakanaWord) db.getWord(global.current_id, choiceManager.katakana);

            image = katakanaWord.getImageInfo();

        }

        // if user wants to train with Kanji or Hiragana fonts
        else {
            HiraganaWord hiraganaWord;
            hiraganaWord = (HiraganaWord) db.getWord(global.current_id, choiceManager.hiragana);

            image = hiraganaWord.getImageInfo();
        }

        return image;

    };
    // Gives randomID of the right answer
    private int getID(){
        int size = global.id_tmp.size();

        // When we used all the words, we take all IDs from id_permanent
        if (size == 0) {
            for (Integer tmp : global.id_permanent) {
                global.id_tmp.add(tmp);
            }

            size = global.id_tmp.size();

        }

        // Taking random id from id_tmp
        Random rand = new Random();
        int tmp = rand.nextInt(size);
        int tmp2 = global.id_tmp.get(tmp);

        // Delete used ID in id_tmp
        global.id_tmp.remove(tmp);

        return tmp2;
    }



    // Gives 3 random words
    // Used in QuizGame
    public String[] getWrongAnswers(){

        if (choiceManager.isSyllables()) {
            return getThreeSyllables();
        }
        else {
            return getThreeWords();
        }
    }

    private String[] getThreeSyllables(){
        DatabaseManager db = new DatabaseManager(activity);
        // 3 random words
        String[] syllables = new String[3];
        // IDs of 3 random words
        int[] randomSyllables = getThreeRandomID(global.current_id);


        // if user wants to train with Katakana font
        if (choiceManager.isKatakana()){
            // For getting strings
            KatakanaSyllable tmp;

            // Romaji Training
            if (choiceManager.isRomaji()){

                tmp = (KatakanaSyllable) db.getSyllable(randomSyllables[0], choiceManager.katakana);
                syllables[0] = tmp.getKatakana();
                tmp = (KatakanaSyllable) db.getSyllable(randomSyllables[1], choiceManager.katakana);
                syllables[1] = tmp.getKatakana();
                tmp = (KatakanaSyllable) db.getSyllable(randomSyllables[2], choiceManager.katakana);
                syllables[2] = tmp.getKatakana();
            }
                // Katakana Training
            else {
                tmp = (KatakanaSyllable) db.getSyllable(randomSyllables[0], choiceManager.katakana);
                syllables[0] = tmp.getRomaji();
                tmp = (KatakanaSyllable) db.getSyllable(randomSyllables[1], choiceManager.katakana);
                syllables[1] = tmp.getRomaji();
                tmp = (KatakanaSyllable) db.getSyllable(randomSyllables[2], choiceManager.katakana);
                syllables[2] = tmp.getRomaji();
                }
            }

        // if user wants to train with Hiragana fonts
        else {

            // For getting strings
            HiraganaSyllable tmp;

            // Romaji Training
            if (choiceManager.isRomaji()){
                tmp = (HiraganaSyllable) db.getSyllable(randomSyllables[0], choiceManager.hiragana);
                syllables[0] = tmp.getHiragana();
                tmp = (HiraganaSyllable) db.getSyllable(randomSyllables[1], choiceManager.hiragana);
                syllables[1] = tmp.getHiragana();
                tmp = (HiraganaSyllable) db.getSyllable(randomSyllables[2], choiceManager.hiragana);
                syllables[2] = tmp.getHiragana();
            }
            // Japanese Training
            else {
                tmp = (HiraganaSyllable) db.getSyllable(randomSyllables[0], choiceManager.hiragana);
                syllables[0] = tmp.getRomaji();
                tmp = (HiraganaSyllable) db.getSyllable(randomSyllables[1], choiceManager.hiragana);
                syllables[1] = tmp.getRomaji();
                tmp = (HiraganaSyllable) db.getSyllable(randomSyllables[2], choiceManager.hiragana);
                syllables[2] = tmp.getRomaji();
            }
            }

        return syllables;

    }
    private String[] getThreeWords(){
        DatabaseManager db = new DatabaseManager(activity);
        // 3 random words
        String[] words = new String[3];
        // IDs of 3 random words
        int[] randomWords = getThreeRandomID(global.current_id);

        // if user wants to train with Katakana font
        if (choiceManager.isKatakana()) {
            // For getting strings
            KatakanaWord tmp;

            // Romaji Training
            if (choiceManager.isRomaji()) {

                tmp = (KatakanaWord) db.getWord(randomWords[0], choiceManager.katakana);
                words[0] = tmp.getKatakana();
                tmp = (KatakanaWord) db.getWord(randomWords[1], choiceManager.katakana);
                words[1] = tmp.getKatakana();
                tmp = (KatakanaWord) db.getWord(randomWords[2], choiceManager.katakana);
                words[2] = tmp.getKatakana();
            }
            // Katakana Training
            else {
                tmp = (KatakanaWord) db.getWord(randomWords[0], choiceManager.katakana);
                words[0] = tmp.getRomaji();
                tmp = (KatakanaWord) db.getWord(randomWords[1], choiceManager.katakana);
                words[1] = tmp.getRomaji();
                tmp = (KatakanaWord) db.getWord(randomWords[2], choiceManager.katakana);
                words[2] = tmp.getRomaji();
            }
        }

        // if user wants to train with Kanji or Hiragana fonts
        else {

            // For getting strings
            HiraganaWord tmp;

            // Hiragana
            if (choiceManager.isHiragana()) {
                // Romaji Training
                if (choiceManager.isJapanese()) {
                    tmp = (HiraganaWord) db.getWord(randomWords[0], choiceManager.hiragana);
                    words[0] = tmp.getHiragana();
                    tmp = (HiraganaWord) db.getWord(randomWords[1], choiceManager.hiragana);
                    words[1] = tmp.getHiragana();
                    tmp = (HiraganaWord) db.getWord(randomWords[2], choiceManager.hiragana);
                    words[2] = tmp.getHiragana();
                }
                // Hiragana Training
                else {
                    tmp = (HiraganaWord) db.getWord(randomWords[0], choiceManager.hiragana);
                    words[0] = tmp.getRomaji();
                    tmp = (HiraganaWord) db.getWord(randomWords[1], choiceManager.hiragana);
                    words[1] = tmp.getRomaji();
                    tmp = (HiraganaWord) db.getWord(randomWords[2], choiceManager.hiragana);
                    words[2] = tmp.getRomaji();
                }
            }

            // Kanji
            else {
                // Romaji Training
                if (choiceManager.isRomaji()) {
                    tmp = (HiraganaWord) db.getWord(randomWords[0], choiceManager.hiragana);
                    words[0] = tmp.getKanji();
                    tmp = (HiraganaWord) db.getWord(randomWords[1], choiceManager.hiragana);
                    words[1] = tmp.getKanji();
                    tmp = (HiraganaWord) db.getWord(randomWords[2], choiceManager.hiragana);
                    words[2] = tmp.getKanji();
                }
                // Kanji Training
                else {
                    tmp = (HiraganaWord) db.getWord(randomWords[0], choiceManager.hiragana);
                    words[0] = tmp.getRomaji();
                    tmp = (HiraganaWord) db.getWord(randomWords[1], choiceManager.hiragana);
                    words[1] = tmp.getRomaji();
                    tmp = (HiraganaWord) db.getWord(randomWords[2], choiceManager.hiragana);
                    words[2] = tmp.getRomaji();
                }
            }
        } // end of the id/else

        return words;
    }
    // Gives 3 random IDs of the wrong answers
    private int[] getThreeRandomID(int id){
        int[] ids = new int[3];
        ArrayList<Integer> tmpList = new ArrayList<>();

        for (Integer tmp : global.id_permanent) {
            // Check
            if (tmp != id)
                tmpList.add(tmp);
        }

        Collections.shuffle(tmpList);

        ids[0] = tmpList.get(0);
        ids[1] = tmpList.get(1);
        ids[2] = tmpList.get(2);

        return ids;
    }
}
