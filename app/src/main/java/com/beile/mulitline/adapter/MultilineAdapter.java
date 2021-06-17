package com.beile.mulitline.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beile.mulitline.bean.MultilineBean;
import com.beile.multiline.R;

import java.util.ArrayList;

public class MultilineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private ArrayList<MultilineBean> itemList;
    private int dp13;
    private int screenWidth;
    private float density;
    private boolean isFirstEt = true;

    public MultilineAdapter(Context context, ArrayList<MultilineBean> itemlist) {
        mContext = context;
        itemList = itemlist;
        dp13 = (int) mContext.getResources().getDimension(R.dimen.dp_13);
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        density = dm.density;
        screenWidth = (int) (width / density);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_multiline, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int index) {
        if (viewHolder instanceof ItemViewHolder) {
            MultilineBean searchHistoryBean = itemList.get(index);
            final ItemViewHolder itemViewHold = ((ItemViewHolder) viewHolder);
            if (index == 5 || index == 8 || index == 12) {
                if (isFirstEt) {
                    itemViewHold.mTitle.setFocusable(true);
                    itemViewHold.mTitle.setFocusableInTouchMode(true);
                    itemViewHold.mTitle.requestFocus();
                    isFirstEt = false;
                }
                itemViewHold.mTitle.setEnabled(true);
                itemViewHold.mTitle.setPadding(dp13, 0, dp13, 0);
//                itemViewHold.mTitle.setFilters(new InputFilter[]{new InputFilter.LengthFilter((int) ((screenWidth - 40) * density / 21) - 4)});
            } else {
                itemViewHold.listItemLayout.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                itemViewHold.mTitle.setEnabled(false);
                itemViewHold.mTitle.setMinWidth(0);
                itemViewHold.mTitle.setMinimumWidth(0);
            }
            itemViewHold.mTitle.setText(searchHistoryBean.getTitle());
            itemViewHold.mTitle.setSelection(searchHistoryBean.getTitle().length());
            itemViewHold.mTitle.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                    return false;
                }
            });
            itemViewHold.mTitle.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s != null) {
                        String s1 = s.toString();
                        int editableLength = (int) itemViewHold.mTitle.getPaint().measureText(s1);
                        if (editableLength >= (screenWidth - 40) * density - 30) {
                            itemViewHold.mTitle.setFilters(new InputFilter[]{new InputFilter.LengthFilter(s1.length())});
                        }
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        LinearLayout listItemLayout;
        EditText mTitle;

        public ItemViewHolder(View view) {
            super(view);
            listItemLayout = (LinearLayout) view.findViewById(R.id.ll_layout);
            mTitle = (EditText) view.findViewById(R.id.et_title);
        }
    }
}