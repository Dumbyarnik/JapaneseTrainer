
// Class for inserting words and syllables

package helpClasses.managerClasses;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.japantrainer.R;

import java.io.ByteArrayOutputStream;

import helpClasses.syllableClasses.HiraganaSyllable;
import helpClasses.syllableClasses.KatakanaSyllable;
import helpClasses.wordClasses.HiraganaWord;
import helpClasses.wordClasses.KatakanaWord;

public class InsertManager {

    // context for converting images into bitmap
    private Context context;


    public InsertManager(Context current){
        this.context = current;
    }

    // Insert the words
    public void insertWords(){

        DatabaseManager db = new DatabaseManager(context);

        if (db.isEmpty()) {
            words(db);
            syllables(db);
        }
    }

    // Here we have all the syllables
    public void syllables (DatabaseManager db){

        // Katakana Section
        db.insertSyllable(new KatakanaSyllable("ア","a"));
        db.insertSyllable(new KatakanaSyllable("カ","ka"));
        db.insertSyllable(new KatakanaSyllable("ク","ku"));
        db.insertSyllable(new KatakanaSyllable("セ","se"));
        db.insertSyllable(new KatakanaSyllable("ネ","ne"));
        db.insertSyllable(new KatakanaSyllable("イ","i"));
        db.insertSyllable(new KatakanaSyllable("ウ","u"));
        db.insertSyllable(new KatakanaSyllable("エ","e"));
        db.insertSyllable(new KatakanaSyllable("オ","o"));
        db.insertSyllable(new KatakanaSyllable("ヘ","he"));
        db.insertSyllable(new KatakanaSyllable("ホ","ho"));
        db.insertSyllable(new KatakanaSyllable("モ","mo"));
        db.insertSyllable(new KatakanaSyllable("ミャ","mya"));
        db.insertSyllable(new KatakanaSyllable("フ","fu"));
        db.insertSyllable(new KatakanaSyllable("ニ","ni"));


        // Hiragana Section
        db.insertSyllable(new HiraganaSyllable("あ","a"));
        db.insertSyllable(new HiraganaSyllable("す","su"));
        db.insertSyllable(new HiraganaSyllable("て","te"));
        db.insertSyllable(new HiraganaSyllable("た","ta"));
        db.insertSyllable(new HiraganaSyllable("こ","ko"));
        db.insertSyllable(new HiraganaSyllable("え","e"));
        db.insertSyllable(new HiraganaSyllable("ひ","hi"));
        db.insertSyllable(new HiraganaSyllable("む","mu"));
        db.insertSyllable(new HiraganaSyllable("み","mi"));
        db.insertSyllable(new HiraganaSyllable("ゆ","yu"));
        db.insertSyllable(new HiraganaSyllable("た","ta"));
        db.insertSyllable(new HiraganaSyllable("す","su"));
        db.insertSyllable(new HiraganaSyllable("け","ke"));
        db.insertSyllable(new HiraganaSyllable("う","u"));
        db.insertSyllable(new HiraganaSyllable("ほ","ho"));
    }


    // Here we have all the words
    private void words(DatabaseManager db){
        // Variables to store pictures in DB
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap bitmap;

        // Katakana Section

        // this is the code of compressing jpeg to byte[]
        // https://www.macprovideo.com/article/illustrator/freehand-drawing-with-adobe-illustrator-cc
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cake);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new KatakanaWord("ケーキ","kēki", "cake", stream.toByteArray()));
        stream.reset();

        // https://www.cleanpng.com/png-pizza-steve-drawing-tutorial-food-7114992/preview.html
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pizza);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new KatakanaWord("ピザ","piza", "pizza", stream.toByteArray()));
        stream.reset();

        // Taken from https://www.shareicon.net/
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.toast);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new KatakanaWord("トースト","tōsuto", "toast", stream.toByteArray()));
        stream.reset();

        // https://evaly.com.bd/products/chinigura-polaw-rice-premium-bulk-b51fa3971
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.rice);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new KatakanaWord("ライス","raisu", "rice", stream.toByteArray()));
        stream.reset();

        // pascogifts.com
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.lunchset);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new KatakanaWord("ランチ","ranchi", "lunch set", stream.toByteArray()));
        stream.reset();

        // Taken from iEmoji.com
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.chocolate);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new KatakanaWord("チョコレート","chokorēto", "chocolate", stream.toByteArray()));
        stream.reset();

        // Taken from freepik.com
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.friedpotatoes);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new KatakanaWord("ポテト","poteto", "fried potatoes", stream.toByteArray()));
        stream.reset();

        // Taken from iEmoji.com
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.hamburger);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new KatakanaWord("ハンバーガー","hanbāgā", "hamburger", stream.toByteArray()));
        stream.reset();

        // Taken from iEmoji.com
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.icecream);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new KatakanaWord("アイスクリーム","aisu kurīmu", "ice cream", stream.toByteArray()));
        stream.reset();

        // Taken from depositphotos.com
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.salad);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new KatakanaWord("サラダ","sarada", "salad", stream.toByteArray()));
        stream.reset();


        // Hiragana Section

        // Taken from kartable.fr
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sugar);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new HiraganaWord("さとう", "砂糖", "satou", "sugar", stream.toByteArray()));
        stream.reset();

        // Taken from vecteezy.com
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.fish);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new HiraganaWord("魚", "さかな", "sakana", "fish", stream.toByteArray()));
        stream.reset();

        https://www.istockphoto.com/de/foto/sushi-auf-einem-wei%C3%9Fen-hintergrund-isoliert-gm1060164320-283380597
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sushi);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new HiraganaWord("すし", "鮓", "sushi", "sushi", stream.toByteArray()));
        stream.reset();

        // Taken from vecteezy.com
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.crab);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new HiraganaWord("かに", "蟹", "kani", "crab", stream.toByteArray()));
        stream.reset();

        // Taken from iconeasy.com
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pepper);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new HiraganaWord("こしょう", "胡椒", "koshou", "pepper", stream.toByteArray()));
        stream.reset();

        // Taken from uihere.com
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.oil);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new HiraganaWord("あぶら", "油", "abura", "oil", stream.toByteArray()));
        stream.reset();

        // Taken from pinterest.ch
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.onion);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new HiraganaWord("たまねぎ", "玉葱", "tamanegi", "onion", stream.toByteArray()));
        stream.reset();

        // Taken from pngio.com
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.egg);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new HiraganaWord("たまご", "卵", "tamago", "egg", stream.toByteArray()));
        stream.reset();

        // Taken from iemoji.com
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bentobox);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new HiraganaWord("べんとう", "弁当", "bentou", "bento box", stream.toByteArray()));
        stream.reset();

        // Taken from iconspedia.com
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.misosoup);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new HiraganaWord("みそしる", "味噌汁", "miso shiru", "miso soup", stream.toByteArray()));
        stream.reset();
    }


}
