package com.beile.mulitline;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.beile.multiline.R;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.beile.mulitline.adapter.MultilineAdapter;
import com.beile.mulitline.bean.MultilineBean;

import java.util.ArrayList;

/**
 * 填空题，最多占用一行，输入超过一行，换行显示
 */
public class BlanksActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<MultilineBean> multilineBeanList;
    private MultilineAdapter multilineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        initDatas();
    }

    public void space(View v) {
//        startActivity(new Intent(this, LinesActivity.class));
        startActivity(new Intent(this, MainActivity.class));
    }

    private void initDatas() {
        multilineBeanList = new ArrayList<MultilineBean>();
        String[] testDatas = new String[]{"d", "i", "no", "sa", "ou", "r", "hell1234567", "o", "good morning", "good", "evening", "good bye bye bye bye ", "3456789", "67888", "欢迎来到Github我的家"};
        for (int i = 0; i < testDatas.length; i++) {
            MultilineBean multilineBean = new MultilineBean();
            multilineBean.setTitle(testDatas[i]);
            multilineBeanList.add(multilineBean);
        }

        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(BlanksActivity.this);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        flexboxLayoutManager.setJustifyContent(JustifyContent.CENTER);
        mRecyclerView.setLayoutManager(flexboxLayoutManager);
        if (multilineAdapter == null) {
            multilineAdapter = new MultilineAdapter(this, multilineBeanList);
            mRecyclerView.setAdapter(multilineAdapter);
            mRecyclerView.setSelected(true);
        } else {
            multilineAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
