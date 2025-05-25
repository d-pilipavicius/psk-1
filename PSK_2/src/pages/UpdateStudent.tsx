import { useState } from "react";
import "./createStudent.css";
import type { Student } from "../interfaces/Student";
import Popup from "../components/Popup";

interface Params {
  student: Student;
  onClick: () => void;
}

const updateStudent = async (student: Student): Promise<Response> => {
  return await fetch(`http://localhost:8080/PSK_1/api/students/${student.id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        id: student.id,
        firstName: student.firstName,
        lastName: student.lastName,
        studentId: student.studentId,
        optLockVersion: student.optLockVersion
      }),
    });
}

const getStudent = async (id: number): Promise<Student> => {
  return await fetch(`http://localhost:8080/PSK_1/api/students/${id}`)
  .then((resp) => {
    if(!resp)
      throw new Error("Error on response");
    else if(resp.status === 200)
      return resp.json()
    else 
      throw resp;
  })
}

export default function UpdateStudent({student, onClick}: Params) {
  const [studName, setStudName] = useState<string>(student.firstName); 
  const [studLastName, setStudLastName] = useState<string>(student.lastName);
  const [loading, setLoading] = useState<boolean>(false);
  const [optLockStud, setOptLockStud] = useState<Student | null>(null);

  const submit = async () => {
    setLoading(true);
    try {
      const response = await updateStudent({
        id: student.id,
        firstName: studName,
        lastName: studLastName,
        studentId: student.studentId,
        optLockVersion: student.optLockVersion
      });
      switch(response.status) {
        case 200:
          break;
        case 409:
          const getResp = await getStudent(student.id);
          setOptLockStud(getResp);
          return;
        default:
          throw new Error(`Unexpected response: ${response.status}`);
      }
    } catch(e) {
      if(e instanceof Error) {
        setLoading(false);
        throw e;
      }
    }
    setLoading(false);
    onClick();
  }

  const onNo = () => {
    setOptLockStud(null);
    onClick();
  }

  const onYes = async () => {
    if(optLockStud)
      await updateStudent({
        id: student.id,
        firstName: studName,
        lastName: studLastName,
        studentId: student.studentId,
        optLockVersion: optLockStud?.optLockVersion 
      });
    setOptLockStud(null);
    onClick();
  }

  return (
    <div className="student-form-window">
      <div className="go-back-container">
        <button className="go-back-button" onClick={onClick}>← Go Back</button>
      </div>
      <h2>Update student</h2>
      <form>
        <label htmlFor="firstName">First Name</label>
        <input 
          type="text" 
          id="firstName" 
          placeholder="Enter first name" 
          value={studName} 
          onChange={(e) => setStudName(e.target.value)}/>

        <label htmlFor="lastName">Last Name</label>
        <input 
          type="text" 
          id="lastName"
          placeholder="Enter last name"
          value={studLastName} 
          onChange={(e) => setStudLastName(e.target.value)}/>      

        { loading ? 
          <div className="spinner"/>
          :
          <input type="button" onClick={submit} value="Edit"/>
        }
        { optLockStud !== null &&
          <Popup 
            message={`Overwrite edited student?\nName: ${optLockStud.firstName}\nLast name: ${optLockStud.lastName}`} 
            onYes={onYes} 
            onNo={onNo}/>
        }
      </form>
    </div>
  );
}