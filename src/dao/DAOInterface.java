/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javafx.collections.ObservableList;

/**
 *
 * @author HAROLD
 * @param <Object>
 */
public interface DAOInterface<Object> {

    public Object get(int ID);

    public ObservableList<Object> getAll();

    public int add(Object o);

    public int update(Object o);

    public boolean delete(int ID);
}
