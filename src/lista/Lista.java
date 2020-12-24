package lista;

public class Lista<T>{
    private Node<T> first;
    
    public void inserir(T obj){
        
        if(this.first == null){
        
            this.first = new Node(obj);

        }else{
            Node<T> aux = this.first;
            
            while(aux.getNext() != null){
                aux = aux.getNext();
            }
            
            aux.setNext(new Node(obj));
            
        }
        
    }
    
    public void remover(T obj){
        
        if(this.first.getInfo().equals(obj)){
            this.first = this.first.getNext();
            
        }else{
           Node<T> aux = this.first;
        
            while(aux.getNext() != null && !aux.getNext().getInfo().equals(obj)){
                aux = aux.getNext();
            }

            if(aux.getNext() != null){
                aux.setNext(aux.getNext().getNext());

            } 
        }
        
        
        
    }
    
    public T pegar(int index){
        Node<T> aux = this.first;
        int i = 0;
        
        while(aux != null && i < index){
            aux = aux.getNext();
            i = i + 1;
        }

        if(aux != null){
            return aux.getInfo();
            
        }else{
            return null;
            
        }

    }
    
}
