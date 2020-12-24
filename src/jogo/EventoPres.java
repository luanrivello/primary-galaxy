package jogo;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class EventoPres implements EventHandler<KeyEvent>{
    private static EventoPres evento;
    private String[] keys;
    
    public static EventoPres getInstance(String[] keys){
        
        if(evento == null){
            evento = new EventoPres(keys);
        
        }else{
            evento.keys  = keys;

        }
        
        return evento;
        
    }
    
    private EventoPres(String[] keys){
        this.keys = keys;
        
    }
    
    @Override
    public void handle(KeyEvent event) {
        String precionado = event.getCode().toString();

        if(keys[0] != null && keys[0].equals(precionado)){
            return;
        }
        
        if(keys[1] != null && keys[1].equals(precionado)){
            return;
        }
        
        if(keys[0] == null ){
            keys[0] = precionado;

        }else if(keys[1] == null){
            keys[1] = precionado;

        }

    }
    
}
