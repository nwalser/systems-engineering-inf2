typedef struct
{
    char name[20];
    int h, min, sec;
} Teilnehmer;

int connectSocket(char *ipAddr, int port);
char *sgets(char *buffer, int bufsize, int sock);
void swap(Teilnehmer *a1, Teilnehmer *a2);
int vergleiche(Teilnehmer t1, Teilnehmer t2);
void teilnehmerSortieren(int size, Teilnehmer a[]);
int teilnehmerLaden(char *filename, Teilnehmer *teilnehmer);
int teilnehmerSpeichern(char *filename, int maxT, Teilnehmer *teilnehmer);
int teilnehmerNetzLaden(char *host, int port, Teilnehmer *teilnehmer);