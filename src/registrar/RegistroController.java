package registrar;

import dados.InfoScore;
import dados.ScoreDados;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import placar.PlacarController;
import primarygalaxy.MenuController;

public class RegistroController implements Initializable {
    private ScoreDados lista;
    private Stage stage;
    private int score;
    
    @FXML Label scorelb;
    @FXML Label nomelb;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    public void setScore(int score) {
        this.score = score;
        scorelb.setText(String.valueOf(score));
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void typed(String key){
        if(!nomelb.getText().equals("DIGITE SEU NOME")){
            
            if(nomelb.getText().length() < 5){
                nomelb.setText(nomelb.getText() + key);
            }
            
        }else{
            nomelb.setText(key);
            
        }
    }
    
    public void backspace(){
        String text = nomelb.getText();
        
        if(text.length() >= 2){
           nomelb.setText(nomelb.getText().substring(0, text.length()-2)); 
        
        }else if(text.length() == 1){
            nomelb.setText("");
            
        }
        
    }

    public void registrar(){
        
        if(nomelb.getText().length() >= 3 && nomelb.getText().length() < 9){
            ObjectOutputStream oos = null;
            String aux = getClass().getProtectionDomain().getCodeSource().getLocation().toString();
            String path = aux.substring(5, aux.length() -17) + "Save.obj";
            
            System.out.println(path);
            //String path = "/media/jodah/Storage/Codes/Java/PrimaryGalaxy/dist/Save.obj";

            try {
                //Carregar
                File file = new File(path);
                if(!file.exists()){
                    //Criar
                    lista = new ScoreDados();

                }else{
                    //Carregar
                    try{
                        FileInputStream fin = new FileInputStream(file);
                        ObjectInputStream ois = new ObjectInputStream(fin);
                        lista = (ScoreDados) ois.readObject();

                    }catch(IOException | ClassNotFoundException e){
                        System.out.println("Nao Carregou");

                    }

                }

                //Inserir
                InfoScore info = new InfoScore(nomelb.getText(), score);
                boolean inserido = lista.inserir(info);

                if(inserido){
                    //Salvar
                    //String aux = getClass().getResource("/dados") + "/Save.obj";
                    File save = new File(path);

                    if(!save.exists()){
                        save.createNewFile();

                    }

                    FileOutputStream fos = new FileOutputStream(path);
                    oos = new ObjectOutputStream(fos);
                    oos.writeObject(lista);
                    
                    oos.close();
                    fos.close();

                    //voltar
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

            } catch (IOException ex) {
                Logger.getLogger(RegistroController.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
    }
    
}
