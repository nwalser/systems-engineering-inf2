import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.lang.Integer.parseInt;

public class RanglisteDAO {
    protected final int MAX = 3200;
	protected Teilnehmer[] rangliste = Teilnehmer.newArray(MAX);
	protected int max = 0;
	protected String connection;
	
	public void connect(String connectionString) {
	    this.connection = connectionString;
	}
	
	public int load() throws DAOException {
       	fillDemoData();
		return rangliste.length;
	}
	
	public int save()  throws DAOException {
	    return max;
	}

    private void fillDemoData() {
		max = 0;
		rangliste[max++] = new Teilnehmer("Mueller Stefan", 2, 31, 14);
		rangliste[max++] = new Teilnehmer("Marti Adrian", 2, 30, 9);
		rangliste[max++] = new Teilnehmer("Kiptum Daniel", 2, 11, 31);
		rangliste[max++] = new Teilnehmer("Ancay Tarcis", 2, 20, 2);
		rangliste[max++] = new Teilnehmer("Kreibuhl Christian", 2, 21, 47);
		rangliste[max++] = new Teilnehmer("Ott Michael", 2, 33, 48);
		rangliste[max++] = new Teilnehmer("Menzi Christoph", 2, 27, 26);
		rangliste[max++] = new Teilnehmer("Oliver Ruben", 2, 32, 12);
		rangliste[max++] = new Teilnehmer("Elmer Beat", 2, 33, 53);
		rangliste[max++] = new Teilnehmer("Kuehni Martin", 2, 33, 36);
    }

	public RanglisteDAO() {}

	public void create(Teilnehmer teilnehmer) throws DAOException{
		if(find(teilnehmer.getName())!=null)
			throw new DAOException("Teilnehmer schon vorhanden");
		rangliste[max] = teilnehmer;
		max++;
	}
	
	public Teilnehmer[] read()  {
	    Teilnehmer[] tn = new Teilnehmer[max];
	    System.arraycopy(rangliste,0,tn,0,max);
	    return tn;
	}
	
	public void update(Teilnehmer teilnehmer) throws DAOException{
		if(find(teilnehmer.getName())==null)
			throw new DAOException("Name nicht vorhanden");
		if(teilnehmer.getH()+teilnehmer.getMin()+teilnehmer.getSec()==0)
			throw new DAOException("Zeit ungültig");
		int i = findIdx(teilnehmer.getName());
		rangliste[i] = teilnehmer;
	}

	public void delete(Teilnehmer teilnehmer) throws DAOException{
		if(find(teilnehmer.getName())==null)
			throw new DAOException("Name nicht vorhanden");
		int i = findIdx(teilnehmer.getName());
		if (i >= 0) {
    		for (; i < max - 1; i++) {
    			rangliste[i] = rangliste[i + 1];
    		}	
    		max--;
        }
	}
	
	private int findIdx(String name) {
		for (int i = 0; i < max && rangliste[i] != null; i++) {
			if (rangliste[i].getName().equals(name)) {
				return i;
			}
		}		
		return -1;	
	}

	public Teilnehmer find(String name)  {
		int i = findIdx(name);
		return (i >=0)?rangliste[i]:null;
	}
	
	static class TimeCompare implements Comparator<Teilnehmer> {
        public int compare(Teilnehmer t1, Teilnehmer t2) {
            return (t1.h * 3600 + t1.min * 60 + t1.sec)
                    - (t2.h * 3600 + t2.min * 60 + t2.sec);
        }
    }

    public void sort() {
        Arrays.sort(rangliste, 0, max, new TimeCompare());
    }
	

}