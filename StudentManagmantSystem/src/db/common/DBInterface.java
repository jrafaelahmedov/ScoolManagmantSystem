/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.common;

import java.util.List;

/**
 *
 * @author RafaelAhmedov
 */
public interface DBInterface<T> {
    
    public abstract List<T> getAll();

    public abstract int insert(T obj);

    public abstract boolean update(T obj);

    public abstract int delete(int id);
}
