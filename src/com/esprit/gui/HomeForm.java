package com.esprit.gui;

import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Evenement;
import com.esprit.services.ServiceEvenement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HomeForm extends Form {

    private Button btnAddPerson;
    private Button btnShowPerson;
    private Calendar calendar;
    private ServiceEvenement serviceEvenement;

    public HomeForm() {
        super("Home", BoxLayout.y());
        serviceEvenement = new ServiceEvenement(); // Create an instance of ServiceEvenement
        initializeComponents();
        addActions();
    }

    private void initializeComponents() {
        btnAddPerson = new Button("Ajouter");
        btnShowPerson = new Button("Afficher");
        calendar = new Calendar();

        setTitle("Calendar");
        setLayout(new BorderLayout());
        addComponent(BorderLayout.CENTER, calendar);
        addComponent(BorderLayout.SOUTH, new Label("Choisissez une option :"));
        addComponent(BorderLayout.SOUTH, btnAddPerson);
        addComponent(BorderLayout.SOUTH, btnShowPerson);

        calendar.addActionListener((e) -> {
            Date selectedDate = new Date(calendar.getSelectedDay());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(selectedDate);
            Log.p("You picked: " + selectedDate);
            // Pass the formatted date to the serviceEvenement to retrieve events
         List<Evenement> events = serviceEvenement.getEventsByDate(formattedDate);

    // Show the AffichePersonnesForm with the retrieved events
    new AfficheEventByDate(this, events).show();

    Log.p("events" + events);
        });
    }

    private void addActions() {
        btnAddPerson.addActionListener((evt) -> {
            new AjoutPersonneForm(this).show();
        });
        btnShowPerson.addActionListener((evt) -> {
            new AffichePersonnesForm(this).show();
        });
    }
}
