import { useEffect, useState } from "react";
import {
  UilSave,
  UilTimes,
  UilPen,
  UilSpinner,
} from "@iconscout/react-unicons";
import querystring from "querystring";

export default function modalBox({
  setModal,
  boxData,
  isEditable,
  setIsEditable,
  update,
  setUpdate,
}) {
  const [note, setNote] = useState(boxData.notes);
  const [save, setSave] = useState(false);
  const [saveing, setSaveing] = useState(false);

  useEffect(() => {
    isEditable
      ? document
          .getElementsByName("note")[0]
          .setSelectionRange(note.length, note.length)
      : null;
  }, [isEditable]);

  async function saveNote() {
    if (note.length > 10) {
      setSaveing(true);
      if (update) {
        await fetch("https://apiholder.000webhostapp.com/update.php", {
          method: "POST",
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
          },
          body: querystring.stringify({
            notes: note,
            id: boxData.id,
          }),
        });
      } else {
        await fetch("https://apiholder.000webhostapp.com/addnote.php", {
          method: "POST",
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
          },
          body: querystring.stringify({
            notes: note,
            updateat: boxData.updateat,
            color: boxData.color,
          }),
        });
      }
      setModal(false);
      window.location.reload();
    } else {
      alert("Note is too short");
    }
  }

  return (
    <div className="w-screen h-screen fixed top-0 z-10 bg-[#424242d9] flex items-center justify-center">
      <div
        className={`max-w-4xl w-full bg-[#${
          boxData.color === "1"
            ? "F2B66D"
            : boxData.color === "2"
            ? "F2916D"
            : boxData.color === "3"
            ? "B091F2"
            : boxData.color === "4"
            ? "05DBF2"
            : "EFF294"
        }] max-h-[500px] h-screen rounded-3xl relative p-12`}
      >
        <UilTimes
          onClick={() => {
            note === boxData.notes ? setModal(false) : setSave(true);
          }}
          size="40px"
          className="absolute top-4 right-4 hover:bg-slate-400 rounded-full transition-all cursor-pointer"
        />
        {save && (
          <div className="w-48 bg-white p-4 rounded-md absolute right-0 top-0">
            <p>Do you want to Save?</p>
            <div className="flex justify-between items-center mt-4">
              <span
                onClick={() => saveNote()}
                className="cursor-pointer bg-slate-200 px-4 py-1"
              >
                Save
              </span>
              <span
                onClick={() => setModal(false)}
                className="cursor-pointer bg-slate-200 px-4 py-1"
              >
                Close
              </span>
            </div>
          </div>
        )}
        {!isEditable ? (
          <p
            className={`w-full ${
              note.length > 1600 ? "overflow-y-scroll" : null
            } max-h-[370px]`}
          >
            {note}
          </p>
        ) : (
          <textarea
            autoFocus
            className={`w-full ${
              note.length > 1600 ? "overflow-y-scroll" : null
            } max-h-[370px] h-screen focus:outline-none bg-transparent`}
            onChange={(e) => setNote(e.target.value)}
            name="note"
            value={note}
          ></textarea>
        )}

        <span className="text-sm italic absolute bottom-4 left-10">
          {boxData.updateat}
        </span>
        {isEditable ? (
          saveing ? (
            <UilSpinner
              size="40px"
              className="bg-black rounded-full text-white p-2 absolute bottom-4 right-10 animate-spin"
            />
          ) : (
            <UilSave
              onClick={() => saveNote()}
              size="40px"
              className="bg-black rounded-full text-white p-2 absolute bottom-4 right-10 cursor-pointer"
            />
          )
        ) : (
          <UilPen
            onClick={() => {
              setIsEditable(true);
              setUpdate(true);
            }}
            size="40px"
            className="bg-black rounded-full text-white p-2 absolute bottom-4 right-10 cursor-pointer"
          />
        )}
      </div>
    </div>
  );
}
