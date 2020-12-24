package jogo;

import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import lista.Lista;
import registrar.KeyPres;
import registrar.RegistroController;

public class JogoController implements Initializable{
    private int naveSpeed;
    private static final int ROTSPEED = 14;
    private int maxSpeed;
    private int aceleracao;
    private static final int MINX = 10;
    private static final int MAXX = 1458;
    private int relativeSpeed;
    @FXML private Pane layoutJogo; 
    @FXML private ImageView player;
    @FXML private ImageView gas;
    @FXML private Label scorelb;
    private Lista<Mob> mobs;
    private Stage stage;
    private int score;
    private SecureRandom random;
    private int fuel;
    private int dificuldade;
    private boolean end;
    private boolean rainbow;
    private boolean rainbowStar;
    
    //keys
    private String[] keys;
    
    //Nave imgs
    private int naveState;
    private int naveColor;
    private Image naves[][];
    /*
    0-Branca
    1-Amarelo
    2-Azul
    3-Vermelho
    */
    
    //Laser imgs
    private Image lasers[];
    /*
    0-Amarelo
    1-Azul
    2-Vermelho
    */
    
    //Estrelas img
    private Image[] stars;
    private Image[] starBranca;
    private int rainbowState;
    /*
    preta = 0
    verde = 1
    roxo = 2
    laranja = 3
    branco = 4
    */
    
    //Fuel img
    private Image[] gasImg;
    
    //Explosion img
    private Image[] explosion;
    private int explosionState;
            
    //Sound Effects
    private AudioClip[] audios;
    /*
    0- mayday
    1- rainbowAudio
    2- explosaoAudio
    3- laser
    4- coletar
    5- turbina
    */
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.score = 0;
        this.naveSpeed = 0;
        this.maxSpeed = 11;
        this.aceleracao = 1;
        this.mobs = new Lista();
        this.player.setX(MINX);
        this.player.setY(-54);
        this.relativeSpeed = 5;
        this.random = new SecureRandom();
        this.naveState = 4;
        
        //naves
        naves = new Image[4][3];
        lasers = new Image[3];
        
        int i = 1,j = 0;
        
        naves[0][0] = naves[i][j] = new Image("/jogo/imgs/v1/0/nave1.png");
        naves[0][1]= naves[i][j] = new Image("/jogo/imgs/v1/0/nave1.png");
        naves[0][2] = naves[i][j] = new Image("/jogo/imgs/v1/0/nave2.png");
        
        while(i < 4){
            
            while(j < 3){
                naves[i][j] = new Image("/jogo/imgs/v1/" + i + "/nave" + j + ".png");
                
                j = j + 1;
                
            }
      
            j = 0;
            i = i + 1;
            
        }
        
        naveColor = random.nextInt(3) + 1;       
        this.frameNave();
        
        //lasers
        i = 0;
        
        while(i < 3){
            lasers[i] = new Image("/jogo/imgs/v1/laser/laser" + i + ".png");
            i = i + 1;
        }
        
        
        //estrelas
        stars = new Image[4];
        stars[0] = new Image("/jogo/imgs/stars/starPreta.png");
        stars[1] = new Image("/jogo/imgs/stars/starVerde.png");
        stars[2] = new Image("/jogo/imgs/stars/starRoxo.png");
        stars[3] = new Image("/jogo/imgs/stars/starLaranja.png");
        
        starBranca = new Image[5];
        
        i = 0;
        while(i < 5){
            starBranca[i] = new Image("/jogo/imgs/stars/branca/starBranca" + i + ".png");
            i = i + 1;
        }

        rainbowState = 0;
        
        //gasolina
        this.fuel = (10);
        gasImg = new Image[11];
        i = 0;
        
        while(i < 11){
            
            gasImg[i] = new Image("/jogo/imgs/gas/gas" + i + ".png");
            
            i = i + 1;
            
        }
        
        //explosion
        explosion = new Image[3];
        explosionState = 0;
        
        i = 0;
        
        while(i < 3){
            explosion[i] = new Image("/jogo/imgs/v1/explosion/explosion" + i + ".png");
            i = i + 1;
        }

        end = false;
        rainbow = false;
        rainbowStar = false;

        //audio
        audios = new AudioClip[6];
        
        audios[0] = new AudioClip(getClass().getResource("/audio/mayday.mp3").toString());
        audios[0].setCycleCount(-1);
        audios[0].setVolume(0.4);
        audios[0].play();
        
