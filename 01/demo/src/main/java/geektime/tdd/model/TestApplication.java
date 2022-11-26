package geektime.tdd.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Optional;

public class TestApplication {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        StudentRepository studentRepository = new StudentRepository(entityManager);
        Student john = studentRepository.save(new Student("john", "smith", "john.smith@emai.com"));

        entityManager.getTransaction().commit();

        System.out.println(john.getId());

        Optional<Student> loaded= studentRepository.findById(john.getId());
        System.out.println(loaded);

        Optional<Student> loadedByEmail = studentRepository.findByEmail("john.smith@emai.com");
        System.out.println(loadedByEmail);

        Optional<Student> loadedByNoValidEmail = studentRepository.findByEmail("john.smith1@emai.com");
        System.out.println(loadedByNoValidEmail);

        entityManager.close();
        entityManagerFactory.close();
    }
}


