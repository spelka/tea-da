package ca.sebon.tea_da.Menu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ca.sebon.tea_da.Database.Tea;
import ca.sebon.tea_da.R;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.TeaHolder>
{
    //initialize this here so we don't have a null object before our first LiveData update.
    private List<Tea> teaList = new ArrayList<>();

    @NonNull
    @Override
    public TeaHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        //Create and return a TeaHolder list item.
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item, parent, false);
        return new TeaHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TeaHolder teaHolder, int position)
    {
        //Get the data from java objects into our TeaHolder list item
        Tea currentTea = teaList.get(position);
        teaHolder.textViewTeaType.setText(currentTea.getTeaType());

    }

    @Override
    public int getItemCount() {
        return teaList.size();
    }

    public void setTeaList(List<Tea> teaList)
    {
        this.teaList = teaList;
        notifyDataSetChanged();
    }

    class TeaHolder extends RecyclerView.ViewHolder
    {
        private TextView textViewTeaType;

        public TeaHolder(@NonNull View itemView) {
            super(itemView);
            textViewTeaType = itemView.findViewById(R.id.text_view_tea_type);
        }
    }
}
