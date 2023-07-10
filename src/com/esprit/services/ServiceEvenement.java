/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.esprit.entities.Evenement;
import com.esprit.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author abdel
 */
public class ServiceEvenement implements IService<Evenement> {

    private boolean responseResult;
    private final List<Evenement> evenment;
    
    private final String URI = Statics.BASE_URL + "/evenement/";

    public ServiceEvenement() {
        evenment = new ArrayList();
    }

    @Override
    public boolean ajouter(Evenement t) {
        ConnectionRequest request = new ConnectionRequest();
        
        request.setUrl(URI);
        request.setHttpMethod("POST");

        request.addArgument("nom", t.getNom());
        request.addArgument("prenom", t.getNom());

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 201; // Code HTTP 201 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    @Override
    public boolean modifier(Evenement t) {
        ConnectionRequest request = new ConnectionRequest();
        
        request.setUrl(URI + t.getIdE());
        request.setHttpMethod("PUT");

        request.addArgument("nom", t.getNom());
        request.addArgument("prenom", t.getNom());

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    @Override
    public boolean supprimer(Evenement t) {
        ConnectionRequest request = new ConnectionRequest();
        
        request.setUrl(URI + t.getIdE());
        request.setHttpMethod("DELETE");

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    /**
     *
     * @return
     */
    
  @Override
public List<Evenement> afficher() {
    List<Evenement> evenement = new ArrayList<>(); // Create a new list to store Evenement objects

    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(URI);
    request.setHttpMethod("GET");

    request.addResponseListener((evt) -> {
        try {
            InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
            Map<String, Object> result = new JSONParser().parseJSON(jsonText);
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> obj : list) {
                int id = (int) Float.parseFloat(obj.get("idE").toString());
                String nom = obj.get("nom").toString();
                String lieu = obj.get("lieu").toString();
                String organisateur = obj.get("organisateur").toString();
                int nbPlace = (int) Float.parseFloat(obj.get("nbPlace").toString());
                String dateString = obj.get("date").toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(dateString);
                // Extract other properties from the JSON response

                Evenement evenementObj = new Evenement(id, nom, date, lieu, organisateur, nbPlace);
                evenement.add(evenementObj);
            }
        } catch (IOException | ParseException ex) {
            System.out.println(ex.getMessage());
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(request);

    return evenement;
}
   public boolean incrementNbPlace(int eventId) {
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(URI + eventId + "/increment");
        request.setHttpMethod("PUT");

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 200; // HTTP 200 OK
        });

        NetworkManager.getInstance().addToQueueAndWait(request);
        return responseResult;
    }
   public List<Evenement> getEventsByDate(String selectedDate) {
    List<Evenement> evenements = new ArrayList<>();

    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(URI + "date/" + selectedDate);
    request.setHttpMethod("GET");

    request.addResponseListener((evt) -> {
        try {
            InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
            Map<String, Object> result = new JSONParser().parseJSON(jsonText);
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> obj : list) {
                int id = (int) Float.parseFloat(obj.get("idE").toString());
                String nom = obj.get("nom").toString();
                String lieu = obj.get("lieu").toString();
                String organisateur = obj.get("organisateur").toString();
                int nbPlace = (int) Float.parseFloat(obj.get("nbPlace").toString());
                String dateString = obj.get("date").toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(dateString);

                Evenement evenementObj = new Evenement(id, nom, date, lieu, organisateur, nbPlace);
                
                evenements.add(evenementObj);
            }
        } catch (IOException | ParseException ex) {
            System.out.println(ex.getMessage());
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(request);

    return evenements;
}




}
