/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.mirana.tpbanquemirana.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import mg.itu.mirana.tpbanquemirana.entity.CompteBancaire;
import mg.itu.mirana.tpbanquemirana.jsf.util.Util;
import mg.itu.mirana.tpbanquemirana.service.GestionnaireCompte;

/**
 * Backing bean de la page qui liste les comptes
 *
 * @author MIRANA
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    private List<CompteBancaire> listeComptes;

    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }

    public List<CompteBancaire> getAllComptes() {
        if (listeComptes == null) {
            listeComptes = gestionnaireCompte.getAllComptes();
        }
        return listeComptes;
    }

    public String supprimerCompte(CompteBancaire compteBancaire) {
        gestionnaireCompte.supprimerCompte(compteBancaire);
        Util.addFlashInfoMessage("Le compte de " + compteBancaire.getNom() + " a été supprimé");
        return "listeComptes?faces-redirect=true";
    }

}
