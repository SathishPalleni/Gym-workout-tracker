import { useState } from "react";
import axios from "axios";

function WorkoutForm({ memberId, onSuccess }) {
  const [exerciseName, setExerciseName] = useState("");
  const [sets, setSets] = useState("");
  const [reps, setReps] = useState("");
  const [weight, setWeight] = useState("");
  const [notes, setNotes] = useState("");
  const [message, setMessage] = useState("");

  if (!memberId) {
    return <p>Select a member to add workout</p>;
  }

  const handleSubmit = (e) => {
    e.preventDefault();

    const payload = {
      memberId,
      workoutDate: new Date().toISOString().split("T")[0],
      exerciseName,
      sets,
      reps,
      weight: weight || null,
      notes,
    };

    axios
      .post("http://localhost:8080/api/workouts", payload)
      .then(() => {
        setMessage("Workout logged successfully ✅");
        setExerciseName("");
        setSets("");
        setReps("");
        setWeight("");
        setNotes("");
        onSuccess();
      })
      .catch(() => {
        setMessage("Failed to log workout ❌");
      });
  };

  return (
    <div>
      <h3>Add Workout</h3>

      {message && <p>{message}</p>}

      <form onSubmit={handleSubmit}>
        <input
          placeholder="Exercise"
          value={exerciseName}
          onChange={(e) => setExerciseName(e.target.value)}
          required
        />
        <br />

        <input
          type="number"
          placeholder="Sets"
          value={sets}
          onChange={(e) => setSets(e.target.value)}
          required
        />
        <br />

        <input
          placeholder="Reps"
          value={reps}
          onChange={(e) => setReps(e.target.value)}
          required
        />
        <br />

        <input
          type="number"
          placeholder="Weight (kg)"
          value={weight}
          onChange={(e) => setWeight(e.target.value)}
        />
        <br />

        <input
          placeholder="Notes"
          value={notes}
          onChange={(e) => setNotes(e.target.value)}
        />
        <br />

        <button type="submit">Save Workout</button>
      </form>
    </div>
  );
}

export default WorkoutForm;
