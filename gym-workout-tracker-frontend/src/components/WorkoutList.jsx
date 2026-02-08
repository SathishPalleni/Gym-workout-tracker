import { useEffect, useState } from "react";
import axios from "axios";
import EditWorkoutForm from "./EditWorkoutForm";

function WorkoutList({ memberId, refresh, onRefresh }) {
  const [workouts, setWorkouts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [editingWorkout, setEditingWorkout] = useState(null);

  useEffect(() => {
    if (!memberId) return;

    setLoading(true);

    axios
      .get(`http://localhost:8080/api/workouts?memberId=${memberId}`)
      .then((res) => setWorkouts(res.data))
      .catch((err) => console.error(err))
      .finally(() => setLoading(false));
  }, [memberId, refresh]);

  const deleteWorkout = async (id) => {
    if (!window.confirm("Delete this workout?")) return;

    try {
      await axios.delete(`http://localhost:8080/api/workouts/${id}`);
      onRefresh();
    } catch (err) {
      console.error(err);
      alert("Failed to delete workout");
    }
  };

  if (!memberId) return <p>Please select a member</p>;
  if (loading) return <p>Loading workouts...</p>;

  return (
    <div>
      <h3>Workout History</h3>

      {editingWorkout && (
        <EditWorkoutForm
          workout={editingWorkout}
          onCancel={() => setEditingWorkout(null)}
          onSuccess={() => {
            setEditingWorkout(null);
            onRefresh();
          }}
        />
      )}

      {workouts.length === 0 ? (
        <p>No workouts found</p>
      ) : (
        <table border="1" cellPadding="8">
          <thead>
            <tr>
              <th>Date</th>
              <th>Exercise</th>
              <th>Sets</th>
              <th>Reps</th>
              <th>Weight</th>
              <th>Notes</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {workouts.map((w) => (
              <tr key={w.id}>
                <td>{w.workoutDate}</td>
                <td>{w.exerciseName}</td>
                <td>{w.sets}</td>
                <td>{w.reps}</td>
                <td>{w.weight ?? "-"}</td>
                <td>{w.notes ?? "-"}</td>
                <td>
                  <button onClick={() => setEditingWorkout(w)}>
                    ✏️ Edit
                  </button>{" "}
                  <button onClick={() => deleteWorkout(w.id)}>
                    🗑 Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default WorkoutList;
