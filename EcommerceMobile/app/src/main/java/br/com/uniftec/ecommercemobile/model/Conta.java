package br.com.uniftec.ecommercemobile.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by bruno on 07/11/17.
 */

public class Conta implements Serializable {
    ArrayList<EnderecoUsuario> listaEndereco;

    public ArrayList<EnderecoUsuario> getListaEndereco() {
        return listaEndereco;
    }

    public void setListaEndereco(ArrayList<EnderecoUsuario> listaEndereco) {
        this.listaEndereco = listaEndereco;
    }
}
