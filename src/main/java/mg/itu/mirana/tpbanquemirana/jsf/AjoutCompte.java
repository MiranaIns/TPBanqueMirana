/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.mirana.tpbanquemirana.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.PositiveOrZero;
import mg.itu.mirana.tpbanquemirana.entity.CompteBancaire;
import mg.itu.mirana.tpbanquemirana.jsf.util.Util;
import mg.itu.mirana.tpbanquemirana.service.GestionnaireCompte;

/**
 * Backing bean pour la page ajouter un compte bancaire
 *
 * @author MIRANA
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    private String nom;
    
    @PositiveOrZero
    private int solde;

    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public String creer() {
        gestionnaireCompte.creerCompte(new CompteBancaire(nom, solde));
        Util.addFlashInfoMessage("Compte créé avec succès pour " + nom + " avec un solde initial de " + solde + ".");
        return "listeComptes?faces-redirect=true";
    }

}
