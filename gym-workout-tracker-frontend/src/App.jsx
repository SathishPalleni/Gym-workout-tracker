import MemberSelect from "./components/MemberSelect";
import WorkoutList from "./components/WorkoutList";
import WorkoutForm from "./components/WorkoutForm";
import { useState } from "react";

function App() {
  const [memberId, setMemberId] = useState("");
  const [refresh, setRefresh] = useState(false);
  const triggerRefresh = () => setRefresh(!refresh);

  return (
    <div style={{ padding: "20px" }}>
      <h1>Gym Workout Tracker</h1>

      <MemberSelect onSelect={setMemberId} />

      <WorkoutForm
        memberId={memberId}
        onSuccess={() => setRefresh(!refresh)}
      />

      <WorkoutList memberId={memberId} refresh={refresh} />
    </div>
  );
}

export default App;



