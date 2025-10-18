package com.example;

import com.example.model.Student;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

    public static void main(String[] args) {
        // Add students
        Student student1 = addStudent("Jameeru", 95);
        Student student2 = addStudent("Elon", 80);

        System.out.println("Added: " + student1);
        System.out.println("Added: " + student2);

        // Update student1's marks
        Student updatedStudent = updateStudent(student1.getId(), "Jameeru", 98);
        System.out.println("Updated: " + updatedStudent);

        // Delete student2
        boolean deleted = deleteStudent(student2.getId());
        System.out.println("Deleted (student2): " + deleted);

        HibernateUtil.getSessionFactory().close();
    }

    public static Student addStudent(String name, int marks) {
        Transaction transaction = null;
        Student student = new Student(name, marks);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return student;
    }

    public static Student updateStudent(int id, String name, int marks) {
        Transaction transaction = null;
        Student student = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            student = session.get(Student.class, id);
            if (student != null) {
                student.setName(name);
                student.setMarks(marks);
                session.update(student);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return student;
    }

    public static boolean deleteStudent(int id) {
        Transaction transaction = null;
        boolean deleted = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null) {
                session.delete(student);
                transaction.commit();
                deleted = true;
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return deleted;
    }
}
