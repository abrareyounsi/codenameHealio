/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.esprit.entities.Evenement;
import com.esprit.services.ServiceEvenement;
import java.util.List;

/**
 *
 * @author abdel
 */
public class AffichePersonnesForm extends Form {

    private Form previousForm;

    public AffichePersonnesForm(Form f) {
        super("Affichage", BoxLayout.y());
        previousForm = f;
        OnGui();
        addActions();
    }

private void OnGui() {
    List<Evenement> evenements = new ServiceEvenement().afficher();

    Container container = new Container(BoxLayout.y());

    for (Evenement evenement : evenements) {
        String eventInfo = "ID: " + evenement.getIdE() + "\n"
                + "Nom: " + evenement.getNom() + "\n"
                + "Date: " + evenement.getDate() + "\n"
                + "Lieu: " + evenement.getLieu() + "\n"
                + "Organisateur: " + evenement.getOrganisateur() + "\n"
                + "Nombre de places: " + evenement.getNbPlace() + "\n\n";

        SpanLabel spanLabel = new SpanLabel(eventInfo);
        spanLabel.getAllStyles().setBorder(Border.createLineBorder(1, 0x999999));
        spanLabel.getAllStyles().setPadding(5, 5, 5, 5);
        spanLabel.getAllStyles().setMargin(5, 5, 5, 5);
        spanLabel.getAllStyles().setBgColor(0xEEEEEE);
        spanLabel.getAllStyles().setFgColor(0x333333);

        Button participationButton = new Button("Participer");
        participationButton.addActionListener((evt) -> {
            // Open a dialog to enter personal data
            // Implement the dialog functionality according to your requirements
            Dialog dialog = new Dialog("Ajouter mon informations");
            dialog.setLayout(BoxLayout.y());

            TextField nameField = new TextField();
            nameField.setHint("Nom complete");
            dialog.add(nameField);

            TextField emailField = new TextField();
            emailField.setHint("Email");
            dialog.add(emailField);

            // Add more fields as needed

            Button submitButton = new Button("Participer");
            submitButton.addActionListener((submitEvt) -> {
                // Handle the submit action
                String name = nameField.getText();
                String email = emailField.getText();

                // Process the entered data
          new          ServiceEvenement().incrementNbPlace(evenement.getIdE());
                dialog.dispose(); // Close the dialog
            });
            dialog.add(submitButton);

            dialog.show();
        });

        Container eventContainer = BorderLayout.center(spanLabel);
        eventContainer.add(BorderLayout.SOUTH, participationButton);

        container.add(eventContainer);
    }

    this.add(container);
}


    private void addActions() {
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previousForm.showBack();
        });
    }
}
