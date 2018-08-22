package db.student;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

/**
 *
 * @author RafaelAhmedov
 */
public interface StudentDBInterface<T> {

    public abstract List<T> getAll(String name, String surname, Integer age);

}
