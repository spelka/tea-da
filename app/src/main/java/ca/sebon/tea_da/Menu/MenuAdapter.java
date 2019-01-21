package ca.sebon.tea_da.Menu;

import android.content.Context;
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
import de.hdodenhof.circleimageview.CircleImageView;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.TeaHolder>
{
    //initialize this here so we don't have a null object before our first LiveData update.
    private List<Tea> teaList = new ArrayList<>();

    private Context mContext;

    @NonNull
    @Override
    public TeaHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        //Capture the context of the parent
        mContext = parent.getContext();

        //Create, inflate and return a TeaHolder list item.
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item, parent, false);
        return new TeaHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TeaHolder teaHolder, int position)
    {
        //For each item in the list, put the data from java objects into our TeaHolder list item
        Tea currentTea = teaList.get(position);
        teaHolder.textViewTeaType.setText(currentTea.getTeaType());
        teaHolder.circleImageViewTeaType.setImageResource(currentTea.getTeaImage());
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
        private CircleImageView circleImageViewTeaType;

        public TeaHolder(@NonNull View itemView) {
            super(itemView);
            textViewTeaType = itemView.findViewById(R.id.text_view_menu_item);
            circleImageViewTeaType = itemView.findViewById(R.id.circle_image_view_menu_item);
        }
    }
}