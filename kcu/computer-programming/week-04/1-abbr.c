#include <stdio.h>

int main(void)
{
    int x = 10, y = 10, z = 33;

    x += 1; // 11
    y *= 2; // 10

    z %= 10 + 20;   // 3

    printf("x = %d, y = %d, z = %d", x, y, z);

    return 0;
}