import { useState } from "react";
import "./createStudent.css"
import "../App.css"
import type { CreateStudent } from "../interfaces/Student";

const createStudent = async (student: CreateStudent) => {
  await fetch(`http://localhost:8080/PSK_1/api/students`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        firstName: student.firstName,
        lastName: student.lastName,
        studentId: student.studentId,
      }),
    });
}

interface Response {
  response: string;
}

export default function CreateStudent() {
  const [studName, setStudName] = useState<string>(""); 
  const [studLastName, setStudLastName] = useState<string>("");
  const [studId, setStudId] = useState<string>("");
  const [generatorId, setGeneratorId] = useState<string>("");
  const [loadingId, setLoadingId] = useState<boolean>(false);
  const [loading, setLoading] = useState<boolean>(false);

  const getGeneratorId = async () => {
    setLoadingId(true);
    const genId: string = await fetch(`http://localhost:8080/PSK_1/api/generateId`)
      .then((response) => {
        if(!response) {
          setLoadingId(false);
          throw new Error("No response");
        }
        return response.json();
      });
    setGeneratorId(genId);
    setLoadingId(false);
  }

  const getStudentId = async () => {
    setLoadingId(true);
    const genId: Response = await fetch(`http://localhost:8080/PSK_1/api/generateId/${generatorId}`)
      .then((response) => {
        if(!response) {
          setLoadingId(false);
          throw new Error("No response");
        }
        return response.json();
      });
    const element = parseInt(genId.response);
    if(Number.isNaN(element))
      alert(genId.response);
    else {
      setGeneratorId("");
      setStudId(genId.response);
    }
    setLoadingId(false);
  }

  const onClick = async () => {
    if(studId.trim() === "")
      return;
    
    createStudent({
      firstName: studName,
      lastName: studLastName,
      studentId: studId
    });
    setStudName("");
    setStudLastName("");
    setStudId("");
    setLoading(false);
  };
  
  return (
    <>
      <div className="student-form-window">
        <h2>Create Student</h2>
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

          <label>Student Id</label>
          <div className="horizontal">
            { loadingId ? 
              <div className="spinner"/>
              :
              <>
                <input 
                  type="button" 
                  onClick={getGeneratorId} 
                  value="Generate id" 
                  disabled={generatorId !== "" || studId !== ""}
                />
                { studId === "" ?
                  <input
                    type="button"
                    onClick={getStudentId}
                    value="Check generator"
                    disabled={generatorId === ""}
                  />
                  :
                  <p>Id: {studId}</p>
                }
              </>
            }
          </div>        

          { loading ? 
            <div className="spinner"/>
            :
            <input type="button" onClick={onClick} value="Create"/>
          }
        </form>
      </div>
    </>
  );
}