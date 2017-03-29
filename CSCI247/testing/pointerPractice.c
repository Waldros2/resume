#include <stdio.h>

int main(){

  float x, y;
  float *fp, *fp2;
  x = 6.5;
  printf("Value of x is %.1f, address of x %p\n", x, &x);

  fp = &x;

  printf("Value in memory location fp is %.1f\n", *fp);
  *fp = 9.2;
  printf("New value of x is %.1f = %.1f \n", *fp, x);
  *fp = *fp + 1.5;
  printf("Final value of x is %.1f = %.1f \n", *fp, x);

  y = *fp;
  fp2 = fp;
  printf("Transfered value into y = %.1f and fp2 = %.1f \n", y, *fp2);
}
