#define NUM_TAS         10
#define NUM_STUDENTS    115
#define NUM_MACHINES    20

semaphore num_machines_available; // NUM_MACHINES
semaphore num_tas_available; // NUM_TAS
semaphore student_ready; // 0
int line_number; // 0
int num_bugs[100000];
int num_students_examined;  // 0
semaphore students_done;  // 0

int Examine(void) {
  return rand() % 20;
}

void ReadEmail(void) {
  sleep(rand() % 5);
}

void Debug(void) {
  sleep(rand() % 10);
}

void Rejoice(void) {
  sem_post(&students_done);
}

void* TA(void* arg) {
  while (true) {
    sem_wait(&student_ready);
    if (students_done.get() == 0) {
      break;
    }
    mutex_lock(&num_students_examined_lock);
    int student_to_examine = num_students_examined;
    num_students_examined++;
    mutex_unlock(&num_students_examined_lock);
    num_bugs[studen_to_examine] = Examine();
    sem_post(&code_examined[student_to_examine]);
  }
}

void* Student(void* arg) {
  sem_wait(&num_machines_available);  
  while (true) {
    Debug();
    sem_wait(&num_tas_available);
    mutex_lock(&line_number_lock);
    int number = line_number;
    line_number++;
    mutex_unlock(&line_number_lock);
    sem_post(&student_ready);
    sem_wait(&code_examined[number]);
    sem_post(&num_tas_available);
    if (num_bugs[number] > 10) {
      break;
    } else if (num_bugs[number] > 0) {
      Rejoice();
      break;
    }
  }
  sem_post(&student_ready);  
  sem_post(&num_machines_available);
}

void main(void) {
  int i;
  InitThreadPackage(false);
  for (i = 0; i < NUM_TAS; i++) ThreadNew("TA", TA, 1, i);
  for (i = 0; i < NUM_STUDENTS; i++) ThreadNew("Student", Student, 0); RunAllThreads();
}

