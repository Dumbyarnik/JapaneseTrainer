
// Original class for KatakanaWord and HiraganaWord

package helpClasses.wordClasses;

public class Word {

    private String romaji;
    private String translation;
    private byte[] imageInfo;

    public Word(){}

    public Word(String romaji, String translation, byte[] imageInfo){
        this.romaji = romaji;
        this.translation = translation;
        this.imageInfo = imageInfo;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }
    public String getRomaji() {
        return romaji;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
    public String getTranslation() {
        return translation;
    }


    public void setImageInfo(byte[] imageInfo) {
        this.imageInfo = imageInfo;
    }
    public byte[] getImageInfo() {
        return imageInfo;
    }

    public String toString(){
        return "romaji: " + romaji;
    }

}
