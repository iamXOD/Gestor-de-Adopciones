/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import auxiliar.Connector;
import controller.MainViewController;
import dao.AdoptanteDAO;
import dao.MascotaDAO;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Adoptante;
import model.Mascota;

/**
 *
 * @author XOD
 */
public class Main extends Application {

    ObservableList<Mascota> mascotas;
    ObservableList<Adoptante> adoptantes;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connector.createSchema();
        launch(args);
    }

    @Override
    public void init() {
        MascotaDAO m = new MascotaDAO();
        AdoptanteDAO a = new AdoptanteDAO();
        mascotas = m.getAll();
        adoptantes = a.getAll();
    }

    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.setTitle("Gestor de Adopciones");
//        stage.show();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainView.fxml"));

        Parent sceneMain = loader.load();

        MainViewController controller = loader.<MainViewController>getController();
        controller.initData(mascotas, adoptantes);

        Scene scene = new Scene(sceneMain);
        stage.setScene(scene);
        stage.setTitle("Gestor de Adopciones");
        stage.show();
    }

}
