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


public class AfficheEventByDate extends Form {

    private Form previousForm;

    public AfficheEventByDate(Form f, List<Evenement> evenements) {
        super("Affichage par date", BoxLayout.y());
        previousForm = f;
        OnGui(evenements);
        addActions();
    }

    private void OnGui(List<Evenement> evenements) {
        if (evenements.isEmpty()) {

            SpanLabel noEventsLabel = new SpanLabel("Il n'y a pas d'événements.");
            noEventsLabel.getAllStyles().setAlignment(CENTER);
            this.add(noEventsLabel);
        } else {
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
                    
                    Dialog dialog = new Dialog("Ajouter vos infos ");
                    dialog.setLayout(BoxLayout.y());

                    TextField nameField = new TextField();
                    nameField.setHint("Nom complet");
                    dialog.add(nameField);

                    TextField emailField = new TextField();
                    emailField.setHint("Email");
                    dialog.add(emailField);


                    Button submitButton = new Button("Participer");
                    submitButton.addActionListener((submitEvt) -> {

                        String name = nameField.getText();
                        String email = emailField.getText();

                        new ServiceEvenement().incrementNbPlace(evenement.getIdE());
                        dialog.dispose(); 
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
    }

    private void addActions() {
        this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
            previousForm.showBack();
        });
    }
}
