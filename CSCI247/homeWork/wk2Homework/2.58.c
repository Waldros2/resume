/*                                                            *
 * Tests whether the machine is a little Endian or big Endian *
 *                                                            *
*/

#include<stdio.h>

int main(){
  int test = 0x1;

  char *grabber = (char*)&test;
  printf("%d\n",*grabber);
}
