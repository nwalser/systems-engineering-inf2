#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include <stdbool.h>
#include <string.h>

#ifdef _MSC_VER
#include <Ws2tcpip.h>
#include <Mswsock.h>
#else
#include <unistd.h>
#endif

#ifdef WIN32
#include <Winsock2.h>
#include <windows.h>
#include <wininet.h>
#include <stdlib.h>
typedef int socklen_t;
#else
#include <unistd.h>
#include <errno.h>
#include <sys/socket.h>
#include <sys/ioctl.h>
#include <netinet/in.h>
#include <netinet/tcp.h>
#include <arpa/inet.h>
#include <netdb.h>
int GetLastError() { return errno; }
#endif

#define BUFSIZE 1024

#define MAXT 100
#define MAX_LEN 100

char filename[] = "Rangliste.csv";

typedef struct
{
	char name[20];
	int h, min, sec;
} Teilnehmer;

int maxT = 0;
Teilnehmer teilnehmer[MAXT];

// connect socket
int connectSocket(char *ipAddr, int port)
{
	int error;
	int sock = 0;
	static struct sockaddr_in serv_addr;

#ifdef WIN32
	// Initialize Winsock
	WSADATA wsaData;
	if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0)
	{
		return -WSAGetLastError();
	}
#endif
	if ((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0)
		return -GetLastError();
	serv_addr.sin_family = AF_INET;
	serv_addr.sin_port = htons(port);
	serv_addr.sin_addr.s_addr = inet_addr(ipAddr);

	if (connect(sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0)
		return -GetLastError();
	return sock;
}

// read line from socket
char *sgets(char *buffer, int bufsize, int sock)
{
	int valread = recv(sock, buffer, bufsize, 0);
	buffer[valread] = '\0';
	return (valread > 0) ? buffer : NULL;
}

void swap(Teilnehmer *a1, Teilnehmer *a2)
{
	Teilnehmer temp;
	temp = *a1;
	*a1 = *a2;
	*a2 = temp;
}

int vergleiche(Teilnehmer t1, Teilnehmer t2)
{
	return (t1.sec + 60 * t1.min + 3600 * t1.h) - (t2.sec + 60 * t2.min + 3600 * t2.h);
}

void teilnehmerSortieren(int size, Teilnehmer a[])
{
	int i, n;
	for (n = size; n > 1; n = n - 1)
	{
		for (i = 0; i < n - 1; i = i + 1)
		{
			if (vergleiche(a[i], a[i + 1]) > 0)
			{
				swap(&a[i], &a[i + 1]);
			}
		}
	}
}

// lade Teilnehmer Liste
// gebe Anzahl geladener Zeilnehmer zurück
// oder (negative) Fehlercode
int teilnehmerLaden(char *filename, Teilnehmer *teilnehmer)
{
	FILE *stream;
	char line[MAX_LEN], *result;
	stream = fopen(filename, "r");

	if (stream == NULL)
		return -2;

	int teilnehmerIndex = 0;

	while ((result = fgets(line, MAX_LEN, stream)) != NULL)
	{
		Teilnehmer tn;

		char *name = strtok(result, ",;");
		char *time = strtok(NULL, ",;");

		int idx;
		for (int idx = 0; idx < 20; idx++)
		{
			tn.name[idx] = name[idx];
		}

		tn.h = atoi(strtok(time, ":"));
		tn.min = atoi(strtok(NULL, ":"));
		tn.sec = atoi(strtok(NULL, ":"));

		teilnehmer[teilnehmerIndex] = tn;
		teilnehmerIndex++;
	}

	fclose(stream);

	return teilnehmerIndex;
}

// speichere Teilnehmer Liste
// gebe Anzahl gespeicherter Zeilnehmer zurück
// oder (negative) Fehlercode
int teilnehmerSpeichern(char *filename, int maxT, Teilnehmer *teilnehmer)
{
	FILE *stream;
	stream = fopen(filename, "w");

	if (stream == NULL)
		return -2;

	for (int teilnehmerIndex = 0; teilnehmerIndex < maxT; teilnehmerIndex++)
	{
		Teilnehmer tn = teilnehmer[teilnehmerIndex];
		fprintf(stream, "%s;%02d:%02d:%02d\n", tn.name, tn.h, tn.min, tn.sec);
	}

	fclose(stream);

	return maxT;
}

int teilnehmerNetzLaden(char *host, int port, Teilnehmer *teilnehmer)
{
	int sock = 0;
	char buffer[BUFSIZE] = {0};
	sock = connectSocket(host, port);
	if (sock > 0)
	{
		int teilnehmerIndex = 0;

		while (sgets(buffer, BUFSIZE, sock) != NULL)
		{
			Teilnehmer tn;

			char *name = strtok(buffer, ",;");
			char *time = strtok(NULL, ",;");

			int idx;
			for (int idx = 0; idx < 20; idx++)
			{
				tn.name[idx] = name[idx];
			}

			tn.h = atoi(strtok(time, ":"));
			tn.min = atoi(strtok(NULL, ":"));
			tn.sec = atoi(strtok(NULL, ":"));

			teilnehmer[teilnehmerIndex] = tn;
			teilnehmerIndex++;
		}

		return teilnehmerIndex;
	}
	else
	{
		fprintf(stderr, "\nSocket error %d\n", -sock);
	}
	shutdown(sock, 2);

	return -2;
}

void teilnehmerAusgeben(int maxT, Teilnehmer *teilnehmer)
{
	printf("\nTeilnehmerliste\n");
	int i;
	for (i = 0; i < maxT; i++)
	{
		printf("%-20s %02d:%02d:%02d\n", teilnehmer[i].name, teilnehmer[i].h, teilnehmer[i].min, teilnehmer[i].sec);
	}
}

Teilnehmer leseTeilnehmer(void)
{
	Teilnehmer t;
	printf("Name: ");
	scanf(" %19[^\n]s", t.name);
	printf("Zeit (h:min:sec): ");
	scanf(" %d:%d:%d", &(t.h), &(t.min), &(t.sec));
	return t;
}

void teilnehmerErfassen(Teilnehmer *teilnehmer)
{
	teilnehmer[maxT] = leseTeilnehmer();
	maxT++;
}

int zeigeAuswahlMenu()
{
	char eingabe[10];
	printf("\nRANGLISTENVERWALTUNG \n");
	printf(" 1)	Teilnehmer erfassen	\n");
	printf(" 2)	Teilnehmer ausgeben\n");
	printf(" 3)	Teilnehmer nach	Rang ordnen\n");
	printf(" 4)	Teilnehmer laden \n");
	printf(" 5)	Teilnehmer speichern \n");
	printf(" 6)	Teilnehmer alle	loeschen \n");
	printf(" 7)	Teilnehmer vom Server laden \n");
	printf(" 9)	Programm verlassen\n");
	printf(">");
	scanf("%s", eingabe);
	return atoi(eingabe);
}

void zeigeResultat(char *msg, int res)
{
	if (res >= 0)
		printf("\n%d %s\n", res, msg);
	else
		fprintf(stderr, "\nErrorcode: %d\n", -res);
}

int main()
{
	int res;
	while (true)
	{
		int eingabe = zeigeAuswahlMenu();
		switch (eingabe)
		{
		case 1:
			teilnehmerErfassen(teilnehmer);
			break;
		case 2:
			teilnehmerAusgeben(maxT, teilnehmer);
			break;
		case 3:
			teilnehmerSortieren(maxT, teilnehmer);
			break;
		case 4:
			res = teilnehmerLaden(filename, teilnehmer);
			zeigeResultat("Teilnehmer geladen", res);
			if (res >= 0)
				maxT = res;
			break;
		case 5:
			res = teilnehmerSpeichern(filename, maxT, teilnehmer);
			zeigeResultat("Teilnehmer gespeichert", res);
			if (res >= 0)
				maxT = res;
			break;
		case 7:
			res = teilnehmerNetzLaden("127.0.0.1", 8000, teilnehmer);
			zeigeResultat("Teilnehmer geladen", res);
			if (res >= 0)
				maxT = res;
			break;
		case 6:
			maxT = 0;
			break;
		case 9:
			return 0;
		default:
			break;
		}
	}
	return 0;
}