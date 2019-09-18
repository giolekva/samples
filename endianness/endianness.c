#include <assert.h>
#include <math.h>
#include <stdbool.h>
#include <stdio.h>

// Checks wether OS being used is big endian.
bool is_big_endian() {
  // Allocates four bytes for an integer, in one of which will be 
  // written 1. That one byte will be either right or left most
  // depending on OS being used is big or little endian respectively.
  int i = 1;
  // Lets get left most byte of the integer defined above.
  char ch = *(char*)&i;
  // If that byte is 0 than we are using big endian.
  return ch == 0;
}

// Composes integer out of 4 independently initialized bytes.
void compose_int() {
  char ch[4] = {1, 2, 3, 4};
  int actual = *(int*)ch;
  if (is_big_endian()) {
    // On big endian system right most byte corresponds to least
    // significant bits of the integer and left most byte to most
    // significant bits.
    int p = 1;
    int expected = 0;
    for (int i = 3; i >= 0; --i) {
      expected = expected + ch[i] * p;
      p = p * pow(2, 8);
    }
    printf("Expected: %d Actual: %d\n", expected, actual);
    assert(actual == expected);
  } else {  // Same but in reverse order.
    // On little endian system left most byte corresponds to least
    // significant bits of the integer and right most byte to most
    // significant bits.
    int p = 1;
    int expected = 0;
    for (int i = 0; i < 4; ++i) {
      expected = expected + ch[i] * p;
      p = p * pow(2, 8);
    }
    printf("Expected: %d Actual: %d\n", expected, actual);
    assert(actual == expected);
  }
}

void explore_activation_record() {
  // Local variables are allocated inside function activation record
  // in reverse order. So first 4 bytes will be allocated for |b| and
  // next 4 for |a|.
  int a = 1;
  int b = 2;
  printf("&b: %u &a: %u\n", (unsigned int)&b, (unsigned int)&a);
  assert(&b + 1 == &a);
  // Since we are on little endian OS, left most byte of |a| will contain
  // 1 and rest will be zeros, and left most byte of |b| will contain 2
  // and rest zeros.
  assert((*(char*)&a == 1) &&
	 (*((char*)&a + 1) == 0) &&
	 (*((char*)&a + 2) == 0) &&
	 (*((char*)&a + 3) == 0));
  assert((*(char*)&b == 2) &&
	 (*((char*)&b + 1) == 0) &&
	 (*((char*)&b + 2) == 0) &&
	 (*((char*)&b + 3) == 0));
  // Let's overwrite first byte of |a|.
  *((char*)&b + 4) = 100;
  assert(a == 100);
  // Let's overwrite second and forth bytes of |b|;
  *((char*)&a - 3) = 3;
  *((char*)&a - 1) = 5;
  assert(b == 2 + 3 * pow(2, 8) + 5 * pow(2, 24));
}

int main() {
  if (is_big_endian()) {
    printf("Big endian\n");
  } else {
    printf("Little endian\n");
  }
  compose_int();
  // Let's assure that going forward we are on little endian OS.
  assert(!is_big_endian());
  explore_activation_record();
  return 0;
}
