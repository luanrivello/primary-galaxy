package jogo;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class EventoRel implements EventHandler<KeyEvent>{
    private static EventoRel evento;
    private JogoController control;
    private String[] keys;
    
    /**
     *
     * @param control
     * @param keys
     * @return
     */
    public static EventoRel getInstance(JogoController control, String[] keys){
        if(evento == null){
            evento = new EventoRel(control, keys);
        
        }else{
            evento.control  = control;
            evento.keys  = keys;
            
        }
        
        return evento;
        
    }
    
    private EventoRel(JogoController control, String[] keys){
        this.keys = keys;
        this.control = control;
        
    }
    
    @Override
    public void handle(KeyEvent event) {
        String precionado = event.getCode().toString();
        String key1 = keys[0];
        String key2 = keys[1];
        
        if(precionado.equals(key1)){
            keys[0] = null;
            this.alinhar(precionado);
            
        }else if(precionado.equals(key2)){
            keys[1] = null;
            this.alinhar(precionado);
            
        }

    }
    
    private void alinhar(String precionado){
        
        if(!control.isEnd()){
            if(precionado.equals("A") || precionado.equals("D") || precionado.equals("UP") || precionado.equals("DOWN")){
                control.alinhar();
            } 
        }
        
        /*
        if(!control.isEnd()){
            if(precionado.equals("W") || precionado.equals("S") || precionado.equals("LEFT") || precionado.equals("RIGHT")){
                control.setNaveSpeed(0);
            } 
        }
        */
        
    }
    
}