        audios[1] = new AudioClip(getClass().getResource("/audio/rainbowAudio.mp3").toString());
        audios[1].setCycleCount(-1);
        audios[1].setVolume(0.3);
        
        audios[2] = new AudioClip(getClass().getResource("/audio/explosaoAudio.mp3").toString());
        audios[2].setVolume(0.1);
        
        audios[3] = new AudioClip(getClass().getResource("/audio/laser.mp3").toString());
        audios[3].setCycleCount(-1);
        audios[3].setVolume(0.2);
        
        audios[4] = new AudioClip(getClass().getResource("/audio/coleta.mp3").toString());
        audios[4].setVolume(0.2);
        audios[4].setCycleCount(1);
        
        audios[5] = new AudioClip(getClass().getResource("/audio/turbinaAudio.mp3").toString());
        audios[5].setVolume(0.05);
        audios[5].setCycleCount(-1);
        audios[5].play();
        
    }
    
    //Controle
    public void alinhar(){
        player.setRotate(90);
    }
    
    public void movimento(){
        String key1 = keys[0];
        String key2 = keys[1];
        boolean aux1 = false;
        boolean aux2 = false;
        
        if(key1 != null){
            aux1 = this.check(key1);
            
        }
        
        if(key2 != null){
            aux2 = this.check(key2);
        
        }

        if(aux1 != true && aux2 != true){
            if(naveSpeed > 0){
                this.desacelerar();

            }else if(naveSpeed < 0){
                this.acelerar();

            }
        }
        
        
    }
    
    private boolean check(String aux){
        
        if(aux.equals("W") || aux.equals("RIGHT")){
            this.acelerar();
            return true;
        }

        if(aux.equals("S") || aux.equals("LEFT")){
            this.desacelerar();
            return true;
        }
        
        if(aux.equals("A") || aux.equals("UP")){
            this.andarCima();
        }

        if(aux.equals("D")|| aux.equals("DOWN")){
            this.andarBaixo();
        }
            
        return false;
        
    }
    
    private void andarCima(){
        
        if(player.getRotate() > 70){
            player.setRotate(player.getRotate() - 3);
        }
        
        if(player.getY() > -370){
            player.setY(player.getY() - ROTSPEED);
        }

    }
    
    private void andarBaixo(){
        
        if(player.getRotate() < 110){
            player.setRotate(player.getRotate() + 3);
        }
        
        if(player.getY() < 288){
            player.setY(player.getY() + ROTSPEED);
        }

    }
    
    public void andarFrente(){
        
        if(this.naveSpeed < 0 && player.getX() > MINX || this.naveSpeed > 0 && player.getX() < MAXX){

            player.setX(player.getX() + this.naveSpeed);
            
        }else{
            this.naveSpeed = 0;
            
        }
        
    }   
    
    private void acelerar(){
        
        if(this.naveSpeed < maxSpeed){
            this.naveSpeed = this.naveSpeed + aceleracao;
        }
        
    }
    
    private void desacelerar(){
        
        if(this.naveSpeed > -maxSpeed){
            this.naveSpeed = this.naveSpeed - aceleracao;
        }
        
    }
    
    public void frameNave(){
        int aux;
        if(this.rainbow == true){
            aux = 0;
        
        }else{
            aux = this.naveColor;
            
        }
        
        switch (naveState) {
            case 4:
                this.player.setImage(naves[aux][0]);
                this.naveState = 1;
                break;
            case 1:
                this.player.setImage(naves[aux][1]);
                this.naveState = 2;
                break;
            default:
                this.player.setImage(naves[aux][2]);
                this.naveState = this.naveState + 1;
                break;

        }

    }
    
    public void frameFuel(){
        
        if(this.fuel > 9){
            this.gas.setImage(this.gasImg[10]);
        
        }else if(this.fuel < 10 && this.fuel > 0){
            this.gas.setImage(this.gasImg[this.fuel]);
            
        }else{
            this.gas.setImage(this.gasImg[0]);
            
        }
        
        
    }
    
    public void frameStarRainbow(ImageView img){
        
        if(rainbowState < 15 && rainbowState%3 == 0){
            img.setImage(starBranca[rainbowState/3]);

        }else if(rainbowState >= 15){
            img.setImage(starBranca[0]);
            rainbowState = 0;
            
        }
        
        rainbowState = rainbowState + 1;
        
    }
    
    public boolean frameExplosion(){
        
        switch (explosionState) {
            case 1:
                this.player.setRotate(90);
                this.player.setImage(explosion[1]);
                break;
                
            case 2:
                this.player.setImage(explosion[2]);
                break;
                
            case 3:
                this.player.setImage(null);
                this.explosionState = 0;
                return true;
                
            default:
                this.player.setImage(explosion[0]);
                break;

        }
        
        this.explosionState = this.explosionState + 1;
        return false;
        
    }
    
    //Get Set 
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setNaveSpeed(int naveSpeed) {
        this.naveSpeed = naveSpeed;
    }
    
    public void setGas(Image img) {
        this.gas.setImage(img);
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getDif() {
        return dificuldade;
    }
    
    public void setDif(int dif) {
        this.dificuldade = dif;
    }

    public int getNaveColor() {
        return naveColor;
    }

    public void setNaveColor(int naveColor) {
        this.naveColor = naveColor;
    }

    public void setKeys(String[] keys) {
        this.keys = keys;
    }
    
    public boolean isEnd() {
        return end;
    }

    public boolean isRainbow() {
        return rainbow;
    }
    
    public void setRainbow(boolean rainbow) {
        this.rainbow = rainbow;
    }
    
    
    
    //Score
    public void updateScore(){
        scorelb.setText("Score: " + score);

    }

    public void pontuar(){
        this.score = this.score + 200;

        if(this.score % 5000 == 0 && this.score <= 20000){
             this.relativeSpeed = this.relativeSpeed + 3;
        }
        
        if(this.score == 5000){
            this.dificuldade = 1;
            
        }else if(this.score == 10000){
            this.dificuldade = 2;
            this.maxSpeed = 14;
            this.aceleracao = 2;
            
        }
        
    }
    
    public void pontuar(int plus){
        this.score = this.score + plus;

        if(this.score % 5000 == 0 && this.score <= 20000){
             this.relativeSpeed = this.relativeSpeed + 3;
        }
        
        if(this.score > 10000 && this.score < 11000){
            this.dificuldade = 1;
            
        }else if(this.score > 20000 && this.score < 21000){
            this.dificuldade = 2;
            this.maxSpeed = 14;
            this.aceleracao = 2;
            
        }
        
    }
    
    public void spawnEstrela(){
        int cor, eixoY, tipo;
        ImageView estrela;
        
        if(this.rainbow == false && this.rainbowStar == false){
            cor = random.nextInt(140);
            
        }else{
            cor = 100;
            
        }
        
        
        if(cor == 0){
            estrela = new ImageView(starBranca[0]);
            this.rainbowStar = true;
            
            tipo = 4;
            
        }else{
            cor = random.nextInt(7);
        
            switch (cor) {
                case 0: 
                    case 1: tipo = 1;
                    break;
                case 2: case 3: tipo = 2;
                    break;
                case 4: case 5: tipo = 3;
                    break;
                default: tipo = 0;
                    break;

            }
            
            estrela = new ImageView(stars[tipo]);
            
        }
        
        estrela.setScaleX(0.1);
        estrela.setScaleY(0.1);
        estrela.setX(MAXX);
        
        /*
        -378
        288
        -442
        248
        */
        eixoY = random.nextInt(690)-442;
        estrela.setY(eixoY);
        
        
        mobs.inserir(new Mob(estrela, tipo, eixoY));
        
        layoutJogo.getChildren().add(estrela);
        
    }
    
    public void spawnLaser(){
        ImageView laser;
        int tipo;
        int rand = random.nextInt(2);
        
        switch (naveColor) {
            case 1:
                if(rand == 0){
                    laser = new ImageView(lasers[1]);
                    tipo = 6;
                    
                }else{
                    laser = new ImageView(lasers[2]);
                    tipo = 7;
                    
                }   
                
                break;
                
            case 2:
                if(rand == 0){
                    laser = new ImageView(lasers[0]);
                    tipo = 5;
                    
                }else{
                    laser = new ImageView(lasers[2]);
                    tipo = 7;
                    
                }   
                
                break;
                
            default:
                if(rand == 0){
                    laser = new ImageView(lasers[0]);
                    tipo = 5;
                    
                }else{
                    laser = new ImageView(lasers[1]);
                    tipo = 6;
                    
                }   
                
                break;
                
        }
        
        laser.setX(MAXX +200);
        laser.setY(-8);
        
        mobs.inserir(new Mob(laser, tipo, 0));
        layoutJogo.getChildren().add(laser);
        
        audios[3].play();
        
    }

    public void mover(){
        int i = 0;
        Mob aux = mobs.pegar(i);
        ImageView img;
        
        while(aux != null){
            img = aux.getImg();
            
            if(aux.getTipo() == 4){
                this.frameStarRainbow(img);
            }
            
            if((img.getX() < -550 && aux.getTipo() < 5) || (img.getX() < -100 && aux.getTipo() == -1)){
                
                if(aux.getTipo() == 4){
                    this.rainbowStar = false;
                }
                
                if(aux.getTipo() == -1){
                    audios[3].stop();
                }
                
                layoutJogo.getChildren().remove(img);
                mobs.remover(aux);
                
            }else{
                
                boolean bol = this.colisao(aux, img);
                
                if(end == true){
                    return;
                }
                
                if(bol){
                    i = i + 1;
                }
                
            }

            aux = mobs.pegar(i);
            
        }
        
        
    }
    
    private boolean colisao(Mob aux, ImageView img){
        
        img.setX(img.getX() - relativeSpeed);
                
        //Colisao
        double difY = (this.player.getY() + 378) - (aux.getY() + 442);
        double difX = img.getX() - this.player.getX() + 320;

        //System.out.println("Nave:" + "\nX: " + this.player.getX() + "\nY: " + this.player.getY());
        //System.out.println("Estrela" + i + ": " + "\nX: " + img.getX() + "\nY: " + aux.getY() + "\nDifX: " + difX + "\nDifY: " + difY);
        /*
        System.out.println("NaveX: " + (this.player.getX() + 320));
        System.out.println("StarX: " + img.getX());
        System.out.println("DifX: " + difX);
        */
        /*
        System.out.println("NaveY: " + (this.player.getY() + 378));
        System.out.println("StarY: " + (aux.getY() + 442));
        System.out.println("DifY: " + difY);

        System.out.println("--------------------");
        */

        double aux2,aux3;

        aux2 = difY*1.25;
        aux3 = -difY/2;

        if(difY > 0){
            aux2 = -aux2;
            aux3 = -aux3;

        }

        if(aux.getTipo() > -1 && aux.getTipo() < 5){
            
            if(difX < relativeSpeed + naveSpeed + aux2 && difX > -180 + aux3 + naveSpeed && difY < 80  && difY > -100){

                if(aux.getTipo() == 0 || (this.rainbow == false && ((this.naveColor == 1 && aux.getTipo() == 2) || (this.naveColor == 2 && aux.getTipo() == 3) || (this.naveColor == 3 && aux.getTipo() == 1)))){

                    this.fim();

                 }else{
                    layoutJogo.getChildren().remove(img);
                    mobs.remover(aux);
                
                    this.pontuar();  
                    
                    this.blinblin();

                    if(aux.getTipo() == 4){
                        this.rainbow = true;
                        this.rainbowStar = false;
                        
                        this.rainbowAudio();

                        this.frameNave();
                    }

                }

            }else{
                return true;

            }
                
        }else if(aux.getTipo() < 8){
            
            difX = difX - 405;

            if(difX < relativeSpeed + naveSpeed ){
                
                if(aux.getTipo() != -1){  
                    
                    if(this.player.getY() < -318 || this.player.getY() > 230){
                        this.fim();
                        return true;
                    }
                    
                    switch (aux.getTipo()) {
                        case 5:
                            this.naveColor = 1;
                            break;
                        case 6:
                            this.naveColor = 2;
                            break;
                        default:
                            this.naveColor = 3;
                            break;
                    }

                    aux.setTipo(-1);
                    
                    this.frameNave();

                }
                
            }
            
            return true;
            
        }  
       
        return false;
        
    }
    
    public void gameOver(){
        
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/registrar/Registro.fxml"));
            Parent rooot = (Parent)loader.load();
            RegistroController controller = loader.getController();
            controller.setStage(stage);
            controller.setScore(score);

            Scene scene = new Scene(rooot, stage.getWidth(), stage.getHeight());

            scene.setOnKeyPressed(new KeyPres(controller));
            
            stage.setScene(scene);        
            
        }catch(IOException ex){
            System.out.println("NotSuposedToHappen");
            
        }
         
    }
    
    public void blinblin(){

        audios[4].play();
        
    }
    
    //audio
    private void rainbowAudio(){
        audios[1].play();
        audios[0].stop();
    }
    
    //fim
    private void fim(){
        this.end = true;
        mobs = null;
        
        audios[0].stop();
        audios[1].stop();
        audios[3].stop();
        audios[4].stop();
        audios[5].stop();
        
        audios[2].play();
        
    }
    
    public void checkRainbow(){

        if(!audios[0].isPlaying()){
             audios[0].play();
             audios[1].stop();
         }
        
    }
    
}
