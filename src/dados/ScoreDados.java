package dados;

import java.io.Serializable;

public class ScoreDados implements Serializable{
    private final InfoScore[] board;
    private static final int TAM = 10;

    public ScoreDados() {
        this.board = new InfoScore[TAM];
        
    }
    
    public boolean inserir(InfoScore inserir){
        InfoScore aux = board[0];
        
        int i = 0;
        while(i < TAM-1 &&  aux != null && inserir.getPonto() <= aux.getPonto()){
            i = i + 1;
            aux = board[i];
            
        }
        
        if(aux == null){
            board[i] = inserir;
            return true;
            
        }if(inserir.getPonto() >= aux.getPonto()){
            
            if(i == TAM-1){
                board[TAM -1] = inserir;
                
            }else{
                
                board[i] = inserir;
                
                InfoScore aux2 = board[i +1];
                
                while(i < TAM -2 && board[i] != null){
                    i = i + 1;
                    board[i] = aux;
                    aux = aux2;
                    aux2 = board[i +1];

                }
                
            }
            
            return true;
            
        }
        
        return false;
                
    }
    
    public InfoScore index(int i){
        
        return i >  TAM ? null : board[i];
        
    }
    
}
