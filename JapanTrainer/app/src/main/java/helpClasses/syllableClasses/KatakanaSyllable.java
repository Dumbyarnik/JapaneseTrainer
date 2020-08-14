
// Class for Katakana Syllables

package helpClasses.syllableClasses;

public class KatakanaSyllable extends Syllable {

    private String katakana;

    public KatakanaSyllable(){}

    public KatakanaSyllable(String katakana, String romaji){
        super(romaji);
        this.katakana = katakana;
    }

    public void setKatakana(String katakana) {
        this.katakana = katakana;
    }
    public String getKatakana() {
        return katakana;
    }

    public String toString(){
        return "Katakana: " + katakana + ", Romaji: " + this.getRomaji();
    }

}
