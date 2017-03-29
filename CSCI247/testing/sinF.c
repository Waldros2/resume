/*****************
*   Table of     *
* Sin Function   *
*****************/

#include <stdio.h>
#include <math.h>

int main(){
  int angleDegree;
  double angleRadian, pi, value;

  printf("\nCompute a table of the sin function \n\n");
  pi = 4.0*atan(1.0);
  printf (" Value of Pi = %f \n\n", pi);
  printf(" angle Sin \n");
  
  angleDegree=0;
  
  while (angleDegree <=360 ){
    angleRadian = pi * angleDegree/180.0;
    value = sin(angleRadian);
    printf( "%3d %f \n ", angleDegree, value);
    angleDegree = angleDegree + 10;
  }
}
