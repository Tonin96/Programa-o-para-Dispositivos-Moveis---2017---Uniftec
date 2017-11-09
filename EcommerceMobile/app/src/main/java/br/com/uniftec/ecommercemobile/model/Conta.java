package br.com.uniftec.ecommercemobile.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by bruno on 07/11/17.
 */

public class Conta implements Serializable {
    ArrayList<Endereco> listaEndereco;

    public ArrayList<Endereco> getListaEndereco() {
        return listaEndereco;
    }

    public void setListaEndereco(ArrayList<Endereco> listaEndereco) {
        this.listaEndereco = listaEndereco;
    }
}
