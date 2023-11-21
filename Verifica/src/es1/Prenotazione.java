package es1;

public class Prenotazione {
    private Persona persona;
    private String data;
    private String ora;

    public Prenotazione(Persona persona, String data, String ora) throws Exception{
        controlloOra(ora);
        controlloData(data);
        this.persona = persona;
        this.data = data;
        this.ora = ora;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) throws Exception{
        controlloData(data);
        this.data = data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) throws Exception{
        controlloOra(ora);
        this.ora = ora;
    }

    public String toString() {
        return "[" + persona.toString() + ", " + data + ", " + ora + "]";
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

    private void controlloOra(String ora) throws Exception{
        try{
            if(ora == null){
                throw new NullPointerException("L'attributo non può essere di tipo null.");
            }
            if(ora.matches("")){
                throw new Exception("L'attributo non può essere vuoto.");
            }

            String[] tmp = ora.split(":");

            int ore = Integer.parseInt(tmp[0]), minuti = Integer.parseInt(tmp[1]), secondi = Integer.parseInt(tmp[2]);

            if(!(secondi >= 0 && secondi <= 59 && minuti >= 0 && minuti <= 59 && ore >= 0 && ore <= 23)){
                throw new Exception("Ora non valida");
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