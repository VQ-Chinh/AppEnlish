package com.thelazyteam.entity;

import java.util.List;

/**
 * Created by AM on 5/23/2017.
 */

public class Grammar {
    private String Name;
    private String Form;
    private List<UsingGrammar> ListUsingGrammar;

    public Grammar() {

    }

    public Grammar(String name, String form, List<UsingGrammar> listUsingGrammar) {
        Name = name;
        Form = form;
        ListUsingGrammar = listUsingGrammar;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getForm() {
        return Form;
    }

    public void setForm(String form) {
        Form = form;
    }

    public List<UsingGrammar> getListUsingGrammar() {
        return ListUsingGrammar;
    }

    public void setListUsingGrammar(List<UsingGrammar> listUsingGrammar) {
        ListUsingGrammar = listUsingGrammar;
    }
}
