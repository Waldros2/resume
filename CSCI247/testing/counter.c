#include <stdio.h>

int main(){

  int c, nc = 0, nl = 0, nw = 0;
  while ((c = getchar()) != EOF){
    nc++;
    if (c == '\n') nl++;
    if (c == ' ') nw++;
  }
  
  printf("Number of characters = %d, number of words = %d, number of lines = %d\n", nc, nw, nl);
}
  
