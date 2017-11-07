package com.example.tkixi.googlemaps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tkixi on 11/6/17.
 */

public class MyCustomAdapter extends BaseAdapter {
    private
    String items[];
    String itemDescription[];
    ArrayList<Integer> itemImages;

    // create a variable for the context so we can use it later
    Context context;

    //STEP 2: Override the Constructor, be sure to:
    // grab the context, the callback gets it as a parm.
    // load the strings and images into object references.
    public MyCustomAdapter(Context aContext) {
        //initializing our data in the constructor.
        context = aContext;  //saving the context we'll need it again (for intents)

        //retrieving list of episodes predefined in strings-array "episodes" in strings.xml
        items = aContext.getResources().getStringArray(R.array.items);
        itemDescription = aContext.getResources().getStringArray(R.array.item_descriptions);

        // building ArrayList of item images
//        itemImages = new ArrayList<Integer>();
//        itemImages.add(R.drawable.vacuum_one);
//        itemImages.add(R.drawable.vacuum_two);
    }

    //STEP 3: Override and implement getCount(..), ListView uses this to determine how many rows to render.
    @Override
    public int getCount() {
        return items.length;   //all of the arrays are same length
    }

    //STEP 4: Override getItem/getItemId, we aren't using these, but we must override anyway (base is abstract)
    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //THIS IS WHERE THE ACTION HAPPENS.  getView(..) is how each row gets rendered.
//STEP 5: Easy as A-B-C
    @Override
    //convertView is Row (it may be null), parent is the layout that has the row Views.
    public View getView(final int position, View convertView, ViewGroup parent) {

//STEP 5a: Inflate the listview row based on the xml.
        //this will refer to the row to be inflated or displayed if it's already been displayed. (listview_row.xml)
        View row;

        // Let's optimize a bit by checking to see if we need to inflate, or if it's already been inflated.
        if (convertView == null){  //indicates this is the first time we are creating this row.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.listview_row, parent, false);
        }
        else
        {
            row = convertView;
        }

//STEP 5b: Now that we have a valid row instance, we need to get references to the views

        //we prefixed findViewByID with row, because row is the container.
   //     ImageView imgItem = (ImageView) row.findViewById(R.id.imgItem);
        TextView itemTitle = (TextView) row.findViewById(R.id.itemTitle);
        TextView itemDescriptions = (TextView) row.findViewById(R.id.itemDescription);
        RatingBar ratingBar = (RatingBar) row.findViewById(R.id.rbItem);

        // fill with the appropriate text and images.
        itemTitle.setText(items[position]);
        itemDescriptions.setText(itemDescription[position]);
      //  imgItem.setImageResource(itemImages.get(position).intValue());
        ratingBar.setRating(retrieveSharedPreferenceInfo(position));

        // when image is clicked, open a website (Wikipedia of Start Trek episodes)
     //   imgItem.setOnClickListener(new View.OnClickListener() {
      //      @Override
        //    public void onClick(View v) {
//                String url = "http://en.wikipedia.org/wiki/List_of_Star_Trek:_The_Original_Series_episodes";
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
       //     }
     //   });

        // when episode title is clicked, open a website (Wikipedia of Start Trek episodes)
        itemTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String url = "http://en.wikipedia.org/wiki/List_of_Star_Trek:_The_Original_Series_episodes";
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
            }
        });

        // make sure the ratings for each episode "stick"
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser == true) {
                    saveSharedPreferenceInfo(rating, position);
                }
            }
        });

//STEP 5c: That's it, the row has been inflated and filled with data, return it.
        return row;

    }

    // We want the rating for each episode to "stick," so we create SharedPreferences
    // this means the rating will remain even when we close the app, and they will be restored when we open it again
    void saveSharedPreferenceInfo(float rating, int position){
        //1. Refer to the SharedPreference Object.
        // (Private means no other Apps can access this.)
        SharedPreferences ratingsPreferences = context.getSharedPreferences("RatingsPreferences", Context.MODE_PRIVATE);

        //2. Create an Shared Preferences Editor for Editing Shared Preferences.
        SharedPreferences.Editor editor = ratingsPreferences.edit();

        // create variably to hold the string for the key for the key, value pair
        String key = "";

        // depending on which rating (value) was changed, choose the appropriate key
        switch (position) {
            case 0:
                key = "rbRatings0";
                break;
            case 1:
                key = "rbRatings1";
                break;
            case 2:
                key = "rbRatings2";
                break;
            case 3:
                key = "rbRatings3";
                break;
            case 4:
                key = "rbRatings4";
                break;
            case 5:
                key = "rbRatings5";
                break;
            case 6:
                key = "rbRatings6";
                break;
        }

        //3. Store what's important!  (Key, Value Pair)
        editor.putFloat(key, rating);

        //4. Save your information.
        editor.apply();
    }

    // when we open the app, we want the ratings that we saved to be restored
    float retrieveSharedPreferenceInfo(int position){
        SharedPreferences ratingsPreferences = context.getSharedPreferences("RatingsPreferences", Context.MODE_PRIVATE);

        // create variable to hold key for key, value pair
        String key = "";

        // depending on which rating we are restoring, select that key
        switch (position) {
            case 0:
                key = "rbRatings0";
                break;
            case 1:
                key = "rbRatings1";
                break;
            case 2:
                key = "rbRatings2";
                break;
            case 3:
                key = "rbRatings3";
                break;
            case 4:
                key = "rbRatings4";
                break;
            case 5:
                key = "rbRatings5";
                break;
            case 6:
                key = "rbRatings6";
                break;
        }

        // return the rating
        return ratingsPreferences.getFloat(key, 0);
    }
}
