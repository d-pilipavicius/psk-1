import { useEffect, useState } from "react";
import "./getStudents.css";
import UpdateStudent from "./UpdateStudent";
import type { Student } from "../interfaces/Student";

export default function GetStudents() {
  const [students, setStudents] = useState<Student[]>([]);
  const [editingStudent, setEditingStudent] = useState<number>(-1);
  const [loading, setLoading] = useState<boolean>(false);

  useEffect(() => {
    setLoading(true);
    fetch("http://localhost:8080/PSK_1/api/students")
      .then((response) => {
        if(!response)
          throw new Error("Bad fetch");
        
        return response.json();
      })
      .then((data) => {
        console.log(data);
        setStudents(data);
        setLoading(false);
      })
      .catch((err) => {
        console.log(err.message);
        setLoading(false);
      });

  }, [editingStudent]);

  return (
    <>
      { (editingStudent >= 0 && students.some((student) => student.id === editingStudent)) ? 
        <UpdateStudent 
          student={students.filter((student) => student.id === editingStudent)[0]}
          onClick={() => setEditingStudent(-1)}/>
        :
        <div className="student-list-window">
          { loading ? 
            <div className="spinner"/>
            :
            <>
              <h2>Student List</h2>
              <ul className="student-list">
                {students.map((student) => (
                  <li key={student.id} onClick={() => setEditingStudent(student.id)} className="student-list-item">
                    <p><strong>{student.firstName} {student.lastName}</strong></p>
                    <span className="student-id">ID: {student.id}</span>
                  </li>
                ))}
              </ul>
            </>
          }
        </div>
      }
    </>
  );
}