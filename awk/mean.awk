    { sum += $3 }
END { printf("%.3f\n", sum / NR) }