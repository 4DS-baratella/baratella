package es1;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Persona {
    private String cognome;
    private String nome;
    private String codFisc;
    private String dataDiNascita;
    private static int numeroIstanze;

    public Persona() {
        numeroIstanze++;
    }

    public Persona(String cognome, String nome, String codFisc, String dataDiNascita) throws Exception{
        controlloCodiceFiscale(codFisc);
        controlloData(dataDiNascita);
        this.cognome = controlloNominativi(cognome);
        this.nome = controlloNominativi(nome);
        this.codFisc = codFisc;
        this.dataDiNascita = dataDiNascita;
        numeroIstanze++;
    }

    public Persona(Persona persona) {
        this.cognome = persona.getCognome();
        this.nome = persona.getNome();
        this.codFisc = persona.getCodFisc();
        numeroIstanze++;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) throws Exception{
        this.cognome = controlloNominativi(cognome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws Exception{
        this.nome = controlloNominativi(nome);
    }

    public String getCodFisc() {
        return codFisc;
    }

    public void setCodFisc(String codFisc) throws Exception{
        controlloCodiceFiscale(codFisc);
        this.codFisc = codFisc;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public boolean verificaOmonimia(Persona persona) {
        return this.nome.equalsIgnoreCase(persona.getNome()) &&
                this.cognome.equalsIgnoreCase(persona.getCognome());
    }

    public Integer calcolaEta(String data){
        //Fornisci un formato pesonalizzato alla stringa di data di nascita
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //Converte la stringa della data di nascita in un oggetto LocalDate utilizzando il formato personalizzato
        LocalDate dataNascita = LocalDate.parse(dataDiNascita, formatter);

        //Ottiene la data corrente
        LocalDate oggi = LocalDate.now();

        //Calcola la differenza di anni, mesi e giorni tra oggi e la data di nascita
        return Period.between(dataNascita, oggi).getYears();
    }

    public String toString() {
        return "[" + cognome + ", " + nome + ", " + codFisc + ", " + dataDiNascita + "]";
    }

    private String controlloNominativi(String nominativo) throws Exception{
        // Validazione: una o più parole composte dalle sole lettere dell'alfabeto con iniziali maiuscole
        if (nominativo.matches("^[A-Za-z]+[A-Za-z]*")) {
            // Trasformazione delle iniziali in maiuscolo
            String[] parole = nominativo.split("[\\s']");
            StringBuilder risultato = new StringBuilder();
            for (String parola : parole) {
                risultato.append(parola.substring(0, 1).toUpperCase()).append(parola.substring(1)).append(" ");
            }
            return risultato.toString().trim();
        } else {
            throw new Exception("Formato nominativo non valido: " + nominativo);
        }
    }

    private void controlloCodiceFiscale(String codiceFiscale) throws Exception{
        try{
            if(codiceFiscale == null){
                throw new NullPointerException("L'attributo non può essere di tipo null.");
            }
            if(codiceFiscale.matches("")){
                throw new Exception("L'attributo non può essere vuoto.");
            }
            if(!(codiceFiscale.matches("[A-Za-z]{6}[0-9]{2}[A-Za-z][0-9]{2}[A-Za-z][0-9]{3}[A-Za-z]"))){
                throw new Exception("Codice fiscale non valido.");
            }
        }catch (NullPointerException e){
            throw new NullPointerException(e.getMessage());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    private void controlloData(String data) throws Exception{
        try{
            if(data == null){
                throw new NullPointerException("L'attributo non può essere di tipo null.");
            }
            if(data.matches("")){
                throw new Exception("L'attributo non può essere vuoto.");
            }

            String[] tmp = data.split("/");

            int mese = Integer.parseInt(tmp[1]);
            int giorno = Integer.parseInt(tmp[0]);

            if(controlloBisestile(tmp[2])){
                if(mese == 2){
                    if(giorno <= 0 || giorno >= 30){
                        throw new Exception("Data non valida.");
                    }
                }else if(mese == 1 || mese == 3 || mese == 5 || mese == 7 || mese == 8 || mese == 10 || mese == 12){
                    if(giorno <= 0 || giorno >= 32){
                        throw new Exception("Data non valida.");
                    }
                }else if(mese == 4 || mese == 6 || mese == 9 || mese == 11){
                    if(giorno <= 0 || giorno >= 31){
                        throw new Exception("Data non valida.");
                    }
                }else{
                    throw new Exception("Data non valida.");
                }
            }else {
                if (mese == 2) {
                    if (giorno <= 0 || giorno >= 29) {
                        throw new Exception("Data non valida.");
                    }
                } else if (mese == 1 || mese == 3 || mese == 5 || mese == 7 || mese == 8 || mese == 10 || mese == 12) {
                    if (giorno <= 0 || giorno >= 32) {
                        throw new Exception("Data non valida.");
                    }
                } else if (mese == 4 || mese == 6 || mese == 9 || mese == 11) {
                    if (giorno <= 0 || giorno >= 31) {
                        throw new Exception("Data non valida.");
                    }
                }
            }
        }catch (NullPointerException e){
            throw new NullPointerException(e.getMessage());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    private boolean controlloBisestile(String aa){
        int anno = Integer.parseInt(aa);
        boolean flagAnno = false;
        if(anno % 4 == 0){
            flagAnno = true;
        }
        return flagAnno;
    }
}