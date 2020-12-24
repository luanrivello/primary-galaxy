package primarygalaxy;

import comojogar.ComoJogarFXMLController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import jogo.*;
import placar.PlacarController;

public class MenuController implements Initializable{
    private Stage stage;
    @FXML BorderPane mainPane;
    private AudioClip audio;
    private FXMLLoader loader;
    
    public void jogar() throws IOException{
        
        loader = new FXMLLoader(getClass().getResource("/jogo/Jogo.fxml"));
        Parent root = (Parent)loader.load();
        JogoController controller = loader.getController();
        controller.setStage(stage);
        
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        
        //Evento
        String[] keys = new String[2];
        scene.setOnKeyPressed(EventoPres.getInstance(keys));
        scene.setOnKeyReleased(EventoRel.getInstance(controller, keys));
        controller.setKeys(keys);
        
        //Animacao
        Time timeline = new Time(controller);
        timeline.start();
        
        audio.stop();
        
        stage.setScene(scene);
        
    }
    
    public void score(){
        
        audio.stop();
        
        try {
            loader = new FXMLLoader(getClass().getResource("/placar/Placar.fxml"));
            Parent root = (Parent)loader.load();
            PlacarController controller = loader.getController();
            controller.setStage(stage);
            
            Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
            
            stage.setScene(scene);
            
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    public void comoJogar(){

        audio.stop();
        
        try {
            loader = new FXMLLoader(getClass().getResource("/comojogar/ComoJogarFXML.fxml"));
            Parent root = (Parent)loader.load();
            ComoJogarFXMLController controller = loader.getController();
            controller.setStage(stage);
            
            Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
            
            stage.setScene(scene);
            
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }
    
    public void close(){
        stage.close();
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //audio
        audio = new AudioClip(getClass().getResource("/audio/stellarisFTL.mp3").toString());
        audio.setCycleCount(-1);
        audio.setVolume(0.4);
        audio.play();

        
        //lista
        
    }
    
}
