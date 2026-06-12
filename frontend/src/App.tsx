import { useState } from "react";

export default function App() {
  const [file, setFile] = useState<File | null>(null);
  const [status, setStatus] = useState<string>("");

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files && e.target.files.length > 0) {
      setFile(e.target.files[0]);
    }
  };

  const handleUpload = async () => {
    if (!file) {
      setStatus("Please select a file first.");
      return;
    }

    const formData = new FormData();
    formData.append("doc", file);

    try {
      setStatus("Uploading...");

      const res = await fetch("http://localhost:8080/api/documents", {
        method: "POST",
        body: formData,
      });

      if (!res.ok) {
        throw new Error("Upload failed");
      }

      setStatus("Upload successful!");
      setFile(null);
    } catch (err) {
      console.error(err);
      setStatus("Upload failed.");
    }
  };

  return (
    <div style={{ padding: "40px", fontFamily: "Arial" }}>
      <h2>Upload Document</h2>

      <input type="file" onChange={handleFileChange} />

      <br />
      <br />

      <button onClick={handleUpload} disabled={!file}>
        Upload
      </button>

      <p>{status}</p>
    </div>
  );
}