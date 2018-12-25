package com.material.components.activity.login;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.material.components.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ActivityCars extends AppCompatActivity {
    ArrayList<String> arrname = new ArrayList<>();
    ArrayList<String> arrimage = new ArrayList<>();
    ArrayList<String> arrworth = new ArrayList<>();
    ArrayList<String> arrinyear = new ArrayList<>();
    ArrayList<String> arrsource = new ArrayList<>();
    //  ListView listview;
    private Context context;
    private static final String MEMBER_URL ="http://192.168.43.73/naijacars/Api/adslist.php";
    ArrayList<MemberList> listItems = new ArrayList<MemberList>();
    MemberAdapter memberAdapter;
    private SwipeMenuListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cars_list);
        context = ActivityCars.this;

        listView=(SwipeMenuListView)findViewById(R.id.listviewcars);

        try {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, MEMBER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            getJsonResponse(response);
                            System.out.println("RESPONSE");
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ActivityCars.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    });

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
        catch (Exception e)
        {

        }
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                /*SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);*/

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(125));
                // set a icon
                //   deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };


// set creator
        listView.setMenuCreator(creator);
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Toast.makeText(context, "YES", Toast.LENGTH_SHORT).show();
                listItems.remove(position);
                memberAdapter.notifyDataSetChanged();
                return false;
            }

        });

        memberAdapter = new MemberAdapter(context,listItems);
        listView.setAdapter(memberAdapter);
        //memberAdapter.notifyDataSetChanged();
    }

    private void getJsonResponse(String response)
    {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i =0;i<jsonArray.length();i++)
            {
                String name = jsonArray.getJSONObject(i).getString("title").toString();
                String image =jsonArray.getJSONObject(i).getString("pic").toString();
                String worth=jsonArray.getJSONObject(i).getString("price").toString();
                String InYear = jsonArray.getJSONObject(i).getString("category").toString();
                String source = jsonArray.getJSONObject(i).getString("location").toString();
                arrname.add(name);
                arrimage.add(image);
                arrinyear.add(InYear);
                arrworth.add(worth);
                arrsource.add(source);
                MemberList memberList = new MemberList();
                memberList.setName(name);
                memberList.setImage(image);
                memberList.setInyear(InYear);
                memberList.setSource(source);
                memberList.setWorth(worth);
                listItems.add(memberList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}