/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;


import java.util.Date;
import java.util.Objects;

public class Evenement {
    private int idE;
    private String nom;
    private Date date;
    private String lieu;
    private String organisateur;
    private int nbPlace;
    private String programme;
    private String isOpen;
    private String isFree;
    private Date created_at;
    private Date updated_at;

    public Evenement(int idE, String nom, Date date, String lieu, String organisateur, int nbPlace, String programme, String isOpen, String isFree, Date created_at, Date updated_at) {
        this.idE = idE;
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.organisateur = organisateur;
        this.nbPlace = nbPlace;
        this.programme = programme;
        this.isOpen = isOpen;
        this.isFree = isFree;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Evenement(int idE, String nom, Date date, String lieu, String organisateur, Date created_at, Date updated_at) {
        this.idE = idE;
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.organisateur = organisateur;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
  public Evenement(int idE, String nom, Date date, String lieu, String organisateur,int nbPlace) {
        this.idE = idE;
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
         this.nbPlace = nbPlace;
        this.organisateur = organisateur;
      
    }
    public Evenement() {
    }

    public int getIdE() {
        return idE;
    }

    public void setIdE(int idE) {
        this.idE = idE;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(String organisateur) {
        this.organisateur = organisateur;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String isOpen() {
        return isOpen;
    }

    public void setOpen(String open) {
        isOpen = open;
    }

    public String isFree() {
        return isFree;
    }

    public void setFree(String free) {
        isFree = free;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idE, nom, date, lieu, organisateur, nbPlace, programme, isOpen, isFree, created_at, updated_at);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Evenement other = (Evenement) obj;
        return idE == other.idE && nbPlace == other.nbPlace && isOpen == other.isOpen && isFree == other.isFree && Objects.equals(nom, other.nom) && Objects.equals(lieu, other.lieu) && Objects.equals(organisateur, other.organisateur) && Objects.equals(programme, other.programme) && Objects.equals(date, other.date) && Objects.equals(created_at, other.created_at) && Objects.equals(updated_at, other.updated_at);
    }

    @Override
    public String toString() {
        return "Evenement{" +
                "idE=" + idE +
                ", nom='" + nom + '\'' +
                ", date=" + date +
                ", lieu='" + lieu + '\'' +
                  ", nbPlace='" + nbPlace + '\'' +
                ", isOpen=" + isOpen +
                ", isFree='" + isFree + '\'' +
                ", organisateur='" + organisateur + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
