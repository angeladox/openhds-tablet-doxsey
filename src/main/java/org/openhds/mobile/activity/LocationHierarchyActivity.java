package org.openhds.mobile.activity;

import org.openhds.mobile.R;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

public class LocationHierarchyActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {

	public static String NUM_HIERARCHIES = "numHierarchies";
	public static String HIERARCHY_1, HIERARCHY_2, HIERARCHY_3, HIERARCHY_4, 
	HIERARCHY_5, HIERARCHY_6, HIERARCHY_7, HIERARCHY_8, HIERARCHY_9, HIERARCHY_10;
	/*
	 * Upon rendering of the Location Hierarchy Activity, the user should be
	 * prompted to enter the number of location levels they would like for the
	 * hierarchy scheme. When a number is chosen, that number of text field
	 * input boxes should be supplied to receive input.
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.location_hierarchies);
		setTitle(getString(R.string.app_name) + " > " + getString(R.string.configureHierarchies));
		Integer num = updateNumHierarchies(NUM_HIERARCHIES);
		for (int i=1;i<num;i++){
			String hierarchy = "hierarchy" + i;
			updateHierarchy(hierarchy);
		}
	}

	private Integer updateNumHierarchies(String numHierarchies) {
		EditTextPreference etp = (EditTextPreference) this.getPreferenceScreen().findPreference(numHierarchies);

		String s = etp.getText().trim();
		Integer num = 0;
		boolean parsed = false;
		try {
			num = Integer.parseInt(s);
			parsed = true;
		} catch (Exception e) {
		}
		if (parsed) {
			etp.setText(s);
			etp.setSummary(s);
		} else {
			etp.setText((String) etp.getSummary());
			Toast.makeText(getApplicationContext(), getString(R.string.num_hierarchy_error), Toast.LENGTH_SHORT).show();
		}
		return num;
	}

	private void updateHierarchy(String hierarchy) {
		EditTextPreference etp = (EditTextPreference) this.getPreferenceScreen().findPreference(hierarchy);

		String s = etp.getText().trim();

		if (!s.isEmpty()) {
			etp.setText(s);
			etp.setSummary(s);
		} else {
			etp.setText((String) etp.getSummary());
			Toast.makeText(getApplicationContext(), getString(R.string.hierarchy_error), Toast.LENGTH_SHORT).show();
		}
	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
			updateHierarchy(key);
	}
}
