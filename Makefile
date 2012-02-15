SOURCE = $(shell ls *.java)
MANIFEST = MANIFEST.MF
JAR = SudokuSolver.jar

all: Sudoku.class

Sudoku.class: $(SOURCE)
	javac *.java

.java.class:
	javac $<

jar: all
	jar cfm $(JAR) $(MANIFEST) *.java *.class

.PHONY: clean distclean run

clean:
	rm -rf *~

distclean: clean
	rm -rf *.class $(JAR)

run: Sudoku.class
	java Sudoku