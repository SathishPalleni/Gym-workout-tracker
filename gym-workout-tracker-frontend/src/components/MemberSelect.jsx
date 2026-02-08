import { useEffect, useState } from "react";
import axios from "axios";

function MemberSelect({ onSelect }) {
  const [members, setMembers] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/member")
      .then((res) => setMembers(res.data))
      .catch(() => setError("Failed to load members"));
  }, []);

  if (error) return <p style={{ color: "red" }}>{error}</p>;

  return (
    <div>
      <h3>Select Member</h3>
      <select onChange={(e) => onSelect(e.target.value)}>
        <option value="">-- Select --</option>
        {members.map((m) => (
          <option key={m.id} value={m.id}>
            {m.name}
          </option>
        ))}
      </select>
    </div>
  );
}

export default MemberSelect;
