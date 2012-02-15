SRCDIR = ./src
SOURCE = $(shell ls $(SRCDIR)/*.java)
MANIFEST = MANIFEST.MF
TARGETCLASS = $(SRCDIR)/Sudoku.class
JAR = SudokuSolver.jar

all: jar

$(TARGETCLASS): $(SOURCE)
	javac $(SRCDIR)/*.java

jar: $(TARGETCLASS)
	jar cfm $(JAR) $(MANIFEST) $(SRCDIR)/*.java $(SRCDIR)/*.class

.PHONY: clean distclean run

clean:
	rm -rf *~ $(SRCDIR)/*~

distclean: clean
	rm -rf $(SRCDIR)/*.class $(JAR)

run: jar
	java -jar $(JAR)