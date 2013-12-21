    {
        sum += $1;
        sum2 += $1 * $1;
    }
END {
        printf("Quad: %.3f\tAbs: %.3f\n", 1 - sqrt(sum2 / NR), 1 - sum / NR);
    }
