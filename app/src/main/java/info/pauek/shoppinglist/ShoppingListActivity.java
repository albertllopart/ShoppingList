package info.pauek.shoppinglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListActivity extends AppCompatActivity {

    // TODO: 1. Afegir un CheckBox a cada ítem, per marcar o desmarcar els ítems (al model també!)
    // TODO: 2. Que es puguin afegir elements (+ treure els inicials)
    // TODO: 3. Afegir un menú amb una opció per esborrar de la llista tots els marcats.
    // TODO: 4. Que es pugui esborrar un element amb LongClick (cal fer OnLongClickListener)

    // Model
    List<ShoppingItem> items;

    // Referències a elements de la pantalla
    private RecyclerView items_view;
    private ImageButton btn_add;
    private EditText edit_box;
    private ShoppingListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        items = new ArrayList<>();
        // items.add(new ShoppingItem("Potatoes", true));
        // items.add(new ShoppingItem("Toilet Paper", false));

        items_view = findViewById(R.id.items_view);
        btn_add = findViewById(R.id.btn_add);
        edit_box = findViewById(R.id.edit_box);

        adapter = new ShoppingListAdapter(this, items);

        items_view.setLayoutManager(new LinearLayoutManager(this));
        items_view.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        );
        items_view.setAdapter(adapter);

        adapter.setOnClickListener(new ShoppingListAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                items.get(position).toggleChecked();
                adapter.notifyItemChanged(position);
                String msg = "Has clicat: " + items.get(position).getName();
                Toast.makeText(ShoppingListActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnLongClickListener(new ShoppingListAdapter.OnLongClickListener() {
            @Override
            public void onLongClick(int position) {
                items.remove(position);
                adapter.notifyItemRemoved(position);
                Toast.makeText(ShoppingListActivity.this, "Has eliminat un item", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.doc_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Toast.makeText(this, "Delete Selected", Toast.LENGTH_SHORT).show();

        int i = 0;
        while (items.size() > i)
        {
            if (items.get(i).isChecked())
            {
                items.remove(i);
                adapter.notifyItemRemoved(i);
            }
            else
            {
                i++;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void onAddItem(View view) {
        items.add(new ShoppingItem(edit_box.getText().toString(), false));
        adapter.notifyItemInserted(items.size()-1);
    }

    public void onDeleteItem(int pos){
        items.remove(pos);
        adapter.notifyItemRemoved(pos);
    }
}
