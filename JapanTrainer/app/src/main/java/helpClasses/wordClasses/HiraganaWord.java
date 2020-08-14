
// Class for Hiragana and Kanji Words

package helpClasses.wordClasses;

public class HiraganaWord extends Word {

    private String hiragana;
    private String kanji;

    public HiraganaWord(){}

    public HiraganaWord(String hiragana, String kanji, String romaji, String translation, byte[] imageInfo){
        super(romaji, translation, imageInfo);
        this.hiragana = hiragana;
        this.kanji = kanji;
    }

    public void setHiragana(String hiragana) {
        this.hiragana = hiragana;
    }
    public String getHiragana() {
        return hiragana;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }
    public String getKanji() {
        return kanji;
    }

    public String toString(){
        return "Hiragana: " + hiragana + ", Kanji: " + kanji + ", Romaji: " + this.getRomaji() +
                ", Translation" + this.getTranslation();
    }


}
