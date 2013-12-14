all:
	javac -cp $IRLIB -d bin *.java

clean:
	rm -f slide.aux slide.log

pdf:
	pdflatex tex/slide.tex

run:
	java -cp $IRLIB:./bin Main

.PHONY: all clean pdf run