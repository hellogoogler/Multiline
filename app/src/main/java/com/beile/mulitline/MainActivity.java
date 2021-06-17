package com.beile.mulitline;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.beile.multiline.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static Context mContext;

    ClassDemo demo = new ClassDemo();
//    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        mContext = this;

//        for (float x = 1; x < 1000; x++) {
//            for (float y = 1; y < 1000; y++) {
//                for (float z = 1; z < 1000; z++) {
//                    if (1 / x + 1 / y + 1 / z == 1 / 14f && x != y && y != z && x != z) {
//                        Log.i("满足条件的答案：", " x " + x + " y " + y + " z " + z);
//                        i++;
//                    }
//                }
//            }
//        }
//        Log.i("满足条件的答案个数：", String.valueOf(i/6));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    public class ClassDemo {
        private static final String TAG = "ClassDemo";
        public int[] arr = new int[10000];

        public ClassDemo() {
            Log.d(TAG, "ClassDemo: ");
        }
    }
}
