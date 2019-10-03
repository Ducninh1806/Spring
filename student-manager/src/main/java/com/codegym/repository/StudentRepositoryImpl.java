package com.codegym.repository;

import com.codegym.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentRepositoryImpl implements StudentRepository{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query= em.createQuery("select s from Student s",Student.class);
        return query.getResultList();
    }

    @Override
    public Student findById(Long id) {
        TypedQuery<Student> query= em.createQuery("select s from Student s where s.id=:id",Student.class);
        query.setParameter("id",id);
        try {
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void save(Student model) {
        if (model.getId()!= null){
            em.merge(model);
        }else {
            em.persist(model);
        }
    }

    @Override
    public void remove(Long id) {
        Student student = findById(id);
        if (student != null){
            em.remove(student);
        }
    }
}
