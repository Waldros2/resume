#include <stdio.h>

#define SIZE 3
int main(){

  float x[SIZE];
  float *fp;
  int i;

  for(i = 0; i <SIZE; i++)
    x[i] = 0.5*(float)i;

  for (i = 0; i < SIZE; i++)
    printf(" %d %.1f \n", i, x[i]);

  fp = x;
  
  for (i = 0; i <SIZE; i++)
    printf(" %d %.1f \n", i, *(fp+i));
}
