package tema2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Executie implements Runnable {
    private int timpDeSimulare;
    private int timpScurs = 0;
    private View view;
    private int nrCozi;
    private GeneratorClienti generator;
    private List<SimulatorCoada> cozi;
    private FileWriter file;

    public Executie(int nrCozi, GeneratorClienti generator, int timpDeSimulare, View view) {
        this.view = view;
        this.nrCozi = nrCozi;
        this.generator = generator;
        this.timpDeSimulare = timpDeSimulare;
        this.cozi = new ArrayList<SimulatorCoada>();
        try {
            this.file = new FileWriter("fisier.txt");
        } catch (IOException e) {
            System.out.println("Nu s-a putut crea fisierul.");
        }
        for (int i = 0; i < nrCozi; i++) {
            this.cozi.add(new SimulatorCoada(i, generator));
        }
        Thread one = new Thread(this);
        one.start();
    }

    @Override
    public void run() {
        boolean okAsteptare = true, okCozi = true;
        String mesaj = "";
        int peakHour = 0;
        int maximSumaPeakHour = 0;
        float waitingTime = 0;
        float sumaWaitingTime = 0;
        float avgServiceTime = this.generator.getAverageServiceTime();
        float impartitorAvgWaitingTime = 0;
        while (timpScurs < this.timpDeSimulare && (okAsteptare == true || okCozi == true)) {
            if (this.generator.verificareListaGoala() == true) {
                okAsteptare = false;
            }
            for (int i = 0; i < nrCozi; i++) {
                cozi.get(i).setTimpScurs(this.timpScurs);
                Thread th = new Thread(cozi.get(i));
                th.start();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            mesaj = "Timp: " + (timpScurs) + "\n";
            int sumaPeakHour = 0;
            for (SimulatorCoada coada : this.cozi) {
                mesaj = mesaj + coada.afisareClientCoada(coada.getIndex()) + "\n";
                sumaPeakHour += coada.getNumarClientiCoada();
                sumaWaitingTime += coada.getWaitingTimeCoada();
                if (coada.getCoada().isEmpty() || coada.getCoada().size() == 1)
                    impartitorAvgWaitingTime++;
            }
            if (sumaPeakHour > maximSumaPeakHour) {
                maximSumaPeakHour = sumaPeakHour;
                peakHour = timpScurs;
            }
            mesaj = mesaj + this.generator.afiseazaClientiInAsteptare() + "\n";
            view.setAfisare(mesaj);
            this.timpScurs++;
            if (okAsteptare == false) {
                okCozi = false;
                for (SimulatorCoada coada : this.cozi) {
                    if (!(coada.getCoada().isEmpty())) {
                        okCozi = true;
                    }
                }
            }
            try {
                file.write(mesaj);
                file.write("\n");
            } catch (IOException e) {
                System.out.println("Nu s-a putut scrie in fisier.");
            }
        }
        waitingTime = sumaWaitingTime / ((timpDeSimulare * nrCozi) - impartitorAvgWaitingTime);
        mesaj = "Timp " + timpScurs + "\n";
        for (SimulatorCoada coada : this.cozi) {
            mesaj = mesaj + coada.afisareClientCoada(coada.getIndex()) + "\n";
        }
        mesaj = mesaj + this.generator.afiseazaClientiInAsteptare() + "\n" + "Sfarsit.\n" + "Ora de varf: " + peakHour + "\n";
        mesaj = mesaj + "Timp mediu de servire: " + avgServiceTime + "\n";
        mesaj = mesaj + "Timp mediu de asteptare in coada: " + waitingTime + "\n";
        view.setAfisare(mesaj);
        try {
            file.write(mesaj);
            file.close();
        } catch (IOException e) {
            System.out.println("Nu s-a putut scrie in fisier.");
        }
    }
}
