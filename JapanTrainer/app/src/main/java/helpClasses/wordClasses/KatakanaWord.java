
// Class for Katakana Words

package helpClasses.wordClasses;

public class KatakanaWord extends Word {

    private String katakana;

    public KatakanaWord(){}

    public KatakanaWord(String katakana, String romaji, String translation, byte[] imageInfo){
        super(romaji, translation, imageInfo);
        this.katakana = katakana;
    }


    public void setKatakana(String katakana) {
        this.katakana = katakana;
    }
    public String getKatakana() {
        return katakana;
    }

    public String toString(){
        return "Katakana: " + katakana + ", Romaji: " + this.getRomaji() +
                ", Translation" + this.getTranslation();
    }


}
