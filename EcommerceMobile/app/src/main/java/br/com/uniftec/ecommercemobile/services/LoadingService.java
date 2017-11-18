package br.com.uniftec.ecommercemobile.services;

import android.app.Activity;

public interface LoadingService {

    public void progressDialog(Activity activity, String mensagem);
    public void dismisProgressDialog();
}
