package com.example.tkixi.googlemaps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tkixi on 11/7/17.
 */

public class Geocode extends AppCompatActivity {
    Button back, convert;
    TextView coords;
    EditText address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geocoding);

        back = (Button)findViewById(R.id.backButton);
        convert = (Button)findViewById(R.id.convertButton);
        coords = (TextView)findViewById(R.id.coordsText);
        address = (EditText)findViewById(R.id.addressText);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // convert address to coords
                String key = "https://maps.googleapis.com/maps/api/geocode/json?address=";
                String add = address.getText().toString();
                String api = "&key=AIzaSyCdD6V_pMev1dl8LAsoJ6PLG5JLnR-OiUc";
                String stringUrl = key+add+api;



                coords.setText(stringUrl);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Geocode.this, MapsActivity.class));
            }
        });

    }


}

class GeocodeResponse {
    private String status;
    private List<Geocodes> results = new ArrayList<Geocodes>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResults(List<Geocodes> results) {
        this.results = results;
    }

    public List<Geocodes> getResults() {
        return results;
    }
}


class Geocodes {
    private Collection<String> types = new ArrayList<String>();
    private String formatted_address;
    private Collection<AddressComponent> address_components = new ArrayList<AddressComponent>();
    private Geometry geometry;
    private boolean partialMatch;

    public Collection<String> getTypes() {
        return types;
    }

    public void setTypes(Collection<String> types) {
        this.types = types;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setAddress_components(Collection<AddressComponent> address_components) {
        this.address_components = address_components;
    }

    public Collection<AddressComponent> getAddress_components() {
        return address_components;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public boolean isPartialMatch() {
        return partialMatch;
    }

    public void setPartialMatch(boolean partialMatch) {
        this.partialMatch = partialMatch;
    }
}

class AddressComponent {
    private String longName;
    private String shortName;
    private Collection<String> types = new ArrayList<String>();

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Collection<String> getTypes() {
        return types;
    }

    public void setTypes(Collection<String> types) {
        this.types = types;
    }
}

class Geometry {
    private Location location;
    private String locationType;
    private Area viewport;
    private Area bounds;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public Area getViewport() {
        return viewport;
    }

    public void setViewport(Area viewport) {
        this.viewport = viewport;
    }

    public Area getBounds() {
        return bounds;
    }

    public void setBounds(Area bounds) {
        this.bounds = bounds;
    }
}


class Location {
    private double lat;
    private double lng;

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLng() {
        return lng;
    }
}


class Area {
    private Location southWest;
    private Location northEast;

    public Location getSouthWest() {
        return southWest;
    }

    public void setSouthWest(Location southWest) {
        this.southWest = southWest;
    }

    public Location getNorthEast() {
        return northEast;
    }

    public void setNorthEast(Location northEast) {
        this.northEast = northEast;
    }
}
