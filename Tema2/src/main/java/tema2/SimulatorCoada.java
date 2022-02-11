package tema2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SimulatorCoada implements Runnable {

    private BlockingQueue<Client> coada;
    private int index;
    private GeneratorClienti clienti;
    private int timpScurs;

    public SimulatorCoada(int index, GeneratorClienti clienti) {
        this.index = index;
        this.clienti = clienti;
        this.timpScurs = 0;
        this.coada = new LinkedBlockingQueue<>();
    }

    public BlockingQueue<Client> getCoada() {
        return this.coada;
    }

    public int getIndex() {
        return this.index;
    }

    public void setTimpScurs(int timp) {
        this.timpScurs = timp;
    }

    public int getNumarClientiCoada(){
        return coada.size();
    }

    public float getWaitingTimeCoada(){
        float waitingTime;
        float sumaServiceTime=0;
        for(Client client : this.coada){
            sumaServiceTime+=client.getTimpServire();
        }
        if(this.coada.size()!=0 && this.coada.size()!=1) {
            waitingTime=sumaServiceTime/this.coada.size();
            return waitingTime;
        }
        else return 0;
    }

    public String afisareClientCoada(int index) {
        if (this.coada.isEmpty())
            return "Coada " + (index + 1) + " este goala";
        else {
            String mesaj = "In coada " + (index + 1) + " se afla: ";
            for (Client client : this.coada) {
                mesaj = mesaj + client.toString() + " ";
            }
            return mesaj;
        }
    }

    @Override
    public void run() {
        if (this.coada.isEmpty()) {
            for (Client client : this.clienti.getClienti()) {
                if (timpScurs >= client.getTimpArrive()) {
                    try {
                        this.clienti.getClienti().remove(client);
                        this.coada.put(client);
                    } catch (InterruptedException e) {
                    }
                }
            }
        } else {
            if (this.coada.element().getTimpServire() == 1) {
                this.coada.remove(this.coada.element());
            } else {
                this.coada.element().setTimpServire(this.coada.element().getTimpServire() - 1);
            }
        }
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){}

    }
}
