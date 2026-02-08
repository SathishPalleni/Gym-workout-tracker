import { useState } from "react";
import axios from "axios";

function AddWorkoutForm({ memberId, onSuccess }) {
  const [form, setForm] = useState({
    workoutDate: new Date().toISOString().slice(0, 10),
    exerciseName: "",
    sets: "",
    reps: "",
    weight: "",
    notes: "",
  });

  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  if (!memberId) {
    return <p>Select a member to add workout</p>;
  }

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setMessage("");

    try {
      await axios.post("http://localhost:8080/api/workouts", {
        memberId: memberId,
        workoutDate: form.workoutDate,
        exerciseName: form.exerciseName,
        sets: Number(form.sets),
        reps: form.reps,
        weight: form.weight ? Number(form.weight) : null,
        notes: form.notes,
      });

      setMessage("✅ Workout logged!");
      setForm({
        workoutDate: new Date().toISOString().slice(0, 10),
        exerciseName: "",
        sets: "",
        reps: "",
        weight: "",
        notes: "",
      });

      onSuccess(); // refresh workout list
    } catch (err) {
      console.error(err);
      setMessage("❌ Failed to add workout");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ marginTop: "20px" }}>
      <h3>Add Workout</h3>

      <form onSubmit={handleSubmit}>
        <div>
          <label>Date: </label>
          <input
            type="date"
            name="workoutDate"
            value={form.workoutDate}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label>Exercise: </label>
          <input
            name="exerciseName"
            value={form.exerciseName}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label>Sets: </label>
          <input
            type="number"
            name="sets"
            value={form.sets}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label>Reps: </label>
          <input
            name="reps"
            value={form.reps}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label>Weight (kg): </label>
          <input
            type="number"
            name="weight"
            value={form.weight}
            onChange={handleChange}
          />
        </div>

        <div>
          <label>Notes: </label>
          <input
            name="notes"
            value={form.notes}
            onChange={handleChange}
          />
        </div>

        <button type="submit" disabled={loading}>
          {loading ? "Saving..." : "Add Workout"}
        </button>
      </form>

      {message && <p>{message}</p>}
    </div>
  );
}

export default AddWorkoutForm;
