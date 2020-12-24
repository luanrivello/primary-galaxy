package jogo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

/**
 *
 * @author jodah
 */
public class Time {
    private final JogoController controle;
    private final Timeline timeline1;
    private final Timeline timeline2;
    private final Timeline timeline3;
    private final Timeline laserTimer1;
    private final Timeline laserTimer2;
    private final Timeline spawn1;
    private final Timeline spawn2;
    private final Timeline spawn3;
    private final Timeline explosion;
    
    private boolean laserIncome;
    private boolean change;
    private int delay;
    private int delayRain;
    
    public Time(JogoController controle){
        this.controle = controle;
        this.timeline1 = new Timeline(new KeyFrame(Duration.millis(500), this::doStepUm));
        timeline1.setCycleCount(Timeline.INDEFINITE);
        
        this.timeline2 = new Timeline(new KeyFrame(Duration.millis(25), this::doStepDois));
        timeline2.setCycleCount(Timeline.INDEFINITE);
        
        this.timeline3 = new Timeline(new KeyFrame(Duration.millis(500), this::doStepTres));
        timeline3.setCycleCount(Timeline.INDEFINITE);
        
        this.laserTimer1 = new Timeline(new KeyFrame(Duration.seconds(30), this::doStepLaser1));
        laserTimer1.setCycleCount(Timeline.INDEFINITE);
        
        this.laserTimer2 = new Timeline(new KeyFrame(Duration.seconds(15), this::doStepLaser2));
        laserTimer2.setCycleCount(Timeline.INDEFINITE);
        
        this.spawn1 = new Timeline(new KeyFrame(Duration.millis(450), this::doStepSpawn1));
        spawn1.setCycleCount(Timeline.INDEFINITE);
        
        this.spawn2 = new Timeline(new KeyFrame(Duration.millis(250), this::doStepSpawn2));
        spawn2.setCycleCount(Timeline.INDEFINITE);
        
        this.spawn3 = new Timeline(new KeyFrame(Duration.millis(175), this::doStepSpawn3));
        spawn3.setCycleCount(Timeline.INDEFINITE);
        
        this.explosion = new Timeline(new KeyFrame(Duration.millis(250), this::doStepExplosion));
        explosion.setCycleCount(Timeline.INDEFINITE);

        this.laserIncome = false;
        this.change = false;
        this.delay = 0;
        this.delayRain = 0;

    }
     
    //Timeline1
    private void doStepUm(ActionEvent action){   
        if(!controle.isEnd()){
            
            //Animacao Nave
            controle.frameNave();

            if(controle.getDif() == 1){
                spawn1.stop();
                spawn2.play();
                controle.setDif(-1);

            }else if(controle.getDif() == 2){
                spawn2.stop();
                spawn3.play();
                change = true;
                controle.setDif(-1);

            }
        
        }

    }

    //Timeline3
    private void doStepDois(ActionEvent action){
        if(controle.isEnd()){
            this.stop();
            explosion.play();
        
        }else{
            //Pontuacao
            controle.updateScore();

            //movimento
            controle.movimento();
            controle.andarFrente();
            controle.mover();

            //controle.frameFuel();
            if(controle.isRainbow() && delayRain == 0){
                timeline3.play();
                
            }
        }

    }
    
    //timeline3
    private void doStepTres(ActionEvent action){
        
        if(delayRain == 19){
           controle.checkRainbow(); 
        
        }else if(delayRain == 20){
            controle.setRainbow(false);
            delayRain = 0;
            timeline3.stop();
            return;
        }
        
        delayRain = delayRain + 1;

    }
    
    //laser counter
    private void doStepLaser1(ActionEvent action){
        
        laserIncome = true;
        
        if(change){
            laserTimer2.play();
            laserTimer1.stop();

        }
        
    }
    
    private void doStepLaser2(ActionEvent action){
        
        laserIncome = true;
        
    }
    
    //Spawn1
    private void doStepSpawn1(ActionEvent action){
        //Estrelas
        if(!controle.isEnd()){
            if(laserIncome == false){
                controle.spawnEstrela();

            }else{

                if(delay == 4){
                    controle.spawnLaser();

                }else if(delay > 6){
                    laserIncome = false;
                    delay = 0;

                }

                delay = delay + 1;

            }
        }
    }
    
    //Spawn2
    private void doStepSpawn2(ActionEvent action){
        //Estrelas
        if(!controle.isEnd()){
           if(laserIncome == false){
                controle.spawnEstrela();

            }else{

                if(delay == 4){
                    controle.spawnLaser();

                }else if(delay > 7){
                    laserIncome = false;
                    delay = 0;

                }

                delay = delay + 1;

            }
        }
    }
    
    //Spawn3
    private void doStepSpawn3(ActionEvent action){
        //Estrelas
        if(!controle.isEnd()){
            if(laserIncome == false){
                controle.spawnEstrela();

            }else{

                if(delay == 4){
                    controle.spawnLaser();

                }else if(delay > 7){
                    laserIncome = false;
                    delay = 0;

                }

                delay = delay + 1;

            }
        }
    }
    
    //Explosion
    private void doStepExplosion(ActionEvent action){
        
        if(delay < 4){
            controle.frameExplosion();
            
        }else if(delay > 4){
            delay = 0;
            explosion.stop();
            controle.gameOver();
        }
        
        delay = delay + 1;
        
    }
    
    public void start(){
        this.timeline1.play();
        this.timeline2.play();
        this.laserTimer1.play();
        this.spawn1.play();
    }
    
    public void stop(){
        this.timeline1.pause();
        this.timeline2.pause();
        this.timeline3.pause();
        this.laserTimer1.pause();
        this.laserTimer2.pause();
        this.spawn1.pause();
        this.spawn2.pause();
        this.spawn3.pause();
    }
    
}
