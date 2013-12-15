all:
	javac -cp $(IRLIB) -d bin src/*.java

clean:
	rm -f slide.aux slide.log slide.nav slide.out slide.snm slide.toc

pdf:
	pdflatex tex/slide.tex

run:
	java -cp $(IRLIB):./bin Main

.PHONY: all clean pdf run
