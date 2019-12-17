
#define NUM_TAS         10
#define NUM_STUDENTS    115
#define NUM_MACHINES    20

struct ta_state {
  bool available;
  pthread_mutex_t available_lock;
  sem_t requested; // 0
  sem_t finished; // 0
  int num_bugs;
};

ta_state tas[NUM_TAS];
sem_t num_tas_available; // 10
sem_t num_machine_available; // 20
int num_students_left; // 115
pthread_mutex_t num_students_left_lock;

// these simulation functions
static int Examine(void) {
  return rand() % 15;
}

static void ReadEmail(void) {
  sleep(rand() % 5);
}

static void Debug(void); {
  sleep(rand() % 10);
}

static void Rejoice(void) {
  sleep(rand() % 5);
}

void* TA(void* arg) {
  ta_state* state = arg;
  while (true) {
    sem_wait(&state->requested);
    mutex_lock(&num_students_left_lock);
    if (num_students_left == 0) { 
      mutex_unlock(&num_students_left_lock);     
      break;
    }
    mutex_unlock(&num_students_left_lock);
    state->num_bugs = Examine();
    sem_post(&state->finished);
  }
}

void* Student(void* arg) {
  sem_wait(&num_machines_available);
  int num_bugs = 1;
  while (num_bugs != 0 && num_bugs <= 10) {
    Debug();
    sem_wait(&num_tas_available);
    for (int i = 0; i < NUM_TAS; ++i) {
      mutex_lock(&tas[i].available_lock);
      if (tas[i].avialable) {
	tas[i].available = false;
	mutex_unlock(&tas[i].available_lock);
	sem_post(&tas[i].requested);
	sem_wait(&tas[i].finished);
	num_bugs = tas[i].num_bugs;
	mutex_lock(&tas[i].available_lock);
	tas[i].available = true;
	mutex_unlock(&tas[i].available_lock);
	break;
      } else {
	mutex_unlock(&tas[i].available_lock);
      }
    }
    sem_post(&num_tas_available);
  }
  sem_post(&num_machines_available);
  if (num_bugs == 0) {
    Rejoice();
  }
  mutex_lock(&num_students_left_lock);
  num_students_left--;
  if (num_students_left == 0) {
    for (int i = 0; i < NUM_TAS; ++i) {
      sem_post(&tas[i].requested);
    }
  }
  mutex_unlock(&num_students_left_lock);
}

void Init() {
  sem_init(&num_tas_available, 0, NUM_TAS);
  sem_init(&num_machines_available, 0, NUM_MACHINES);
  for (int i = 0; i < NUM_TAS; i++) {
    tas[i].available = true;
    sem_init(&tas[i].requested, 0, 0);
    sem_init(&tas[i].finished, 0, 0);
  }
}
  
int main(void) {
  Init();
  pthread_t tid;
  for (int i = 0; i < NUM_TAS; i++) {
    pthread_create(&tid, NULL, TA, &tas[i]);
    pthread_detach(&tid);
  }
  for (int i = 0; i < NUM_STUDENTS; i++) {
    pthread_create(&tid, NULL, Student, NULL);
    pthread_detach(&tid);
  }
  // wait
  return 0;
}
