package org.example.models;

import java.sql.Date;

public class Commantaire {
    private int idcomm;

    public int getIdpub() {
        return idpub;
    }

    public void setIdpub(int idpub) {
        this.idpub = idpub;
    }

    private int idpub;
   private Publication pub_id;
   private String contenucom,name;
   private Date datecomm;

    public Commantaire() {

    }

    @Override
    public String toString() {
        return "Commantaire{" +
                "idcomm=" + idcomm +
                ", pub_id=" + pub_id +
                ", contenucom='" + contenucom + '\'' +
                ", name='" + name + '\'' +
                ", datecomm=" + datecomm +
                '}';
    }

    public Commantaire(int idpub, String contenucom, String name, Date datecomm) {
        this.idpub = idpub;
        this.contenucom = contenucom;
        this.name = name;
        this.datecomm = datecomm;
    }

    public Commantaire(Publication pub_id, String contenucom, String name, Date datecomm) {
        this.pub_id = pub_id;
        this.contenucom = contenucom;
        this.name = name;
        this.datecomm = datecomm;
    }

    public Commantaire(int idcomm, int idpub, String contenucom, String name, Date datecomm) {
        this.idcomm = idcomm;
        this.idpub = idpub;
        this.contenucom = contenucom;
        this.name = name;
        this.datecomm = datecomm;
    }

    public Commantaire(int idcomm, Publication pub_id, String contenucom, String name, Date datecomm) {
        this.idcomm = idcomm;
        this.pub_id = pub_id;
        this.contenucom = contenucom;
        this.name = name;
        this.datecomm = datecomm;
    }

    public int getIdcomm() {
        return idcomm;
    }

    public void setIdcomm(int idcomm) {
        this.idcomm = idcomm;
    }

    public Publication getPub_id() {
        return pub_id;
    }

    public void setPub_id(Publication pub_id) {
        this.pub_id = pub_id;
    }

    public String getContenucom() {
        return contenucom;
    }

    public void setContenucom(String contenucom) {
        this.contenucom = contenucom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDatecomm() {
        return datecomm;
    }

    public void setDatecomm(Date datecomm) {
        this.datecomm = datecomm;
    }
}
