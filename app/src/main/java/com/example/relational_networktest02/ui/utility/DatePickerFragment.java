package com.example.relational_networktest02.ui.utility;

import static android.content.ContentValues.TAG;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private DatePickerDialog.OnDateSetListener mListener;
    // 创建对话框
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // 创建日期选择器对话框
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);

        // 返回日期选择器对话框
        return datePickerDialog;
    }

    // 设置日期
    public void onDateSet(DatePicker view, int year, int month, int day) {
        // TODO: 在这里处理选择的日期
        if (mListener != null) {
            mListener.onDateSet(view, year, month, day);
        }

        Log.d(TAG, "时间: "+year+":"+(month+1)+":"+day);
        String str=year+""+(month+1)+""+day;
        return ;
    }
    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        mListener = listener;
    }
}

