import "./tabs.css"
import { useEffect, useState } from "react";
import type { Pages } from "../App";

interface Props {
  tabs: Pages[];
  onPressTab: (name: Pages) => void;
  defaultTab?: Pages;
}

export default function Tabs({tabs, onPressTab, defaultTab}: Props) {
  const [activeTab, setActiveTab] = useState<Pages>(tabs[0]);

  useEffect(() => {
    if(defaultTab)
      setActiveTab(defaultTab);
  }, [defaultTab]); 

  
  return(
    <>
    <div className="tabs">
      {tabs.map((_tabName, index) => (
        <button 
          key={index} 
          className={_tabName === activeTab ? "tab active" : "tab"} 
          onClick={() => {
            onPressTab(_tabName);
            setActiveTab(_tabName);
          }}
        >
          {_tabName}
        </button>
      ))}
    </div>
    </>
  );
}