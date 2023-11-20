package com.example.pnuemonia_detection;

public class Patient {
    public String nom;
    public String prenom;
    public String email;
    public String description;
    public String xrayLink;
    public String doctor;

    // Add getters and setters for each field

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getXrayLink() {
        return xrayLink;
    }

    public void setXrayLink(String xrayLink) {
        this.xrayLink = xrayLink;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
