all:
	javac -cp $(IRLIB) -d bin src/*.java

clean:
	rm -f slide.aux slide.log slide.nav slide.out slide.snm slide.toc

pdf:
	pdflatex tex/slide.tex

run:
	java -cp $(IRLIB):./bin Main

score:
	@mkdir tmp
	@make run > tmp/data.raw
	@sed -i s/[^0-9\.\ ]//g tmp/data.raw
	@awk -f awk/score.awk tmp/data.raw
	@rm -r tmp

.PHONY: all clean pdf run score
