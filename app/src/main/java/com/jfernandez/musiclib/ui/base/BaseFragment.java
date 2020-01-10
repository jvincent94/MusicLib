package com.jfernandez.musiclib.ui.base;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.widget.Toast;

public class BaseFragment extends Fragment implements MvpView {

    private ProgressDialog progressDialog;
    /**
     * This method is use to display toast message from API call result
     * @param error needed to display in Toast widget
     */
    @Override
    public void showMessage(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    /**
     * This method is use to show progress dialog
     */
    @Override
    public void showProgressDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading. Please wait. . .");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    /**
     * This method is use to hide progress dialog
     */
    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

}
