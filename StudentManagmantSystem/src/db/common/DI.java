/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.common;

import db.student.StudentDB;

/**
 *
 * @author RafaelAhmedov
 */
public class DI {
    
    public static DBAbstract instanceStudent(){
        return new StudentDB();
    }
    
}
