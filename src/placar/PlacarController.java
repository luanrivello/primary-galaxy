package placar;

import dados.InfoScore;
import dados.ScoreDados;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
import primarygalaxy.MenuController;

public class PlacarController implements Initializable {
    @FXML Label 
            nome1;
    @FXML Label 
            nome2;
    @FXML Label 
            nome3;
    @FXML Label 
            nome4;
    @FXML Label 
            nome5;
    @FXML Label 
            nome6;
    @FXML Label 
            nome7;
    @FXML Label 
            nome8;
    @FXML Label 
            nome9;
    @FXML Label 
            nome10;
    @FXML Label score1;
    @FXML Label score2;
    @FXML Label score3;
    @FXML Label score4;
    @FXML Label score5;
    @FXML Label score6;
    @FXML Label score7;
    @FXML Label score8;
    @FXML Label score9;
    @FXML Label score10;
    
    private Stage stage;
    private ScoreDados lista;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String aux = getClass().getProtectionDomain().getCodeSource().getLocation().toString();
        String path = aux.substring(5, aux.length() -17) + "Save.obj";

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
        
        //Setar dados
        //1
        InfoScore dado = lista.index(0);
        String nome, ponto;
        if(dado != null){
            nome = dado.getNome();
            ponto = String.valueOf(dado.getPonto());
            
            
            nome1.setText(nome);
            score1.setText(ponto);
        }
        
        //2
        dado = lista.index(1);
        if(dado != null){
            nome = dado.getNome();
            ponto = String.valueOf(dado.getPonto());
            
            nome2.setText(nome);
            score2.setText(ponto);
            
        }
        
        //3
        dado = lista.index(2);
        if(dado != null){
            nome = dado.getNome();
            ponto = String.valueOf(dado.getPonto());
            
            nome3.setText(nome);
            score3.setText(ponto);
            
        }
        
        //4
        dado = lista.index(3);
        if(dado != null){
            nome = dado.getNome();
            ponto = String.valueOf(dado.getPonto());
            
            nome4.setText(nome);
            score4.setText(ponto);
            
        }
        
        //5
        dado = lista.index(4);
        if(dado != null){
            nome = dado.getNome();
            ponto = String.valueOf(dado.getPonto());
            
            nome5.setText(nome);
            score5.setText(ponto);
            
        }
        
        //6
        dado = lista.index(5);
        if(dado != null){
            nome = dado.getNome();
            ponto = String.valueOf(dado.getPonto());
            
            nome6.setText(nome);
            score6.setText(ponto);
            
        }
        
        //7
        dado = lista.index(6);
        if(dado != null){
            nome = dado.getNome();
            ponto = String.valueOf(dado.getPonto());
            
            nome7.setText(nome);
            score7.setText(ponto);
            
        }
        
        //8
        dado = lista.index(7);
        if(dado != null){
            nome = dado.getNome();
            ponto = String.valueOf(dado.getPonto());
            
            nome8.setText(nome);
            score8.setText(ponto);
            
        }
        
        //9
        dado = lista.index(8);
        if(dado != null){
            nome = dado.getNome();
            ponto = String.valueOf(dado.getPonto());
            
            nome9.setText(nome);
            score9.setText(ponto);
            
        }
        
        //10
        dado = lista.index(9);
        if(dado != null){
            nome = dado.getNome();
            ponto = String.valueOf(dado.getPonto());
            
            nome10.setText(nome);
            score10.setText(ponto);
            
        }
        
    }    
    
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
