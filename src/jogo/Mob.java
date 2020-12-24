package jogo;

import javafx.scene.image.ImageView;

public class Mob {
    private ImageView img;
    private int y;
    private int tipo;
    
    /*
    desativo = -1
    
    estrela:
    preta = 0
    verde = 1
    roxo = 2
    laranja = 3
    branco = 4
    
    laser:
    amarelo = 5
    azul = 6
    vermelho = 7
    */

    public Mob(ImageView img, int tipo) {
        this.img = img;
        this.tipo = tipo;
    }
    
    public Mob(ImageView img, int tipo, int y) {
        this.img = img;
        this.tipo = tipo;
        this.y = y;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        
        if(obj instanceof Mob){
            Mob aux = (Mob) obj;
            
            if(img.equals(aux.img)){
                return true;
            }
            
        }
        
        return false;
        
    }
    
}
