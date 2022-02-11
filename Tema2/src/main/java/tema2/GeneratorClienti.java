package tema2;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GeneratorClienti {
    private int nrClienti;
    private BlockingQueue<Client> clienti ;
    private int timpServireMin;
    private int timpServireMax;
    private int timpArriveMin;
    private int timpArriveMax;

    public GeneratorClienti(int nrClienti, int timpServireMin, int timpServireMax, int timpArriveMin, int timpArriveMax) {
        this.nrClienti=nrClienti;
        this.timpServireMin=timpServireMin;
        this.timpServireMax=timpServireMax;
        this.timpArriveMin=timpArriveMin;
        this.timpArriveMax=timpArriveMax;
        this.clienti=new LinkedBlockingQueue<Client>(nrClienti);
        for(int i=1;i<=this.nrClienti;i++){
            try {
                this.clienti.put(generareClient(i));
            }
            catch (InterruptedException e){}
        }
    }

    public Client generareClient(int i){
        Random rand= new Random();
        Client client=new Client();
        int timpServire=rand.nextInt((this.timpServireMax-this.timpServireMin)+1)+this.timpServireMin;
        int timpArrive=rand.nextInt((this.timpArriveMax-this.timpArriveMin)+1)+this.timpArriveMin;
        client.setId(i);
        client.setTimpArrive(timpArrive);
        client.setTimpServire(timpServire);
        return client;
    }

    public float getAverageServiceTime(){
        int nrClienti=this.nrClienti;
        float sumaServiceTime=0;
        for(Client client : this.clienti){
            sumaServiceTime+=client.getTimpServire();
        }
        return (sumaServiceTime/nrClienti);
    }

    public String afiseazaClientiInAsteptare(){
        if(!this.clienti.isEmpty()) {
            String mesaj;
            mesaj="In asteptare se afla: ";
            for(Client client : this.clienti){
                mesaj=mesaj+client.toString();
            }
            return mesaj;
        }
        else {
            return "Nu mai sunt clienti in asteptare.";
        }
    }

    public BlockingQueue<Client> getClienti(){
        return this.clienti;
    }

    public boolean verificareListaGoala(){
        if(this.clienti.isEmpty())
            return true;
        return false;
    }

}
