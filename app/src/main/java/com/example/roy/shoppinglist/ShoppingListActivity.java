package com.example.roy.shoppinglist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListActivity extends AppCompatActivity {

    private ListView shoppingList;
    private TextView shoppingListInput;
    private ArrayAdapter<String> mAdapter;
    private List<String> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //find the UI items
        shoppingList = findViewById(R.id.listView);
        shoppingListInput = findViewById(R.id.newItem);
        Button addBtn = findViewById(R.id.addBtn);

        mItems = new ArrayList<>();

        //final ArrayAdapter<String> shoppingListAdapter;

        //shoppingListAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        //shoppingList.setAdapter(shoppingListAdapter);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newItem = (String) shoppingListInput.getText().toString();
                String addMsg = newItem + " Added to the List!";
                mItems.add(newItem);
                updateUI();

                Toast.makeText(getApplicationContext(), addMsg, Toast.LENGTH_SHORT).show();
            }
        });

        shoppingList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                String delItem = (String) parent.getItemAtPosition(position);
                String delMes = delItem + " Deleted!";

                mItems.remove(delItem);
                updateUI();

                Toast.makeText(getApplicationContext(), delMes, Toast.LENGTH_SHORT).show();


                return true;
            }
        });


        }
        private void updateUI() {
            if (mAdapter == null) {
                mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mItems);
                shoppingList.setAdapter(mAdapter);

            } else {
                mAdapter.notifyDataSetChanged();
            }
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shopping_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_delete_item) {
            mItems.clear();
            updateUI();

            Toast.makeText(getApplicationContext(), "All items deleted!", Toast.LENGTH_SHORT).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
