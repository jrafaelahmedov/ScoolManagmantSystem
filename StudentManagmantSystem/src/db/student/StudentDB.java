package db.student;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import db.common.DBAbstract;
import bean.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RafaelAhmedov
 */



//indi fikir ver gor biz ne elde etdik

/*
1. Sen sabah TeacherDBCOnnect yaratmaq istesen onu mutleq DBAbstractdan extends etmelisen
2.Extends etdinse mutleq getAll, insert ,update,delete,get methodlarini override etmelisen
3. connect methodu sende default olaraq var istifade ede bilersen.
4. En esasi deyerdim ki budur rahatliqla TeacherDBConnect obyektini istesen
TeacherDBInterface-e cast edersen istesen DBAbstract-a istesen de DBInterface-e
polymorfizme gore kim harda ne teleb edirse bu obyekti ora gondere bilersen

meselen ele bir method ola biler ki, forun ichinde DBInterface obyektlerini qebul edib olnarin update methodunu chagirmaq
isteye biler. Meselen

public static void callUpdate(List<DBInterface> list){
   for(DBInterface db: list){
        db.update(hansisaobyket);
    }
}

birinci mesele odur ki, sen umumiyyetle bu cure common method duzelde bilecen
yeni ki butun db classlarini eyni anda idare ede bilecen
2-ci odur ki hamini mecbur ede bilirsen ki senin kalibina uysun
3-cu odur ki, sen elave kaliplar da getire bilersen bura meselen
*/
public class StudentDB extends DBAbstract<Student>  implements StudentDBInterface<Student>{
   
    @Override
    public List<Student> getAll(String name, String surname, Integer age) {
        if (name == null && surname == null && age == null) {
            return getAll();
        }

        List<Student> result = new ArrayList<>();
        try (Connection conn = connect()) {
            PreparedStatement ps = conn.prepareStatement("select * from students where name like ? and surname like ?");
            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + surname+ "%");//set edenden sonra execute edirler
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String lname = rs.getString("name");
                String lsurname = rs.getString("surname");
                
                result.add(new Student(lname, lsurname));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Student> getAll() {
        List<Student> result = new ArrayList<>();
        try (Connection conn = connect()) {
            PreparedStatement ps = conn.prepareStatement("select * from students");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String lname = rs.getString("name");
                String lsurname = rs.getString("surname");
                int lage = rs.getInt("age");
                result.add(new Student(id, lname, lsurname, lage));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int insert(Student obj) {
        try (Connection conn = connect()) {
            PreparedStatement ps = conn.prepareStatement("insert students(name,surname,age) values (?,?,?)");
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getSurname());
            ps.setInt(3, obj.getAge());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean update(Student stu) {
        try (Connection conn = connect()) {
            PreparedStatement ps = conn.prepareStatement("update students set name=? , surname=? , age=? where id =?");
            ps.setString(1, stu.getName());
            ps.setString(2, stu.getSurname());
            ps.setInt(3, stu.getAge());
            ps.setInt(4, stu.getId());
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.getStackTrace();
        }
        return false;
    }

    @Override
    public int delete(int id) {
        try (Connection conn = connect()) {
            PreparedStatement ps = conn.prepareStatement("delete from students where id =?");
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
}
