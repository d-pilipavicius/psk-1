export interface Student {
  id: number;
  firstName: string;
  lastName: string;
  studentId: number;
  optLockVersion: number;
}

export interface CreateStudent {
  firstName: string;
  lastName: string;
  studentId: string;
}
