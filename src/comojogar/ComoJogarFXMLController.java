package comojogar;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import placar.PlacarController;
import primarygalaxy.MenuController;

public class ComoJogarFXMLController {
    private Stage stage;

    public void voltar(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/primarygalaxy/Menu.fxml"));
            Parent rooot = (Parent)loader.load();
            MenuController controller = loader.getController();
            controller.setStage(stage);
            
            Scene scene = new Scene(rooot, stage.getWidth(), stage.getHeight());
             
            stage.setScene(scene);
            
        } catch (IOException ex) {
            Logger.getLogger(PlacarController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
}
