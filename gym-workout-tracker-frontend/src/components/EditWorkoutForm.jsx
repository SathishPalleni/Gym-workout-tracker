import { useState } from "react";
import axios from "axios";

function EditWorkoutForm({ workout, onCancel, onSuccess }) {
  const [form, setForm] = useState({
    workoutDate: workout.workoutDate,
    exerciseName: workout.exerciseName,
    sets: workout.sets,
    reps: workout.reps,
    weight: workout.weight || "",
    notes: workout.notes || ""
  });

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    axios
      .put(`http://localhost:8080/api/workouts/${workout.id}`, form)
      .then(() => {
        alert("Workout updated successfully");
        onSuccess();
      })
      .catch((err) => {
        console.error(err);
        alert("Failed to update workout");
      });
  };

  return (
    <div style={{ border: "1px solid #ccc", padding: "10px", marginBottom: "20px" }}>
      <h4>Edit Workout</h4>

      <form onSubmit={handleSubmit}>
        <input
          type="date"
          name="workoutDate"
          value={form.workoutDate}
          onChange={handleChange}
        />

        <input
          name="exerciseName"
          placeholder="Exercise"
          value={form.exerciseName}
          onChange={handleChange}
        />

        <input
          name="sets"
          placeholder="Sets"
          value={form.sets}
          onChange={handleChange}
        />

        <input
          name="reps"
          placeholder="Reps"
          value={form.reps}
          onChange={handleChange}
        />

        <input
          name="weight"
          placeholder="Weight"
          value={form.weight}
          onChange={handleChange}
        />

        <input
          name="notes"
          placeholder="Notes"
          value={form.notes}
          onChange={handleChange}
        />

        <br /><br />

        <button type="submit">Update</button>
        <button type="button" onClick={onCancel} style={{ marginLeft: "10px" }}>
          Cancel
        </button>
      </form>
    </div>
  );
}

export default EditWorkoutForm;
