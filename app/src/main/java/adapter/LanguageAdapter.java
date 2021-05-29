package adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.tysa.R;

import Config.ConstValue;
import util.LanguagePrefs;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.MyViewHolder> {

    private onItemClick onItemClick;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_lang;
        View view_divider;

        public MyViewHolder(View view) {
            super(view);
            tv_lang = view.findViewById(R.id.tv_language);
            view_divider = view.findViewById(R.id.view_language);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    ConstValue.getLanguageList languageList = ConstValue.getLanguageList.values()[position];
                    if (onItemClick != null) {
                        onItemClick.OnItemClick(position, languageList);
                    }
                }
            });

        }
    }

    public LanguageAdapter(Context context, onItemClick onItemClick) {
        this.context = context;
        this.onItemClick = onItemClick;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_language, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ConstValue.getLanguageList mList = ConstValue.getLanguageList.values()[position];

        holder.tv_lang.setText(mList.name());

        if (LanguagePrefs.getLang(context).equals(mList.getValue())) {
            holder.tv_lang.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }

        if (position == (ConstValue.getLanguageList.values().length - 1)) {
            holder.view_divider.setVisibility(View.GONE);
        } else {
            holder.view_divider.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return ConstValue.getLanguageList.values().length;
    }

    public interface onItemClick {
        public void OnItemClick(int position, ConstValue.getLanguageList lang);
    }

}
