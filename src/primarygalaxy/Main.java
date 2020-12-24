package primarygalaxy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
//import lista.Lista;

public class Main extends Application{
    
    public static void main(String[] args) {
        /*
        Lista<Integer> lista = new Lista();
        
        lista.inserir(10);
        lista.inserir(20);
        lista.remover(10);
        
        System.out.println(lista.pegar(0));
        */
        
        launch(args);
        
    }
    
    @Override
    public void start(Stage stage) throws Exception {

        //Loading Scene        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = (Parent)loader.load();
        MenuController controller = loader.getController();
        controller.setStage(stage);

        Scene scene = new Scene(root, 1600, 900);

        stage.setMaxWidth(1600);
        stage.setMaxHeight(900);
        /*
        stage.setMinWidth(1600);
        stage.setMinHeight(900);
        */
        
        //Seting Stagea
        stage.setTitle("Primary Galaxy");
        stage.getIcons().add(new Image("/primarygalaxy/botoes/rby.png"));
        stage.setScene(scene);
        stage.show();        

    } 

}
