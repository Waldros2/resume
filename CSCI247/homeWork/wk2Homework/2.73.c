/*Write code for a function with the following prototype: Addition that saturates to TMin or TMax 
 int saturating_add(int x, int y);

Instead of overflowing the way normal two's complement addition does, saturating addition returns TMax when there would be positive overflow, and TMin when there would be negative overflow. Saturating arithmetic is commonly used in programs that perform digital signal processing. Your function should follow the bit-level integer coding rules (pg 128).

*/

#include <stdio.h>


int saturating_add(int x, int y){
  int TMax = 0x7FFFFFFF;
  int TMin = 0x80000000;
  int sum = x + y;
  int w = (sizeof(int) << 3) - 1;
  int sumCardinality = (sum >> w);
  int xCardinality = (x >> w);
  int yCardinality = (y >> w);

    int overFlow = ~(xCardinality & yCardinality) & sumCardinality;
    int underFlow = xCardinality & yCardinality & ~sumCardinality;

    overFlow && (sum = TMax);
    underFlow && (sum = TMin);
    printf("%d\n", sum);
    return sum;
}

int main(){

  saturating_add(0x7FFFFFFF,0x6FFFFFFF);
  saturating_add(35,35);
  saturating_add(0x80000000,0x80000001);
} 
