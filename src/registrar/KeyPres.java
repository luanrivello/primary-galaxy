package registrar;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyPres implements EventHandler<KeyEvent>{
    RegistroController controle;
    
    public KeyPres(RegistroController controle){
        this.controle = controle;
        
    }
    
    @Override
    public void handle(KeyEvent event) {
        String precionado = event.getCode().toString();
        
        if(precionado.equals("BACK_SPACE")){
            controle.backspace();
            
        }else if(precionado.equals("ENTER")){
            controle.registrar();
            
        }else if(!precionado.equals("SPACE")){
            controle.typed(precionado);
            
        }
        
    }
    
    
}
