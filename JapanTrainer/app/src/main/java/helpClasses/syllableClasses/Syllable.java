
// Original class for KatakanaSyylable and HiraganaSyllable

package helpClasses.syllableClasses;

public class Syllable {

    private String romaji;

    public Syllable(){}

    public Syllable(String romaji){
        this.romaji = romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }
    public String getRomaji() {
        return romaji;
    }

    public String toString(){
        return "romaji: " + romaji;
    }
}
