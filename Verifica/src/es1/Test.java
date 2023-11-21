package es1;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        try {
            // Creazione di un'istanza di Persona
            Persona persona = new Persona("Rossi", "Marco", "RSSMRC95M16H224T", "29/02/1984");

            // Creazione di un'istanza di Prenotazione
            Prenotazione prenotazione = new Prenotazione(persona, "21/11/2023", "07:50:00");

            // Visualizzazione delle informazioni sulla Persona e sulla Prenotazione
            System.out.println("Informazioni Persona: " + persona.toString());
            System.out.println("\nInformazioni Prenotazione: " + prenotazione.toString());

            // Verifica dell'omonimia con un'altra Persona
            Persona altraPersona = new Persona("Rossi", "Maria", "RSSMRC95M16H224T", "20/09/2000");
            if (persona.verificaOmonimia(altraPersona)) {
                System.out.println("\nLe due persone sono omonime.");
            } else {
                System.out.println("\nLe due persone non sono omonime.");
            }

            //Stampo le informazioni del nuovo oggetto
            System.out.println("\nInformazioni Persona: " + altraPersona.toString());

            //Stampo l'età della persona
            System.out.println("\nL'età della persona " + altraPersona.getNome() + " " + altraPersona.getCognome() + " è: " + altraPersona.calcolaEta(altraPersona.getDataDiNascita()) + " anni.");
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }
}