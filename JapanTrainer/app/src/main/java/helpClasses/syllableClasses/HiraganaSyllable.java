
// Class for Hiragana Syllables

package helpClasses.syllableClasses;

public class HiraganaSyllable extends Syllable {

    private String hiragana;

    public HiraganaSyllable(){}

    public HiraganaSyllable(String hiragana, String romaji){
        super(romaji);
        this.hiragana = hiragana;
    }

    public void setHiragana(String katakana) {
        this.hiragana = katakana;
    }
    public String getHiragana() {
        return hiragana;
    }

    public String toString(){
        return "hiragana: " + hiragana + ", Romaji: " + this.getRomaji();
    }

}
