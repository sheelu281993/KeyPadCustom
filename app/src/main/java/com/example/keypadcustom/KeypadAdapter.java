package com.example.keypadcustom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class KeypadAdapter extends ArrayAdapter<String> implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        listener.onTextChangeListener(v.getTag().toString());
    }

    interface OnKeyTypeCallback{
        void onTextChangeListener(String key);
    }

    private Context mContext;
    private String[] items;

    private OnKeyTypeCallback listener;

    public KeypadAdapter( Context context, String[]  list, OnKeyTypeCallback onKeyTypeCallback) {
        super(context, 0 , list);
        mContext = context;
        items = list;
        listener = onKeyTypeCallback;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null) listItem = LayoutInflater.from(mContext).inflate(R.layout.item_keypad, parent,false);

        TextView tvKeypad = listItem.findViewById(R.id.tvKeypad);
        tvKeypad.setText(items[position]);

        tvKeypad.setTag(items[position]);
        tvKeypad.setOnClickListener(this);

        return listItem;
    }
}