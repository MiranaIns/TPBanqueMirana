/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.mirana.tpbanquemirana.service;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import mg.itu.mirana.tpbanquemirana.entity.CompteBancaire;

/**
 * Façade pour gérer les comptes.
 *
 * @author MIRANA
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3307,
        user = "newuser",
        password = "newuser123??",
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true",
            "driverClass=com.mysql.cj.jdbc.Driver"
        }
)
@ApplicationScoped
public class GestionnaireCompte {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void creerCompte(CompteBancaire compte) {
        em.persist(compte);
    }

    @Transactional
    public List<CompteBancaire> getAllComptes() {
        TypedQuery query = em.createNamedQuery("CompteBancaire.findAll", CompteBancaire.class);
        return query.getResultList();
    }

    public long nbComptes() {
        TypedQuery<Long> query = em.createQuery("select count(c) from CompteBancaire c", Long.class);
        return query.getSingleResult();
    }
}
