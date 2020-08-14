
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

        // Hiragana Section
        db.insertSyllable(new HiraganaSyllable("あ","a"));
        db.insertSyllable(new HiraganaSyllable("す","su"));
        db.insertSyllable(new HiraganaSyllable("て","te"));
        db.insertSyllable(new HiraganaSyllable("た","ta"));
        db.insertSyllable(new HiraganaSyllable("こ","ko"));
    }


    // Here we have all the words
    private void words(DatabaseManager db){
        // Variables to store pictures in DB
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap bitmap;

        // Katakana Section

        // this is the code of compressing jpeg to byte[]
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cake);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new KatakanaWord("ケーキ","kēki", "cake", stream.toByteArray()));
        stream.reset();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pizza);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new KatakanaWord("ピザ","piza", "pizza", stream.toByteArray()));
        stream.reset();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.toast);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new KatakanaWord("トースト","tōsuto", "toast", stream.toByteArray()));
        stream.reset();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.rice);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new KatakanaWord("ライス","raisu", "rice", stream.toByteArray()));
        stream.reset();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.lunchset);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new KatakanaWord("ランチ","ranchi", "lunch set", stream.toByteArray()));
        stream.reset();


        // Hiragana Section
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sugar);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new HiraganaWord("さとう", "砂糖", "satou", "sugar", stream.toByteArray()));
        stream.reset();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.fish);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new HiraganaWord("魚", "さかな", "sakana", "fish", stream.toByteArray()));
        stream.reset();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sushi);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new HiraganaWord("すし", "鮓", "sushi", "sushi", stream.toByteArray()));
        stream.reset();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.crab);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new HiraganaWord("かに", "蟹", "kani", "crab", stream.toByteArray()));
        stream.reset();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pepper);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new HiraganaWord(" こしょう", "胡椒", "koshou", "pepper", stream.toByteArray()));
        stream.reset();
    }


}
