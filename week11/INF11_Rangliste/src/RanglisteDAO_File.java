import java.io.*;

import static java.lang.Integer.parseInt;

public class RanglisteDAO_File extends RanglisteDAO{

    @Override
    public int load() throws DAOException {
        String[] items;
        try{
            FileReader inFileReader = new FileReader(connection);
            BufferedReader inReader = new BufferedReader(inFileReader);
            String line;
            max=0;
            while((line = inReader.readLine()) != null){
                items = line.split("[;:]+");
                rangliste[max++] = new Teilnehmer(items[0], parseInt(items[1]), parseInt(items[2]), parseInt(items[3]));
            }
            inReader.close();
        }
        catch (IOException e){
            throw new DAOException("Datei nicht gefunden");
        }
        return max;
    }

    @Override
    public int save() throws DAOException {
        try{
            FileWriter fWrt = new FileWriter(connection);
            PrintWriter outWrt = new PrintWriter(fWrt);
            for(int i=0;i<max;i++){
                outWrt.println(rangliste[i].getName() + ";" + rangliste[i].getH() + ":" + rangliste[i].getMin() + ":" + rangliste[i].getSec()
                );
            }
            outWrt.close();
        }
        catch (IOException e){
            throw  new DAOException("Datei nicht gefunden");
        }
        return max;
    }
}
