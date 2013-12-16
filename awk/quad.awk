    { sum2 += $1 * $1 }
END { printf("%f\n", 1 - sqrt(sum2 / NR)) }
