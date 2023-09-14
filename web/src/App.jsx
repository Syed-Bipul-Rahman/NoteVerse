import { useState, useEffect } from "react";
import { UilPlus, UilPen } from "@iconscout/react-unicons";
import moment from 'moment';

export default function App() {
  const [notes, setNotes] = useState([]);
  const [show, setShow] = useState(false);

  useEffect(() => {
    fetch("https://apiholder.000webhostapp.com/shownotes.php")
      .then((res) => res.json())
      .then((data) => setNotes(data.data))
      .catch((err) => console.log(err));
  }, []);

  //   var colorone="#F2B66D",
  // colortwo="#F2916D",
  //  colorthree="#B091F2",
  // colorfour="#B091F2",
  // colorfive="#EFF294"

  return (
    <section className="flex flex-col">
      <aside className="border-r border-slate-500 py-4 w-28 h-screen fixed text-center">
        <h1 className="text-xl font-semibold">NoteVerse</h1>
        <UilPlus
          size="50px"
          onClick={() => {
            setShow(true);
            setTimeout(() => {
              setShow(false);
            }, 5000);
          }}
          className="mx-auto mt-8 bg-black rounded-full text-white p-2 cursor-pointer"
        />
        {show && (
          <div className="flex flex-col gap-4 mt-8 justify-between items-center">
            <button
              onClick={() => {
                setNotes((prev) => [
                  {
                    notes: "",
                    updateat: moment(new Date()).format('DD/MM/YYYY'),
                    color: "1",
                  },
                  ...prev,
                ]);
              }}
              className="bg-[#F2B66D] rounded-full w-6 h-6"
            ></button>
            <button className="bg-[#F2916D] rounded-full w-6 h-6"></button>
            <button className="bg-[#B091F2] rounded-full w-6 h-6"></button>
            <button className="bg-[#B091F2] rounded-full w-6 h-6"></button>
            <button className="bg-[#EFF294] rounded-full w-6 h-6"></button>
          </div>
        )}
      </aside>
      <main className="mt-4 ml-44">
        <input
          className="focus:outline-none border border-slate-500 text-lg py-1 px-4 rounded-3xl w-2/5"
          placeholder="Search Notes"
          type="text"
          name="search"
        />
        <h1 className="text-5xl font-bold my-8">Notes</h1>
        <div className="flex flex-wrap gap-5">
          {notes.map(({ notes, updateat, color }, index) => {
            return (
              <div
                key={index}
                className={`bg-[#${
                  color === "1"
                    ? "F2B66D"
                    : color === "2"
                    ? "F2916D"
                    : color === "3"
                    ? "B091F2"
                    : color === "4"
                    ? "B091F2"
                    : "EFF294"
                }] w-60 h-52 rounded-2xl relative p-4`}
              >
                <p className="text-slate-900 font-medium">
                  {notes.length > 140 ? notes.slice(1, 140) + "..." : notes}
                </p>
                <div className="absolute bottom-5 flex items-end justify-between min-w-[208px]">
                  <span className="text-sm italic">{updateat}</span>
                  <UilPen
                    size="40px"
                    className="bg-black rounded-full text-white p-2"
                  />
                </div>
              </div>
            );
          })}
        </div>
      </main>
    </section>
  );
}
