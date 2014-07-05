package com.example.friendschatapp;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	private String[] navOptions = {"Family", "Friends", "Others", "Manage Groups", "Car Pooling"};
	private DrawerLayout drawerLayout;
	private ListView navList;
	
	GoogleMap googleMap;
	
	double latitude = 44.867031;
	double longitude = -93.334518;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Initializing home screen variables
		drawerLayout = (DrawerLayout) findViewById(R.id.layout_drawer);
		navList = (ListView) findViewById(R.id.lvDrawer);
		
		// Creating the navigation drawer
		navList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, navOptions ));
		
		// Creating marker on the map
		googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		LatLng location = new LatLng(latitude, longitude);
		MarkerOptions marker = new MarkerOptions().position(location);
		marker.title("Hello Maps");
		
		View markerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.marker_view, null);
		marker.icon(BitmapDescriptorFactory.fromBitmap(createBitmapFromView(markerView)));
		
		googleMap.addMarker(marker);

		/* if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/
	}
	
	private Bitmap createBitmapFromView(View view){
		
		Bitmap markerImage = Bitmap.createBitmap(view.getHeight(), view.getWidth(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(markerImage);
		
		view.draw(canvas);
		return markerImage;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			return rootView;
		}
	}

}
