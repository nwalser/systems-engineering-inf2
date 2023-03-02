#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include <stdbool.h>
#include <string.h>

char filename[] = "Rangliste.csv";
#define MAXT 100

#include "Rangliste.h"

int maxT = 0;
Teilnehmer teilnehmer[MAXT];

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