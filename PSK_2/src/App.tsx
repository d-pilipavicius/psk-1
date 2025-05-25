import './App.css'
import {useState} from "react";
import Tabs from './components/Tabs';
import type { Student } from './interfaces/Student';
import CreateStudent from './pages/CreateStudent';
import GetStudents from './pages/GetStudents';

export type Pages = "Home" | "CreateStudent" | "GetStudents" | "EditStudent";

export default function App() {
    const [tab, setTab] = useState<Pages>("Home");
    const [student, setStudent] = useState<Student | null>(null);

    return (
        <>
            <Tabs 
                tabs={["Home", "CreateStudent", "GetStudents"]} 
                onPressTab={(_tab) => setTab(_tab)}
            />
            { tab === "Home" && <div><h1>Home</h1></div> }
            { tab === "CreateStudent" && <CreateStudent/>}
            { tab === "GetStudents" && <GetStudents/> }
        </>
    );
};
