module es.damdi.andresrl.adressappmavenjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;

    requires java.prefs;
    requires flexjson;
    requires org.controlsfx.controls;

    requires javafx.web;
    requires flexmark;
    requires flexmark.util.ast;
    requires PDFViewerFX;

    requires eu.hansolo.tilesfx;


    opens es.damdi.andresrl.adressappmavenjavafx to javafx.fxml;
    opens es.damdi.andresrl.adressappmavenjavafx.controller;
    opens es.damdi.andresrl.adressappmavenjavafx.model;
    opens es.damdi.andresrl.adressappmavenjavafx.view;


    exports es.damdi.andresrl.adressappmavenjavafx;
}