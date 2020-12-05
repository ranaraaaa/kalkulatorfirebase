package com.example.kalkulatorjelek;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public List<Kalkulator> kalkulatorList;
    private Context context;

    public RecyclerViewAdapter(ArrayList<Kalkulator> kalkulatorArrayList, MainActivity mainActivity) {
        this.kalkulatorList = kalkulatorArrayList;
        this.context = mainActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_design, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final String angk1 = kalkulatorList.get(position).getAngka1();
        final String angk2 = kalkulatorList.get(position).getAngka2();
        final String methd = kalkulatorList.get(position).getMethod();
        final String hasl = kalkulatorList.get(position).getHasil();

        holder.angka1.setText(angk1);
        holder.angka2.setText(angk2);
        holder.method.setText(methd);
        holder.hasil.setText(hasl);
    }

    @Override
    public int getItemCount() {
        return kalkulatorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView angka1, angka2, method, hasil;
        LinearLayout list_item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            angka1 = itemView.findViewById(R.id.angka1);
            angka2 = itemView.findViewById(R.id.angka2);
            method = itemView.findViewById(R.id.method);
            hasil = itemView.findViewById(R.id.Hasil);
        }
    }
}
