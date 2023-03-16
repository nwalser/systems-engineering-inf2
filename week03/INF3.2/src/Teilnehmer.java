public class Teilnehmer {
	protected String name;
	protected int h;
	protected int min;
	protected int sec;
	
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    
    public int getH() {return h;}
    public void setH(int h) {this.h = h;}
    
    public int getMin() {return min;}
    public void setMin(int m) {this.min = m;}
    
    public int getSec() {return sec;}
    public void setSec(int s) {this.sec = s;}
    
    public Teilnehmer() {}
    
    public Teilnehmer(String name, int h, int min, int sec) {
        this.name = name;
        this.h = h;
        this.min = min;
        this.sec = sec;
    }
    
    public String toString() {
        return String.format("%-22s\t%02d:%02d:%02d\n", getName(), h, min, sec);
    }
    
    public static Teilnehmer[] newArray(int size) {
        return new Teilnehmer[size];
    }
}