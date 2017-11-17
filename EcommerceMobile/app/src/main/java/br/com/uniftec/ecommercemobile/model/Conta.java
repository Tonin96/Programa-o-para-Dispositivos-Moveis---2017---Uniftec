package br.com.uniftec.ecommercemobile.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by bruno on 07/11/17.
 */

public class Conta implements Serializable {
    ArrayList<UsuarioEndereco> listaEndereco;

    public ArrayList<UsuarioEndereco> getListaEndereco() {
        return listaEndereco;
    }

    public void setListaEndereco(ArrayList<UsuarioEndereco> listaEndereco) {
        this.listaEndereco = listaEndereco;
    }
}
