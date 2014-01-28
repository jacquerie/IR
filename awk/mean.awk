    { sum += $2 }
END { printf("%.3f\n", sum / NR) }
