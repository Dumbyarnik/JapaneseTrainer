package helpClasses.managerClasses;

import android.app.Activity;
import android.content.SharedPreferences;

public class PointsManager {


    // For initilialization of GlobalVariables
    Activity activity;
    // Name of the variable in the SharedPreferences
    public final static String preferencesName = "score";

    public PointsManager(Activity activity){
        this.activity = activity;
    }

    public int getPoints() {
        SharedPreferences score = activity.getSharedPreferences(preferencesName, activity.MODE_PRIVATE);
        return score.getInt(preferencesName, 0);
    }

    public void incrementPoints() {
        SharedPreferences score = activity.getSharedPreferences(preferencesName, activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = score.edit();

        int tmp = score.getInt(preferencesName, 0) + 1;

        // Writing points into SharedPreferences
        editor.putInt(preferencesName, tmp);
        editor.commit();
    }

    // Set points to 0 before game starts
    // Used in GameChoice
    public void setPointsNull(){
        SharedPreferences score = activity.getSharedPreferences(preferencesName, activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = score.edit();
        // Writing points into SharedPreferences
        editor.putInt(preferencesName, 0);
        editor.commit();
    }

    public void setTriesNull(){
        GlobalVariables global = (GlobalVariables) activity.getApplication();
        global.tries = 0;
    }

    public void incrementTries(){
        GlobalVariables global = (GlobalVariables) activity.getApplication();
        global.tries += 1;
    }

    public int getTries(){
        GlobalVariables global = (GlobalVariables) activity.getApplication();
        return global.tries;
    }


}
