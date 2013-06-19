package org.openhds.mobile.activity;

import org.openhds.mobile.R;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.widget.Toast;

public class LocationHierarchyActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {
	
    public static String NUM_HIERARCHIES = "numHierarchies";
	public static String HIERARCHY_1 = "hierarchy1";
    public static String HIERARCHY_2 = "hierarchy2";
    public static String HIERARCHY_3 = "hierarchy3";
    public static String HIERARCHY_4 = "hierarchy4";
    /*Upon rendering of the Location Hierarchy Activity, the user should be prompted to 
     * enter the number of location levels they would like for the hierarchy scheme. When
     * a number is chosen, that number of text field input boxes should be supplied to
     * receive input.
     */
    
    /*  <EditTextPreference
    		android:id="@+id/numHierarchies"
            android:key="numHierarchies"
            android:defaultValue="@string/default_numHierarchies"
            android:title="@string/numHierarchiesTitle"
            android:dialogTitle="@string/changeNumHierarchy" />
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.layout.location_hierarchies);
        setTitle(getString(R.string.app_name) + " > " + getString(R.string.configureHierarchies));
        updateNumHierarchies(NUM_HIERARCHIES);
        updateHierarchy(HIERARCHY_1);
        updateHierarchy(HIERARCHY_2);
        updateHierarchy(HIERARCHY_3);
        updateHierarchy(HIERARCHY_4);
    }
    
    private void updateNumHierarchies(String numHierarchies){
    	EditTextPreference etp = (EditTextPreference) this.getPreferenceScreen().findPreference(numHierarchies);
        
        String s = etp.getText().trim();

        if (!s.isEmpty()) {
            etp.setText(s);
            etp.setSummary(s);
        } 
        else {
            etp.setText((String) etp.getSummary());
            Toast.makeText(getApplicationContext(), getString(R.string.num_hierarchy_error), Toast.LENGTH_SHORT).show();
        }
    }
    
    private void updateHierarchy(String hierarchy) {
        EditTextPreference etp = (EditTextPreference) this.getPreferenceScreen().findPreference(hierarchy);
        
        String s = etp.getText().trim();

        if (!s.isEmpty()) {
            etp.setText(s);
            etp.setSummary(s);
        } 
        else {
            etp.setText((String) etp.getSummary());
            Toast.makeText(getApplicationContext(), getString(R.string.hierarchy_error), Toast.LENGTH_SHORT).show();
        }
    }
    
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key.equals(HIERARCHY_1)) 
			updateHierarchy(HIERARCHY_1);
		else if (key.equals(HIERARCHY_2)) 
			updateHierarchy(HIERARCHY_2);
        else if (key.equals(HIERARCHY_3)) 
        	updateHierarchy(HIERARCHY_3);
        else if (key.equals(HIERARCHY_4)) 
        	updateHierarchy(HIERARCHY_4);
	}
}
