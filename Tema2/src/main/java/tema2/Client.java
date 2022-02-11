package tema2;

public class Client {
    private int timpArrive;
    private int timpServire;
    private int id;

    public Client(){
    }
    public void setTimpArrive(int timpArrive){
        this.timpArrive=timpArrive;
    }
    public int getTimpArrive(){
        return this.timpArrive;
    }
    public void setTimpServire(int timpServire){
        this.timpServire=timpServire;
    }
    public int getTimpServire(){
        return this.timpServire;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return this.id;
    }
    public String toString(){
        return " ( "+this.id+" , "+this.timpArrive+" , "+this.timpServire+" ) ";
    }
}

