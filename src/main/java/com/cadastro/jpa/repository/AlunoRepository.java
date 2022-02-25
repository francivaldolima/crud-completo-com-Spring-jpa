package com.cadastro.jpa.repository;

import com.cadastro.jpa.entity.Aluno;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AlunoRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Aluno aluno){
        em.persist(aluno);
    }

    public Aluno pessoa(Long id){
        return em.find(Aluno.class, id);
    }

    public List<Aluno> pessoas(){
        Query query = em.createQuery("from Aluno");
        return query.getResultList();
    }

    public void remove(Long id){
        Aluno p = em.find(Aluno.class, id);
        em.remove(p);
    }

    public void update(Aluno aluno){
        em.merge(aluno);
    }

}
