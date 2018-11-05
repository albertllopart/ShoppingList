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

    public ItemHolder(@NonNull final View itemView, final ShoppingListAdapter.OnClickListener onClickListener, final ShoppingListAdapter.OnLongClickListener onLongClickListener) {
        super(itemView);
        name_view = itemView.findViewById(R.id.name_view);
        check_box = itemView.findViewById(R.id.item_checkbox);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    int pos = getAdapterPosition();
                    onClickListener.onClick(pos);
                }
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onLongClickListener != null)
                {
                    int position = getAdapterPosition();
                    onLongClickListener.onLongClick(position);
                    return true;
                }
                else
                {
                    return false;
                }
            }
        });
    }

    public void bind(ShoppingItem item) {
        name_view.setText(item.getName());
        check_box.setChecked(item.isChecked());
    }
}
