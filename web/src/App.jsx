import { useState, useEffect } from "react";
import { UilPlus, UilPen } from "@iconscout/react-unicons";
import moment from "moment";
import ModalBox from "./modalBox";

export default function App() {
  const [notes, setNotes] = useState([]);
  const [search, setSearch] = useState(null);
  const [show, setShow] = useState(false);
  const [modal, setModal] = useState(false);
  const [boxData, setBoxData] = useState({});
  const [isEditable, setIsEditable] = useState(false);
  const [update, setUpdate] = useState(false);

  useEffect(() => {
    fetch("https://apiholder.000webhostapp.com/shownotes.php")
      .then((res) => res.json())
      .then((data) => setNotes(data.data))
      .catch((err) => console.log(err));
  }, []);

  const addNotes = (color) => {
    setNotes((prev) => [
      {
        notes: "",
        updateat: moment(new Date()).format("DD/MM/YYYY"),
        color,
      },
      ...prev,
    ]);
    setBoxData({
      notes: "",
      updateat: moment(new Date()).format("DD/MM/YYYY"),
      color,
    });
    setModal(true);
    setIsEditable(true);
    setUpdate(false)
  };

  //   var colorone="#F2B66D",
  // colortwo="#F2916D",
  //  colorthree="#B091F2",
  // colorfour="#05DBF2",
  // colorfive="#EFF294"

  return (
    <section className="flex flex-col">
      <aside className="border-r border-slate-500 py-4 w-20 sm:w-28 h-screen fixed text-center">
        <h1 className="sm:text-xl font-semibold">NoteVerse</h1>
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
              onClick={() => addNotes("1")}
              className="bg-[#F2B66D] rounded-full w-6 h-6"
            ></button>
            <button
              onClick={() => addNotes("2")}
              className="bg-[#F2916D] rounded-full w-6 h-6"
            ></button>
            <button
              onClick={() => addNotes("3")}
              className="bg-[#B091F2] rounded-full w-6 h-6"
            ></button>
            <button
              onClick={() => addNotes("4")}
              className="bg-[#05DBF2] rounded-full w-6 h-6"
            ></button>
            <button
              onClick={() => addNotes("5")}
              className="bg-[#EFF294] rounded-full w-6 h-6"
            ></button>
          </div>
        )}
      </aside>
      <main className="mt-4 ml-24 sm:ml-44">
        <input
          className="focus:outline-none border border-slate-500 text-lg py-1 px-4 rounded-3xl w-52 sm:w-2/5"
          placeholder="Search Notes"
          onChange={(e) => {
            if (e.target.value === "") {
              setSearch(null);
            } else {
              setSearch(
                notes.filter((data) => data.notes.includes(e.target.value))
              );
            }
          }}
          type="text"
          name="search"
        />
        <h1 className="text-5xl font-bold my-8">Notes</h1>
        <div className="flex flex-wrap gap-5">
          {((arr) =>
            arr.map((data, index) => {
              return (
                <div
                  key={index}
                  className={`bg-[#${
                    data.color === "1"
                      ? "F2B66D"
                      : data.color === "2"
                      ? "F2916D"
                      : data.color === "3"
                      ? "B091F2"
                      : data.color === "4"
                      ? "05DBF2"
                      : "EFF294"
                  }] w-60 h-52 rounded-2xl relative p-4 mx-auto`}
                >
                  {data.notes.length > 140 ? (
                    <p className="text-slate-900 font-medium">
                      {data.notes.slice(1, 130) + "..."}
                      <strong
                        className="cursor-pointer"
                        onClick={() => {
                          setBoxData(data);
                          setModal(true);
                          setIsEditable(false);
                          setUpdate(false)
                        }}
                      >
                        show more
                      </strong>
                    </p>
                  ) : (
                    <p className="text-slate-900 font-medium">{data.notes}</p>
                  )}

                  <div className="absolute bottom-5 flex items-end justify-between min-w-[208px]">
                    <span className="text-sm italic">{data.updateat}</span>
                    <UilPen
                      onClick={() => {
                        setBoxData(data);
                        setModal(true);
                        setIsEditable(true);
                        setUpdate(true)
                      }}
                      size="40px"
                      className="bg-black rounded-full text-white p-2 cursor-pointer"
                    />
                  </div>
                </div>
              );
            }))(search ?? notes)}
        </div>
      </main>
      {modal && (
        <ModalBox
          setModal={setModal}
          boxData={boxData}
          isEditable={isEditable}
          setIsEditable={setIsEditable}
          update={update}
          setUpdate={setUpdate}
        />
      )}
    </section>
  );
}
