SRCDIR = ./src
SOURCE = $(shell ls $(SRCDIR)/*.java)
MANIFEST = MANIFEST.MF
JAR = SudokuSolver.jar

all: Sudoku.class

Sudoku.class: $(SOURCE)
	javac $(SOURCE)

.java.class:
	javac $<

jar: all
	jar cfm $(JAR) $(MANIFEST) $(SOURCE) $(SRCDIR)/*.class

.PHONY: clean distclean run

clean:
	rm -rf *~

distclean: clean
	rm -rf $(SRCDIR)/*.class $(JAR)

run: Sudoku.class
	java Sudoku