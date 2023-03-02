# Rangliste Module wird uebersetzt und ausgefuehrt
all: clean build
clean: 
	@echo ==================== Clean ====================
	rm -f *.exe *.lib *.dll *.o
# Programm uebersetzen 
build: Rangliste.c 
	@echo ==================== Module Rangliste.dll building ... ====================
	gcc -shared -lws2_32 -D_DLL -Wl,--out-implib,Rangliste.lib Rangliste.c -o Rangliste.dll -lwsock32
	@echo ==================== Module RanglisteView compiling ... ====================
	gcc -c RanglisteView.c
	@echo ==================== RanglisteView linking ... ====================
	gcc  RanglisteView.o -L . ./Rangliste.lib -lws2_32 -o RanglisteView.exe
# Programm  starten
.SILENT: run #kein Echo des make Kommandos
run: 
	@echo == Run Program ==
	./RanglisteView.exe