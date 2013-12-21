all:
	javac -cp $(IRLIB):./lib -d bin src/*.java

clean:
	rm -f slide.aux slide.log slide.nav slide.out slide.snm slide.toc

pdf:
	pdflatex tex/slide.tex

run:
	java -cp $(IRLIB):./lib:./bin Main

.PHONY: all clean pdf run
