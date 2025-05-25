import { useState } from 'react';
import './popup.css';
import '../App.css';

interface Props {
  message: string;
  onYes: () => void;
  onNo: () => void;
}

const Popup = ({ message, onYes, onNo }: Props) => {
  const [loading, setLoading] = useState<boolean>(false);

  const onAccept = async () => {
    setLoading(true);
    await onYes();
    setLoading(false);
  }

    const onDecline = async () => {
    setLoading(true);
    await onNo();
    setLoading(false);
  }

  return (
    <>{ loading ? 
        <div className='spinner'/>
        :
        <div className="popup-overlay">
          <div className="popup">
            <p>{message}</p>
            <button onClick={onAccept}>Yes</button>
            <button onClick={onDecline}>No</button>
          </div>
        </div>
    }</>

  );
};

export default Popup;