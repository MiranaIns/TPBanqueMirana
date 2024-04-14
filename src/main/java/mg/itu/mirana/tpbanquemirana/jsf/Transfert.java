/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.mirana.tpbanquemirana.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import mg.itu.mirana.tpbanquemirana.entity.CompteBancaire;
import mg.itu.mirana.tpbanquemirana.jsf.util.Util;
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
        boolean erreur = false;

        CompteBancaire source = gestionnaireCompte.findById(idSource);
        if (source == null) {
            // Message d'erreur associé au composant source ; form:source est l'id client
            // si l'id du formulaire est "form" et l'id du champ de saisie de l'id de la source est "source"
            // dans la page JSF qui lance le transfert.
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
            erreur = true;
        } else {
            if (source.getSolde() < montant) {
                Util.messageErreur("Solde de " + source.getNom() + " insuffisant", "Solde insuffisant", "form:montant");
                erreur = true;
            }
        }

        CompteBancaire destination = gestionnaireCompte.findById(idDestination);
        if (destination == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:destination");
            erreur = true;
        }

        if (erreur) { // en cas d'erreur, rester sur la même page
            return null;
        }

        gestionnaireCompte.transferer(source, destination, montant);
        Util.addFlashInfoMessage("Transfert de " + source.getNom() + " vers " + destination.getNom() + " pour un montant de " + montant + " correctement effectué");
        return "listeComptes?faces-redirect=true";

    }

}
