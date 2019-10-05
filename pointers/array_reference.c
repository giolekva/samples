#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int IntCmp(const void* a, const void* b) {
  return *(int*)a  - *(int*)b;
}

void IntSearch() {
  int arr[] = {1, 2, 3, 4, 5};
  int key = 3;
  int* found = bsearch(&key, arr, 5, sizeof(int), IntCmp);
  if (found == NULL) {
    printf("%d not found.\n", key);
  } else {
    printf("%d found at index %ld.\n", key, found - arr);
  }
  printf("\n---\n\n");
}

int StrCmp(const void* a, const void* b) {
  return strcmp(*(char**)a, *(char**)b);
}

void StrSearch() {
  char* arr[] = {"ab", "c", "de"};
  char* key = "c";
  // This would fail if key was defined as char key[] = "c" as &key would
  // point to same address as key but represent char[1]* instead of char**.
  char** found = bsearch(&key, arr, 3, sizeof(char*), StrCmp);
  if (found == NULL) {
    printf("%s not found.\n", key);
  } else {
    printf("%s found at index %ld.\n", key, found - arr);
  }
  printf("\n---\n\n");
}

void ReferenceToArray() {
  int arr[3] = {1, 2, 3};
  // arr and &arr point to the same address, the only difference
  // is that arr points to int while &arr points to int[3] so their
  // types are int* and (roughly speaking) int[3]* respectively.
  printf(" arr = %p\n",  arr);
  printf("&arr = %p\n",  &arr);   
  // This should print 4.
  printf("  (arr + 1) -  arr = %ld\n", (char*)(arr + 1) - (char*)(arr));
  // This should print 12 as type of &arr is int[3],
  //  which means &arr + 1 is 12 bytes away from &arr.
  printf(" (&arr + 1) - &arr = %ld\n", (char*)(&arr + 1) - (char*)(&arr));
  // This will print 12.
  printf("%lu\n", sizeof(arr));
  // And this 4 or 8 on 32bit and 64bit architectures respectively..
  printf("%lu\n", sizeof(&arr));
  // Back to pointer arithmetics.
  printf(" arr + 1 = %p\n",  arr + 1);
  printf("&arr + 1 = %p\n",  &arr + 1);
}

int main() {
  IntSearch();
  StrSearch();
  ReferenceToArray();
  return 0;
}
