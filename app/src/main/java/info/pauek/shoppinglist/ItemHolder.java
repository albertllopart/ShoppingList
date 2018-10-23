package info.pauek.shoppinglist;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ItemHolder extends RecyclerView.ViewHolder {

    private TextView name_view;
    private CheckBox check_box;
    private ImageButton item_delete;

    public ItemHolder(@NonNull final View itemView, final ShoppingListAdapter.OnClickListener onClickListener) {
        super(itemView);
        name_view = itemView.findViewById(R.id.name_view);
        check_box = itemView.findViewById(R.id.item_checkbox);
        item_delete = itemView.findViewById(R.id.item_delete);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    int pos = getAdapterPosition();
                    onClickListener.onClick(pos);
                }
            }
        });

        item_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = getAdapterPosition();
                String msg = "Has eliminat: " + pos;
                Log.i("info", msg);
            }
        });
    }

    public void bind(ShoppingItem item) {
        name_view.setText(item.getName());
        check_box.setChecked(item.isChecked());
    }
}
