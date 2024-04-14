/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.mirana.tpbanquemirana.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import mg.itu.mirana.tpbanquemirana.entity.CompteBancaire;
import mg.itu.mirana.tpbanquemirana.service.GestionnaireCompte;

/**
 *
 * @author MIRANA
 */
@Named(value = "transfert")
@RequestScoped
public class Transfert {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    private long idSource;
    private long idDestination;
    private int montant;

    /**
     * Creates a new instance of Transfert
     */
    public Transfert() {
    }

    public long getIdSource() {
        return idSource;
    }

    public void setIdSource(long id) {
        this.idSource = id;
    }

    public long getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(long id) {
        this.idDestination = id;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String enregistrer() {
        CompteBancaire source = gestionnaireCompte.findById(idSource);
        CompteBancaire destination = gestionnaireCompte.findById(idDestination);
        gestionnaireCompte.transferer(source, destination, montant);
        return "listeComptes?faces-redirect=true";

    }

}
